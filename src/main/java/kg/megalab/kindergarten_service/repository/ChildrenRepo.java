package kg.megalab.kindergarten_service.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import kg.megalab.kindergarten_service.model.Childrens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ChildrenRepo extends JpaRepository<Childrens, Long> {
    <T> Optional<T> findByFirstNameAndLastNameAndDateOfBirth(@NotBlank(message = "Имя обязательно") @Size(max = 50, message = "Имя не должно превышать 50 символов") String firstName, @NotBlank(message = "Фамилия обязательна") @Size(max = 50, message = "Фамилия не должна превышать 50 символов") String lastName, @NotNull(message = "Дата рождения обязательна") @Past(message = "Дата рождения должна быть в прошлом") LocalDate dateOfBirth);
}
