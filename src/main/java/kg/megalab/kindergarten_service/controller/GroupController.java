package kg.megalab.kindergarten_service.controller;

import jakarta.validation.Valid;
import kg.megalab.kindergarten_service.dto.GroupCategoriesDto;
import kg.megalab.kindergarten_service.dto.GroupsDto;
import kg.megalab.kindergarten_service.mapper.GroupMapper;
import kg.megalab.kindergarten_service.model.GroupCategories;
import kg.megalab.kindergarten_service.model.Groups;
import kg.megalab.kindergarten_service.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @PostMapping
    public ResponseEntity<GroupsDto> createGroup(
            @Valid @RequestBody GroupsDto groupsDto) {
        Groups saved = groupService.create(groupsDto);
        GroupsDto responseDto = groupMapper.toDto(saved);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupsDto> updateGroup(@PathVariable Long id,
                                                                  @Valid @RequestBody GroupsDto groupsDto){
        Groups updated = groupService.update(id, groupsDto);
        GroupsDto responseDto = groupMapper.toDto(updated);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GroupsDto> deleteGroup(@PathVariable Long id){
        Groups deleteGroup = groupService.delete(id);
        GroupsDto responseDto = groupMapper.toDto(deleteGroup);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupsDto> getGroup(@PathVariable Long id){
        Groups getGroup = groupService.getById(id);
        GroupsDto responseDto = groupMapper.toDto(getGroup);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllGroups(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(groupService.getAll(page, size));
    }
}
