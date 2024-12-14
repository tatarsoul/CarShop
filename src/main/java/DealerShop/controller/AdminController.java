package DealerShop.controller;

import DealerShop.model.Carbrand;
import DealerShop.model.CarbrandLog;
import DealerShop.model.Role;
import DealerShop.model.User;
import DealerShop.repository.*;
import DealerShop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    private final UserService userService;
    private final CarbrandLogService carbrandLogService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CarbrandRepository carbrandRepository;
    private final CarbrandPlanRepository carbrandPlanRepository;
    private final DealershopRepository dealershopRepository;

    public AdminController(UserService userService, CarbrandLogService carbrandLogService,
                           RoleService roleService, UserRepository userRepository,
                           RoleRepository roleRepository, CarbrandRepository carbrandRepository,
                           CarbrandPlanRepository carbrandPlanRepository, DealershopRepository dealershopRepository) {
        this.userService = userService;
        this.carbrandLogService = carbrandLogService;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.carbrandRepository = carbrandRepository;
        this.carbrandPlanRepository = carbrandPlanRepository;
        this.dealershopRepository = dealershopRepository;
    }

    @RequestMapping("/admin")
    public String viewAdminPanel(@RequestParam(required = false) String keyword, Model model) {
        List<CarbrandLog> carbrandLogs = carbrandLogService.getAllCarbrandLogs(keyword);
        List<User> users = userService.getAllUsers(keyword);
        List<Role> roles = roleService.getAllRoles(keyword);
        long totalCarbrands = carbrandRepository.count();
        long totalDealershops = dealershopRepository.count();
        long totalCarbrandPlans = carbrandPlanRepository.count();
        List<Object[]> carbrandsByDealershops = carbrandRepository.countByDealershop();
        List<Object[]> carbrandsByPlans = carbrandRepository.countCarbrandsByCarbrandPlan();
        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        model.addAttribute("carbrandLogs", carbrandLogs);
        model.addAttribute("totalCarbrands", totalCarbrands);
        model.addAttribute("totalDealershops", totalDealershops);
        model.addAttribute("totalCarbrandPlans", totalCarbrandPlans);
        model.addAttribute("carbrandsByDealershops", carbrandsByDealershops);
        model.addAttribute("carbrandsByPlans", carbrandsByPlans);
        return "admin";
    }

    @PostMapping("/admin/save")
    public String saveUser(@ModelAttribute User user, @RequestParam Long roleId) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        Optional<Role> existingRole = roleRepository.findById(roleId);
        if (existingUser.isPresent() && existingRole.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setLogin(user.getLogin());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setRole(existingRole.get());
            userRepository.save(updatedUser);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        // Заполняем форму для редактирования
        user.ifPresent(value -> model.addAttribute("user", value));
        return "admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteAdmin(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
