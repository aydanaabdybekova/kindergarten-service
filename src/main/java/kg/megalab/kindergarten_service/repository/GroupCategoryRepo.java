package kg.megalab.kindergarten_service.repository;

import jakarta.validation.constraints.NotBlank;
import kg.megalab.kindergarten_service.model.GroupCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface GroupCategoryRepo extends JpaRepository<GroupCategories, Long> {

    boolean existsByName(@NotBlank(message = "Название категории не может быть пустым") String name);
}
