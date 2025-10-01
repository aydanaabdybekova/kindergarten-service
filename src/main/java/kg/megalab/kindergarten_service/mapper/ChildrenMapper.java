package kg.megalab.kindergarten_service.mapper;

import kg.megalab.kindergarten_service.dto.ChildrensDto;
import kg.megalab.kindergarten_service.model.Childrens;
import org.springframework.stereotype.Component;

@Component
public class ChildrenMapper {

    public Childrens toEntity(ChildrensDto dto) {
        Childrens entity = new Childrens();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPatronymic(dto.getPatronymic());
        entity.setDateOfBirth(dto.getDateOfBirth());
        return entity;
    }

    public ChildrensDto toDto(Childrens entity) {
        ChildrensDto dto = new ChildrensDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPatronymic(entity.getPatronymic());
        dto.setDateOfBirth(entity.getDateOfBirth());
        return dto;
    }
}
