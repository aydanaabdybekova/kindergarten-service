package kg.megalab.kindergarten_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupCategoriesDto {

    private Long id;
    @NotBlank(message = "Название категории не может быть пустым")
    private String name;
    @NotNull(message = "Активность обязательна")
    private Boolean active = true;
    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена должна быть положительной")
    private double price;
}
