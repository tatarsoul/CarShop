package DealerShop.mapper;

import DealerShop.DTO.CarbrandPlanDTO;
import DealerShop.model.CarbrandPlan;

public class CarbrandPlanMapper {

    public static CarbrandPlanDTO toDTO(CarbrandPlan carbrandPlan) {
        CarbrandPlanDTO dto = new CarbrandPlanDTO();
        dto.setId(carbrandPlan.getId());
        dto.setName(carbrandPlan.getName());
        dto.setMaxUsers(carbrandPlan.getMax_users());
        dto.setPrice(carbrandPlan.getPrice());
        return dto;
    }

    public static CarbrandPlan toEntity(CarbrandPlanDTO dto) {
        CarbrandPlan carbrandPlan = new CarbrandPlan();
        carbrandPlan.setId(dto.getId());
        carbrandPlan.setName(dto.getName());
        carbrandPlan.setMax_users(dto.getMaxUsers());
        carbrandPlan.setPrice(dto.getPrice());
        return carbrandPlan;
    }
}
