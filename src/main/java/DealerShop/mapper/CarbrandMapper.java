package DealerShop.mapper;

import DealerShop.DTO.CarbrandDTO;
import DealerShop.model.Carbrand;

public class CarbrandMapper {

    public static CarbrandDTO toDTO(Carbrand carbrand) {
        CarbrandDTO dto = new CarbrandDTO();
        dto.setId(carbrand.getId());
        dto.setKey(carbrand.getFio());
        dto.setStartDate(carbrand.getStart_date());
        dto.setEndDate(carbrand.getEnd_date());
        dto.setDealershopId(carbrand.getDealershop() != null ? carbrand.getDealershop().getId() : null);
        dto.setCarbrandPlanId(carbrand.getCarbrandPlan() != null ? carbrand.getCarbrandPlan().getId() : null);
        return dto;
    }

    public static Carbrand toEntity(CarbrandDTO dto) {
        Carbrand carbrand = new Carbrand();
        carbrand.setId(dto.getId());
        carbrand.setFio(dto.getKey());
        carbrand.setStart_date(dto.getStartDate());
        carbrand.setEnd_date(dto.getEndDate());
        return carbrand;
    }
}
