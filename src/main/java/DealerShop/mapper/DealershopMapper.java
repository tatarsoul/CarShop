package DealerShop.mapper;

import DealerShop.DTO.DealershopDTO;
import DealerShop.model.Dealershop;

public class DealershopMapper {

    public static DealershopDTO toDTO(Dealershop dealershop) {
        DealershopDTO dto = new DealershopDTO();
        dto.setId(dealershop.getId());
        dto.setName(dealershop.getName());
        dto.setAddress(dealershop.getAddress());
        dto.setContact(dealershop.getContact());
        dto.setDescription(dealershop.getDescr());
        return dto;
    }

    public static Dealershop toEntity(DealershopDTO dto) {
        Dealershop dealershop = new Dealershop();
        dealershop.setId(dto.getId());
        dealershop.setName(dto.getName());
        dealershop.setAddress(dto.getAddress());
        dealershop.setContact(dto.getContact());
        dealershop.setDescr(dto.getDescription());
        return dealershop;
    }
}
