package kg.megalab.kindergarten_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PaymentsDto {

    @NotNull(message = "ID записи ребенка в группе обязателен")
    private Long groupChildrenId;

    @NotNull(message = "Сумма платежа обязательна")
    @Positive(message = "Сумма должна быть положительной")
    private Double amount;

    @NotNull(message = "Дата платежа обязательна")
    private LocalDate paymentDate;

}
