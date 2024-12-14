package DealerShop.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CarbrandDTO {
    private Long id;
    private String key;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long dealershopId;     // ID дилерского центра
    private Long carbrandPlanId;   // ID плана по марке
}
