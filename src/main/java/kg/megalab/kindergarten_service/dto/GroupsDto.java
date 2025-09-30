package kg.megalab.kindergarten_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupsDto {

    @NotBlank(message = "Название группы обязательно")
    private String name;

    @NotNull(message = "Макс. количество детей обязательно")
    @Positive(message = "Количество должно быть положительным")
    private Integer maxChildrenCount;

    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена должна быть положительной")
    private double price;

    @NotNull(message = "Няня обязательна")
    private Long nannyId;

    @NotNull(message = "Категория группы обязательна")
    private Long groupCategoryId;

    @NotNull(message = "Учитель обязателен")
    private Long teacherId;

}
