package kg.megalab.kindergarten_service.controller;

import jakarta.validation.Valid;
import kg.megalab.kindergarten_service.dto.GroupCategoriesDto;
import kg.megalab.kindergarten_service.mapper.GroupCategoryMapper;
import kg.megalab.kindergarten_service.model.GroupCategories;
import kg.megalab.kindergarten_service.service.GroupCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group-category")
@RequiredArgsConstructor
public class GroupCategoryController {

    private final GroupCategoryService groupCategoryService;
    private final GroupCategoryMapper groupCategoryMapper;

    @PostMapping
    public ResponseEntity<GroupCategoriesDto> createGroupCategory(
            @Valid @RequestBody GroupCategoriesDto groupCategoriesDto) {
        GroupCategories saved = groupCategoryService.create(groupCategoriesDto);
        GroupCategoriesDto responseDto = groupCategoryMapper.toDto(saved);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupCategoriesDto> updateGroupCategory(@PathVariable Long id,
                                                                  @Valid @RequestBody GroupCategoriesDto groupCategoriesDto){
        GroupCategories updatedCategory = groupCategoryService.update(id, groupCategoriesDto);
        GroupCategoriesDto responseDto = groupCategoryMapper.toDto(updatedCategory);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GroupCategoriesDto> deleteGroupCategory(@PathVariable Long id){
        GroupCategories deleteCategory = groupCategoryService.delete(id);
        GroupCategoriesDto responseDto = groupCategoryMapper.toDto(deleteCategory);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupCategoriesDto> getGroupCategory(@PathVariable Long id){
        GroupCategories getCategory = groupCategoryService.getById(id);
        GroupCategoriesDto responseDto = groupCategoryMapper.toDto(getCategory);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllGroupCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(groupCategoryService.getAll(page, size));
    }
}
