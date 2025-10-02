package kg.megalab.kindergarten_service.controller;

import jakarta.validation.Valid;
import kg.megalab.kindergarten_service.dto.EnrollChildDto;
import kg.megalab.kindergarten_service.dto.GroupChildrensDto;
import kg.megalab.kindergarten_service.dto.WithdrawChildDto;
import kg.megalab.kindergarten_service.mapper.GroupChildrenMapper;
import kg.megalab.kindergarten_service.model.GroupChildrens;
import kg.megalab.kindergarten_service.service.GroupChildrenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group-children")
@RequiredArgsConstructor
public class GroupChildrenController {

    private final GroupChildrenService groupChildrenService;
    private final GroupChildrenMapper groupChildrenMapper;

    @PostMapping
    public ResponseEntity<GroupChildrensDto> enrollChild(@Valid @RequestBody EnrollChildDto dto) {
        GroupChildrens saved = groupChildrenService.enrollChild(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupChildrenMapper.toDto(saved));
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<GroupChildrensDto> withdrawChild(
            @PathVariable Long id,
            @RequestBody(required = false) WithdrawChildDto withdrawChildDto) {

        GroupChildrensDto updatedChild = groupChildrenService.withdrawChild(id, withdrawChildDto);
        return ResponseEntity.ok(updatedChild);
    }
}
