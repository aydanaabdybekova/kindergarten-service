package kg.megalab.kindergarten_service.repository;

import jakarta.validation.constraints.NotBlank;
import kg.megalab.kindergarten_service.model.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teachers, Long>{
     boolean existsByFirstNameAndLastName(@NotBlank(message = "Имя обязательно") String firstName, @NotBlank(message = "Фамилия обязательна") String lastName);
}
