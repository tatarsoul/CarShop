package DealerShop.controller;

import DealerShop.model.Dealershop;
import DealerShop.service.DealershopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DealershopController {

    private final DealershopService dealershopService;

    public DealershopController(DealershopService dealershopService) {
        this.dealershopService = dealershopService;
    }

    @RequestMapping("/dealershops")
    public String viewAllDealershops(@RequestParam(required = false) String keyword, Model model) {
        List<Dealershop> dealershops = dealershopService.getAllDealershops(keyword);
        model.addAttribute("dealershops", dealershops);
        model.addAttribute("dealershop", new Dealershop());
        return "dealershops";
    }

    @PostMapping("/dealershops/save")
    public String saveDealershop(@ModelAttribute("dealershop") Dealershop dealershop) {
        if (dealershop.getId() != null && dealershop.getId() > 0) {
            dealershopService.saveDealershop(dealershop);
        } else {
            dealershopService.saveDealershop(dealershop);
        }
        return "redirect:/dealershops";
    }

    @GetMapping("/dealershops/edit/{id}")
    public String editDealershop(@PathVariable("id") long id, Model model) {
        Optional<Dealershop> dealershop = dealershopService.getDealershopById(id);
        dealershop.ifPresent(value -> model.addAttribute("dealershop", value));
        return "dealershops";
    }

    @GetMapping("/dealershops/delete/{id}")
    public String deleteDealershop(@PathVariable long id) {
        dealershopService.deleteDealershop(id);
        return "redirect:/dealershops";
    }
}
