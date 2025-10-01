package kg.megalab.kindergarten_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GroupChildrensDto {

    @NotNull(message = "ID ребёнка обязателен")
    private Long childrenId;

    @NotNull(message = "ID группы обязателен")
    private Long groupId;

    @NotNull(message = "Дата начала обязательна")
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "Цена обязательна")
    private Double price;
}
