package kg.megalab.kindergarten_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.megalab.kindergarten_service.enums.TeacherDegree;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeachersDto {

    @NotBlank(message = "Имя обязательно")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    private String patronymic;

    @NotBlank(message = "Степень/роль обязательна")
    private TeacherDegree teacherDegree;

    @NotNull(message = "Статус обязателен")
    private Boolean active;

}
