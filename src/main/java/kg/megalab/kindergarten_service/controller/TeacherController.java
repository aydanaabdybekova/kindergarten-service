package kg.megalab.kindergarten_service.controller;

import jakarta.validation.Valid;
import kg.megalab.kindergarten_service.dto.GroupCategoriesDto;
import kg.megalab.kindergarten_service.dto.TeachersDto;
import kg.megalab.kindergarten_service.mapper.TeacherMapper;
import kg.megalab.kindergarten_service.model.GroupCategories;
import kg.megalab.kindergarten_service.model.Teachers;
import kg.megalab.kindergarten_service.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @PostMapping
    public ResponseEntity<TeachersDto> createTeacher(@Valid @RequestBody TeachersDto teachersDto){
        Teachers saved = teacherService.create(teachersDto);
        TeachersDto responseDto = teacherMapper.toDto(saved);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeachersDto> updateTeachers(@PathVariable Long id,
                                                                  @Valid @RequestBody TeachersDto teachersDto){
        Teachers updatedTeachers = teacherService.update(id, teachersDto);
        TeachersDto responseDto = teacherMapper.toDto(updatedTeachers);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeachersDto> deleteTeachers(@PathVariable Long id){
        Teachers deleteTeachers = teacherService.delete(id);
        TeachersDto responseDto = teacherMapper.toDto(deleteTeachers);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeachersDto> getTeachers(@PathVariable Long id){
        Teachers getTeachers = teacherService.getById(id);
        TeachersDto responseDto = teacherMapper.toDto(getTeachers);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTeachers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(teacherService.getAll(page, size));
    }

}
