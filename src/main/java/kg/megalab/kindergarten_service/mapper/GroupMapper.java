package kg.megalab.kindergarten_service.mapper;

import kg.megalab.kindergarten_service.dto.GroupsDto;
import kg.megalab.kindergarten_service.model.Groups;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public Groups toEntity(GroupsDto dto) {
        Groups entity = new Groups();
        entity.setName(dto.getName());
        entity.setMaxChildrenCount(dto.getMaxChildrenCount());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public GroupsDto toDto(Groups entity) {
        GroupsDto dto = new GroupsDto();
        dto.setName(entity.getName());
        dto.setMaxChildrenCount(entity.getMaxChildrenCount());
        dto.setPrice(entity.getPrice());
        dto.setNannyId(entity.getNanny().getId());
        dto.setTeacherId(entity.getTeacher().getId());
        dto.setGroupCategoryId(entity.getGroupCategory().getId());
        return dto;
    }

    public void updateEntityFromDto(GroupsDto dto, Groups entity) {
        entity.setName(dto.getName());
        entity.setMaxChildrenCount(dto.getMaxChildrenCount());
        entity.setPrice(dto.getPrice());
    }
}
