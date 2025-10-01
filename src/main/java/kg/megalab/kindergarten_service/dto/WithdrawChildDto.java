package kg.megalab.kindergarten_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WithdrawChildDto {

    private LocalDate endDate;
}
