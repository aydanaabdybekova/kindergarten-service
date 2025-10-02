package kg.megalab.kindergarten_service.service;

import jakarta.validation.Valid;
import kg.megalab.kindergarten_service.dto.EnrollChildDto;
import kg.megalab.kindergarten_service.dto.GroupChildrensDto;
import kg.megalab.kindergarten_service.dto.WithdrawChildDto;
import kg.megalab.kindergarten_service.model.GroupChildrens;

public interface GroupChildrenService {
    GroupChildrens enrollChild(@Valid EnrollChildDto dto);

    GroupChildrensDto withdrawChild(Long id, WithdrawChildDto withdrawChildDto);
}
