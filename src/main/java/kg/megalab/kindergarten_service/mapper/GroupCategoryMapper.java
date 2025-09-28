package kg.megalab.kindergarten_service.mapper;

import kg.megalab.kindergarten_service.dto.GroupCategoriesDto;
import kg.megalab.kindergarten_service.model.GroupCategories;
import org.springframework.stereotype.Component;

@Component
public class GroupCategoryMapper {

    public GroupCategories toEntity(GroupCategoriesDto dto) {
        GroupCategories entity = new GroupCategories();
        entity.setName(dto.getName());
        entity.setActive(dto.getActive());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public GroupCategoriesDto toDto(GroupCategories entity) {
        GroupCategoriesDto dto = new GroupCategoriesDto();
        dto.setName(entity.getName());
        dto.setActive(entity.getActive());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public void updateEntityFromDto(GroupCategoriesDto groupCategoriesDto, GroupCategories groupCategories){
        groupCategories.setName(groupCategoriesDto.getName());
        groupCategories.setActive(groupCategoriesDto.getActive());
        groupCategories.setPrice(groupCategoriesDto.getPrice());
    }
}
