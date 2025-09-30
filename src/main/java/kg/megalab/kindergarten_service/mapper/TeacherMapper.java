package kg.megalab.kindergarten_service.mapper;

import kg.megalab.kindergarten_service.dto.TeachersDto;
import kg.megalab.kindergarten_service.model.Teachers;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public Teachers toEntity(TeachersDto dto) {
        Teachers entity = new Teachers();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPatronymic(dto.getPatronymic());
        entity.setTeacherDegree(dto.getTeacherDegree());
        entity.setActive(dto.getActive());
        return entity;
    }

    public TeachersDto toDto(Teachers entity) {
        TeachersDto dto = new TeachersDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPatronymic(entity.getPatronymic());
        dto.setTeacherDegree(entity.getTeacherDegree());
        dto.setActive(entity.getActive());
        return dto;
    }

    public void updateEntityFromDto(TeachersDto dto, Teachers entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPatronymic(dto.getPatronymic());
        entity.setTeacherDegree(dto.getTeacherDegree());
        entity.setActive(dto.getActive());
    }
}
