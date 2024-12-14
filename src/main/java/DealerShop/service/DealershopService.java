package DealerShop.service;

import DealerShop.model.Dealershop;
import DealerShop.repository.DealershopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DealershopService {

    @Autowired
    private DealershopRepository dealershopRepository;

    public List<Dealershop> getAllDealershops(String keyword) {
        System.out.println("Вызов getAllDealershops с параметром: " + keyword);
        if (keyword == null || keyword.isEmpty()) {
            return dealershopRepository.findAll();
        }
        return dealershopRepository.search(keyword);
    }

    public Optional<Dealershop> getDealershopById(Long id) {
        return dealershopRepository.findById(id);
    }

    public void saveDealershop(Dealershop dealershop) {
        if (dealershop.getId() != null && dealershop.getId() > 0) {
            dealershopRepository.save(dealershop);
        } else {
            dealershopRepository.save(dealershop);
        }
    }

    @Transactional
    public void deleteDealershop(Long id) {
        dealershopRepository.deleteById(id);
    }
}
