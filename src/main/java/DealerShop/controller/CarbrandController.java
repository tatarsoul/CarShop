package DealerShop.controller;

import DealerShop.model.*;
import DealerShop.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
public class CarbrandController {
    private final CarbrandService carbrandService;
    private final DealershopService dealershopService;
    private final CarbrandPlanService carbrandPlanService;

    public CarbrandController(CarbrandService carbrandService, DealershopService dealershopService,
                              CarbrandPlanService carbrandPlanService) {
        this.carbrandService = carbrandService;
        this.dealershopService = dealershopService;
        this.carbrandPlanService = carbrandPlanService;
    }

    @RequestMapping("/")
    public String viewCarbrands(String keyword, Model model) {
        return getString(model, keyword);
    }


    private String getString(Model model, String keyword) {
        List<Carbrand> carbrands = carbrandService.getAllCarbrands(keyword);
        List<Dealershop> dealershops = dealershopService.getAllDealershops(keyword);
        List<CarbrandPlan> carbrandPlans = carbrandPlanService.getAllCarbrands(keyword);

        model.addAttribute("carbrands", carbrands);
        model.addAttribute("dealershops", dealershops);
        model.addAttribute("carbrandPlans", carbrandPlans);
        return "index";
    }

    @PostMapping("/save")
    public String saveCarbrand(@ModelAttribute Carbrand carbrand,
                               @RequestParam Long dealershopId,
                               @RequestParam Long carbrandPlanId) {
        carbrandService.saveCarbrand(carbrand, dealershopId, carbrandPlanId);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editCarbrand(@PathVariable("id") long id, Model model) {
        Optional<Carbrand> carbrand = carbrandService.getCarbrandById(id);
        carbrand.ifPresent(value -> model.addAttribute("carbrand", value));
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCarbrand(@PathVariable Long id, @AuthenticationPrincipal User user) {
        carbrandService.deleteCarbrand(id, user);
        return "redirect:/";
    }
}
