package kg.megalab.kindergarten_service.service;

import jakarta.validation.Valid;
import kg.megalab.kindergarten_service.dto.GroupsDto;
import kg.megalab.kindergarten_service.model.Groups;

import java.util.Map;

public interface GroupService {
    Groups create(@Valid GroupsDto groupsDto);

    Groups update(Long id, @Valid GroupsDto groupsDto);

    Groups delete(Long id);

    Groups getById(Long id);

    Map<String, Object> getAll(int page, int size);
}
