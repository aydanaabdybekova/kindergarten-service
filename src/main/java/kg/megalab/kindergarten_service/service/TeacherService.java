package kg.megalab.kindergarten_service.service;

import jakarta.validation.Valid;
import kg.megalab.kindergarten_service.dto.TeachersDto;
import kg.megalab.kindergarten_service.model.GroupCategories;
import kg.megalab.kindergarten_service.model.Teachers;

import java.util.Map;

public interface TeacherService {

    Teachers create(@Valid TeachersDto teachersDto);

    Teachers update(Long id, @Valid TeachersDto teachersDto);

    Teachers delete(Long id);

    Teachers getById(Long id);

    Map<String, Object> getAll(int page, int size);
}
