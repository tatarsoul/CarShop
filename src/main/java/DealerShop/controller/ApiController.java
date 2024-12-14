package DealerShop.controller;

import DealerShop.DTO.DealershopDTO;
import DealerShop.DTO.CarbrandDTO;
import DealerShop.DTO.CarbrandPlanDTO;
import DealerShop.mapper.DealershopMapper;
import DealerShop.mapper.CarbrandMapper;
import DealerShop.mapper.CarbrandPlanMapper;
import DealerShop.model.Dealershop;
import DealerShop.model.Carbrand;
import DealerShop.model.CarbrandPlan;
import DealerShop.repository.DealershopRepository;
import DealerShop.repository.CarbrandPlanRepository;
import DealerShop.repository.CarbrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final CarbrandRepository carbrandRepository;
    private final DealershopRepository dealershopRepository;
    private final CarbrandPlanRepository carbrandPlanRepository;

    @GetMapping("/carbrands")
    public ResponseEntity<List<CarbrandDTO>> getAllCarbrands() {
        List<Carbrand> carbrands = carbrandRepository.findAll();
        List<CarbrandDTO> carbrandDTOs = carbrands.stream()
                .map(CarbrandMapper::toDTO)
                .toList();
        return ResponseEntity.ok(carbrandDTOs);
    }

    @GetMapping("/carbrands/{id}")
    public ResponseEntity<CarbrandDTO> getCarbrandById(@PathVariable Long id) {
        return carbrandRepository.findById(id)
                .map(CarbrandMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/carbrands")
    public ResponseEntity<CarbrandDTO> createCarbrand(@RequestBody CarbrandDTO carbrandDTO) {
        Carbrand carbrand = CarbrandMapper.toEntity(carbrandDTO);

        carbrand.setDealershop(carbrandDTO.getDealershopId() != null
                ? dealershopRepository.findById(carbrandDTO.getDealershopId()).orElse(null)
                : null);
        carbrand.setCarbrandPlan(carbrandDTO.getCarbrandPlanId() != null
                ? carbrandPlanRepository.findById(carbrandDTO.getCarbrandPlanId()).orElse(null)
                : null);
        Carbrand savedCarbrand = carbrandRepository.save(carbrand);
        return ResponseEntity.ok(CarbrandMapper.toDTO(savedCarbrand));
    }

    @PutMapping("/carbrands/{id}")
    public ResponseEntity<CarbrandDTO> updateCarbrand(@PathVariable Long id, @RequestBody CarbrandDTO carbrandDTO) {
        return carbrandRepository.findById(id)
                .map(existingCarbrand -> {
                    if (carbrandDTO.getKey() != null) existingCarbrand.setFio(carbrandDTO.getKey());
                    if (carbrandDTO.getStartDate() != null) existingCarbrand.setStart_date(carbrandDTO.getStartDate());
                    if (carbrandDTO.getEndDate() != null) existingCarbrand.setEnd_date(carbrandDTO.getEndDate());
                    if (carbrandDTO.getDealershopId() != null) {
                        existingCarbrand.setDealershop(dealershopRepository.findById(carbrandDTO.getDealershopId()).orElse(null));
                    }
                    if (carbrandDTO.getCarbrandPlanId() != null) {
                        existingCarbrand.setCarbrandPlan(carbrandPlanRepository.findById(carbrandDTO.getCarbrandPlanId()).orElse(null));
                    }
                    return carbrandRepository.save(existingCarbrand);
                })
                .map(CarbrandMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/carbrands/{id}")
    public ResponseEntity<Void> deleteCarbrand(@PathVariable Long id) {
        if (carbrandRepository.existsById(id)) {
            carbrandRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // === Методы для Dealershop ===

    @GetMapping("/dealershops")
    public ResponseEntity<List<DealershopDTO>> getAllDealershops() {
        List<Dealershop> dealershops = dealershopRepository.findAll();
        List<DealershopDTO> dealershopDTOs = dealershops.stream()
                .map(DealershopMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dealershopDTOs);
    }

    @GetMapping("/dealershops/{id}")
    public ResponseEntity<DealershopDTO> getDealershopById(@PathVariable Long id) {
        return dealershopRepository.findById(id)
                .map(DealershopMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/dealershops")
    public ResponseEntity<DealershopDTO> createDealershop(@RequestBody DealershopDTO dealershopDTO) {
        Dealershop dealershop = DealershopMapper.toEntity(dealershopDTO);
        Dealershop savedDealershop = dealershopRepository.save(dealershop);
        return ResponseEntity.ok(DealershopMapper.toDTO(savedDealershop));
    }

    @PutMapping("/dealershops/{id}")
    public ResponseEntity<DealershopDTO> updateDealershop(@PathVariable Long id, @RequestBody DealershopDTO dealershopDTO) {
        return dealershopRepository.findById(id)
                .map(existingDealershop -> {
                    if (dealershopDTO.getName() != null) existingDealershop.setName(dealershopDTO.getName());
                    if (dealershopDTO.getAddress() != null) existingDealershop.setAddress(dealershopDTO.getAddress());
                    if (dealershopDTO.getContact() != null) existingDealershop.setContact(dealershopDTO.getContact());
                    if (dealershopDTO.getDescription() != null) existingDealershop.setDescr(dealershopDTO.getDescription());
                    return dealershopRepository.save(existingDealershop);
                })
                .map(DealershopMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/dealershops/{id}")
    public ResponseEntity<Void> deleteDealershop(@PathVariable Long id) {
        if (dealershopRepository.existsById(id)) {
            dealershopRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // === Методы для CarbrandPlan ===

    @GetMapping("/carbrand-plans")
    public ResponseEntity<List<CarbrandPlanDTO>> getAllCarbrandPlans() {
        List<CarbrandPlan> carbrandPlans = carbrandPlanRepository.findAll();
        List<CarbrandPlanDTO> carbrandPlanDTOs = carbrandPlans.stream()
                .map(CarbrandPlanMapper::toDTO)
                .toList();
        return ResponseEntity.ok(carbrandPlanDTOs);
    }

    @GetMapping("/carbrand-plans/{id}")
    public ResponseEntity<CarbrandPlanDTO> getCarbrandPlanById(@PathVariable Long id) {
        return carbrandPlanRepository.findById(id)
                .map(CarbrandPlanMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/carbrand-plans")
    public ResponseEntity<CarbrandPlanDTO> createCarbrandPlan(@RequestBody CarbrandPlanDTO carbrandPlanDTO) {
        CarbrandPlan carbrandPlan = CarbrandPlanMapper.toEntity(carbrandPlanDTO);
        CarbrandPlan savedCarbrandPlan = carbrandPlanRepository.save(carbrandPlan);
        return ResponseEntity.ok(CarbrandPlanMapper.toDTO(savedCarbrandPlan));
    }

    @PutMapping("/carbrand-plans/{id}")
    public ResponseEntity<CarbrandPlanDTO> updateCarbrandPlan(@PathVariable Long id, @RequestBody CarbrandPlanDTO carbrandPlanDTO) {
        return carbrandPlanRepository.findById(id)
                .map(existingPlan -> {
                    if (carbrandPlanDTO.getName() != null) existingPlan.setName(carbrandPlanDTO.getName());
                    if (carbrandPlanDTO.getMaxUsers() != null) existingPlan.setMax_users(carbrandPlanDTO.getMaxUsers());
                    if (carbrandPlanDTO.getPrice() != null) existingPlan.setPrice(carbrandPlanDTO.getPrice());
                    return carbrandPlanRepository.save(existingPlan);
                })
                .map(CarbrandPlanMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/carbrand-plans/{id}")
    public ResponseEntity<Void> deleteCarbrandPlan(@PathVariable Long id) {
        if (carbrandPlanRepository.existsById(id)) {
            carbrandPlanRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
