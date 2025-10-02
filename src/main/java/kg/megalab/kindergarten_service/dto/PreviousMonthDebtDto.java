package kg.megalab.kindergarten_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PreviousMonthDebtDto {

    private Long childId;
    private Integer amountDue;

}
