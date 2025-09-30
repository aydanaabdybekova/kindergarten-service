package kg.megalab.kindergarten_service.service.impl;

import kg.megalab.kindergarten_service.dto.GroupCategoriesDto;
import kg.megalab.kindergarten_service.dto.TeachersDto;
import kg.megalab.kindergarten_service.mapper.TeacherMapper;
import kg.megalab.kindergarten_service.model.GroupCategories;
import kg.megalab.kindergarten_service.model.Teachers;
import kg.megalab.kindergarten_service.repository.TeacherRepo;
import kg.megalab.kindergarten_service.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;
    private final TeacherRepo teacherRepo;

    @Override
    public Teachers create(TeachersDto teachersDto) {
        if (teacherRepo.existsByFirstNameAndLastName(teachersDto.getFirstName(), teachersDto.getLastName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Преподаватель с таким именем уже существует"
            );
        }
        Teachers teachers = teacherMapper.toEntity(teachersDto);
        return teacherRepo.save(teachers);
    }

    @Override
    public Teachers update(Long id, TeachersDto teachersDto) {
        Teachers teachers = teacherRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория не найдена"));

        teacherMapper.updateEntityFromDto(teachersDto, teachers);
        return teacherRepo.save(teachers);
    }

    @Override
    public Teachers delete(Long id) {
        Teachers teachers = teacherRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория не найдена"));

        teacherRepo.delete(teachers);
        return teachers;
    }

    @Override
    public Teachers getById(Long id) {
        return teacherRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория не найдена"));

    }

    @Override
    public Map<String, Object> getAll(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверные параметры пагинации");
        }
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("teacherDegree").ascending()
                        .and(Sort.by("lastName").ascending())
                        .and(Sort.by("firstName").ascending())
        );

        Page<Teachers> teachersPage = teacherRepo.findAll(pageable);
        List<TeachersDto> dtoList = teachersPage.getContent()
                .stream()
                .map(teacherMapper::toDto)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("teachers", dtoList);
        response.put("currentPage", teachersPage.getNumber());
        response.put("totalItems", teachersPage.getTotalElements());
        response.put("totalPages", teachersPage.getTotalPages());
        return response;
    }
}
