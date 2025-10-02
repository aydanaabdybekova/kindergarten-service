package kg.megalab.kindergarten_service.service.impl;

import kg.megalab.kindergarten_service.dto.GroupCategoriesDto;
import kg.megalab.kindergarten_service.dto.GroupsDto;
import kg.megalab.kindergarten_service.dto.TeachersDto;
import kg.megalab.kindergarten_service.enums.TeacherDegree;
import kg.megalab.kindergarten_service.mapper.GroupMapper;
import kg.megalab.kindergarten_service.model.GroupCategories;
import kg.megalab.kindergarten_service.model.Groups;
import kg.megalab.kindergarten_service.model.Teachers;
import kg.megalab.kindergarten_service.repository.GroupCategoryRepo;
import kg.megalab.kindergarten_service.repository.GroupRepo;
import kg.megalab.kindergarten_service.repository.TeacherRepo;
import kg.megalab.kindergarten_service.service.GroupService;
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
public class GroupServiceImpl implements GroupService {

    private final GroupRepo groupRepo;
    private final GroupMapper groupMapper;
    private final TeacherRepo teacherRepo;
    private final GroupCategoryRepo groupCategoryRepo;


    @Override
    public Groups create(GroupsDto groupsDto) {
        if (groupRepo.existsByName(groupsDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Группа с таким названием уже существует");
        }
        Teachers nanny = teacherRepo.findById(groupsDto.getNannyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Няня не найдена"));

        Teachers teacher = teacherRepo.findById(groupsDto.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Учитель не найден"));

        GroupCategories category = groupCategoryRepo.findById(groupsDto.getGroupCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория не найдена"));

        Groups groups = groupMapper.toEntity(groupsDto);
        groups.setNanny(nanny);
        groups.setTeacher(teacher);
        groups.setGroupCategory(category);

        return groupRepo.save(groups);
    }

    @Override
    public Groups update(Long id, GroupsDto groupsDto) {
        Groups groups = groupRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Группа не найдена"));

        if (groupsDto.getName() == null || groupsDto.getName().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Название категории не может быть пустым");
        }

        Teachers nanny = teacherRepo.findById(groupsDto.getNannyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Няня не найдена"));

        Teachers teacher = teacherRepo.findById(groupsDto.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Учитель не найден"));

        if (nanny.getTeacherDegree() != TeacherDegree.NANNY) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Указанный id не является няней");
        }

        if (teacher.getTeacherDegree() != TeacherDegree.TEACHER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Указанный id не является учителем");
        }

        GroupCategories category = groupCategoryRepo.findById(groupsDto.getGroupCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория не найдена"));

        groups.setNanny(nanny);
        groups.setTeacher(teacher);
        groups.setGroupCategory(category);

        groupMapper.updateEntityFromDto(groupsDto, groups);
        return groupRepo.save(groups);
    }

    @Override
    public Groups delete(Long id) {
        Groups groups = groupRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Группа не найдена"));

        groupRepo.delete(groups);
        return groups;
    }

    @Override
    public Groups getById(Long id) {
        return groupRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Группа не найдена"));
    }

    @Override
    public Map<String, Object> getAll(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверные параметры пагинации");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        Page<Groups> groupsPage = groupRepo.findAll(pageable);
        List<GroupsDto> dtoList = groupsPage.getContent()
                .stream()
                .map(groupMapper::toDto)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("groups", dtoList);
        response.put("currentPage", groupsPage.getNumber());
        response.put("totalItems", groupsPage.getTotalElements());
        response.put("totalPages", groupsPage.getTotalPages());

        return response;
    }
}
