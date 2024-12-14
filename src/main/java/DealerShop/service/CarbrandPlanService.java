package DealerShop.service;

import DealerShop.model.CarbrandPlan;
import DealerShop.repository.CarbrandPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarbrandPlanService {
    public final CarbrandPlanRepository carbrandPlanRepository;

    public List<CarbrandPlan> getAllCarbrands(String keyword) {
        System.out.println("Вызов getAllCarbrands с параметром: " + keyword);
        if (keyword == null || keyword.isEmpty()) {
            return carbrandPlanRepository.findAll();
        }
        return carbrandPlanRepository.search(keyword);
    }

    public Optional<CarbrandPlan> getCarbrandPlanById(Long id) {
        return carbrandPlanRepository.findById(id);
    }

    public void saveCarbrandPlan(CarbrandPlan carbrandPlan) {
        if (carbrandPlan.getId() != null && carbrandPlan.getId() > 0) {
            carbrandPlanRepository.save(carbrandPlan);
        } else {
            carbrandPlanRepository.save(carbrandPlan);
        }
    }

    public void deleteById(Long id) {
        carbrandPlanRepository.deleteById(id);
    }
}
