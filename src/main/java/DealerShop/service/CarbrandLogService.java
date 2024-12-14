package DealerShop.service;

import DealerShop.config.CurrentUserProvider;
import DealerShop.model.Carbrand;
import DealerShop.model.CarbrandLog;
import DealerShop.model.User;
import DealerShop.repository.CarbrandLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CarbrandLogService {

    @Autowired
    private CarbrandLogRepository carbrandLogRepository;

    public List<CarbrandLog> getAllCarbrandLogs(String keyword) {
        System.out.println("Вызов getAllCarbrands с параметром: " + keyword);
        if (keyword == null || keyword.isEmpty()) {
            return carbrandLogRepository.findAll();
        }
        return carbrandLogRepository.search(keyword);
    }

    // Создать лог
    public void createLog(String changeType, Carbrand oldCarbrand, Carbrand newCarbrand) {
        User currentUser = CurrentUserProvider.getCurrentUser();
        CarbrandLog log = new CarbrandLog();
        log.setChange_type(changeType);
        log.setChange_date(new Timestamp(System.currentTimeMillis()));
        log.setUser(currentUser);
        log.setCarbrand(newCarbrand != null ? newCarbrand : oldCarbrand);

        if (oldCarbrand != null) {
            log.setOld_value(oldCarbrand.toString()); // Метод toString() должен быть переопределен в Carbrand
        }

        if (newCarbrand != null) {
            log.setNew_value(newCarbrand.toString());
        }

        carbrandLogRepository.save(log);
    }
}
