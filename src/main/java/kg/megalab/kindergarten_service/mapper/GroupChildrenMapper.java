package kg.megalab.kindergarten_service.mapper;

import kg.megalab.kindergarten_service.dto.GroupChildrensDto;
import kg.megalab.kindergarten_service.model.Childrens;
import kg.megalab.kindergarten_service.model.GroupChildrens;
import kg.megalab.kindergarten_service.model.Groups;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class GroupChildrenMapper {

    public GroupChildrens toEntity(GroupChildrensDto dto, Childrens child, Groups group) {
        GroupChildrens entity = new GroupChildrens();
        entity.setChildren(child);
        entity.setGroup(group);
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public GroupChildrensDto toDto(GroupChildrens entity) {
        GroupChildrensDto dto = new GroupChildrensDto();
        dto.setChildrenId(entity.getChildren().getId());
        dto.setGroupId(entity.getGroup().getId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setPrice(entity.getPrice());
        return dto;
    }
}
