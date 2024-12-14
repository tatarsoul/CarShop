package DealerShop.controller;

import DealerShop.model.CarbrandPlan;
import DealerShop.service.CarbrandPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CarbrandPlanController {
    private final CarbrandPlanService carbrandPlanService;

    public CarbrandPlanController(CarbrandPlanService carbrandPlanService) {
        this.carbrandPlanService = carbrandPlanService;
    }

    @RequestMapping("/carbrand_plans")
    public String viewAllCarbrandPlans(@RequestParam(required = false) String keyword, Model model) {
        List<CarbrandPlan> carbrandPlans = carbrandPlanService.getAllCarbrands(keyword);
        model.addAttribute("carbrandPlans", carbrandPlans);
        model.addAttribute("carbrandPlan", new CarbrandPlan());
        return "carbrandPlans";
    }

    @PostMapping("/carbrand_plans/save")
    public String saveCarbrandPlan(@ModelAttribute("carbrandPlan") CarbrandPlan carbrandPlan) {
        if (carbrandPlan.getId() != null && carbrandPlan.getId() > 0) {
            carbrandPlanService.saveCarbrandPlan(carbrandPlan);
        } else {
            carbrandPlanService.saveCarbrandPlan(carbrandPlan);
        }
        return "redirect:/carbrand_plans";
    }

    @GetMapping("/carbrand_plans/edit/{id}")
    public String editCarbrandPlan(@PathVariable("id") long id, Model model) {
        Optional<CarbrandPlan> carbrandPlan = carbrandPlanService.getCarbrandPlanById(id);
        carbrandPlan.ifPresent(plan -> model.addAttribute("carbrandPlan", plan));
        return "carbrandPlans";
    }

    @GetMapping("/carbrand_plans/delete/{id}")
    public String deleteCarbrandPlan(@PathVariable Long id) {
        carbrandPlanService.deleteById(id);
        return "redirect:/carbrand_plans";
    }
}
