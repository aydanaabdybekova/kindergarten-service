package kg.megalab.kindergarten_service.service;

import jakarta.validation.Valid;
import kg.megalab.kindergarten_service.dto.GroupCategoriesDto;
import kg.megalab.kindergarten_service.model.GroupCategories;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface GroupCategoryService {
    GroupCategories create(@Valid GroupCategoriesDto groupCategoriesDto);

    GroupCategories update(Long id, @Valid GroupCategoriesDto groupCategoriesDto);

    GroupCategories delete(Long id);

    GroupCategories getById(Long id);

    Map<String, Object> getAll(int page, int size);
}
