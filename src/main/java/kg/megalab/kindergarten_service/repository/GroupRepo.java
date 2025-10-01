package kg.megalab.kindergarten_service.repository;

import jakarta.validation.constraints.NotBlank;
import kg.megalab.kindergarten_service.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Groups, Long> {
    boolean existsByName(@NotBlank(message = "Название группы обязательно") String name);
}
