package DealerShop.service;

import DealerShop.model.Carbrand;
import DealerShop.model.CarbrandPlan;
import DealerShop.model.Dealershop;
import DealerShop.model.User;
import DealerShop.repository.CarbrandPlanRepository;
import DealerShop.repository.CarbrandRepository;
import DealerShop.repository.DealershopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarbrandService {
    private final CarbrandRepository carbrandRepository;
    private final CarbrandPlanRepository carbrandPlanRepository;
    private final DealershopRepository dealershopRepository;
    private final CarbrandLogService carbrandLogService;

    public CarbrandService(CarbrandRepository carbrandRepository, CarbrandPlanRepository carbrandPlanRepository,
                           DealershopRepository dealershopRepository, CarbrandLogService carbrandLogService) {
        this.carbrandRepository = carbrandRepository;
        this.carbrandPlanRepository = carbrandPlanRepository;
        this.dealershopRepository = dealershopRepository;
        this.carbrandLogService = carbrandLogService;
    }

    public void saveCarbrand(Carbrand carbrand, Long dealershopId, Long carbrandPlanId) {
        Carbrand oldCarbrand = null;

        if (carbrand.getId() != null) {
            oldCarbrand = carbrandRepository.findById(carbrand.getId()).orElse(null);
        }
        System.out.println(carbrand.getId());

        // Установка связей
        if (dealershopId != null) {
            Dealershop dealershop = dealershopRepository.findById(dealershopId)
                    .orElseThrow(() -> new IllegalArgumentException("Дилерский центр с ID " + dealershopId + " не найден"));
            carbrand.setDealershop(dealershop);
        }

        if (carbrandPlanId != null) {
            CarbrandPlan carbrandPlan = carbrandPlanRepository.findById(carbrandPlanId)
                    .orElseThrow(() -> new IllegalArgumentException("План с ID " + carbrandPlanId + " не найден"));
            carbrand.setCarbrandPlan(carbrandPlan);
        }

        Carbrand savedCarbrand = carbrandRepository.save(carbrand);

        // Записываем лог
        if (oldCarbrand != null) {
            carbrandLogService.createLog("UPDATE", oldCarbrand, savedCarbrand);
        } else {
            carbrandLogService.createLog("CREATE", null, savedCarbrand);
        }
    }

    public List<Carbrand> getAllCarbrands(String keyword) {
        System.out.println("Вызов getAllCarbrands с параметром: " + keyword);
        if (keyword == null || keyword.isEmpty()) {
            return carbrandRepository.findAll();
        }
        return carbrandRepository.search(keyword);
    }

    public Optional<Carbrand> getCarbrandById(Long id) {
        return carbrandRepository.findById(id);
    }

    // Удалить carbrand
    public void deleteCarbrand(Long id, User user) {
        Carbrand carbrand = carbrandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Carbrand с ID " + id + " не найден"));

        carbrandLogService.createLog("DELETE", carbrand, null);
        carbrandRepository.deleteById(id);
    }
}
