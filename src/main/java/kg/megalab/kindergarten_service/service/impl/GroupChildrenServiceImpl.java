package kg.megalab.kindergarten_service.service.impl;

import kg.megalab.kindergarten_service.dto.EnrollChildDto;
import kg.megalab.kindergarten_service.dto.GroupChildrensDto;
import kg.megalab.kindergarten_service.dto.WithdrawChildDto;
import kg.megalab.kindergarten_service.mapper.GroupChildrenMapper;
import kg.megalab.kindergarten_service.model.Childrens;
import kg.megalab.kindergarten_service.model.GroupChildrens;
import kg.megalab.kindergarten_service.model.Groups;
import kg.megalab.kindergarten_service.repository.ChildrenRepo;
import kg.megalab.kindergarten_service.repository.GroupChildrenRepo;
import kg.megalab.kindergarten_service.repository.GroupRepo;
import kg.megalab.kindergarten_service.service.GroupChildrenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GroupChildrenServiceImpl implements GroupChildrenService {

    private final GroupChildrenRepo groupChildrenRepo;
    private final ChildrenRepo childrenRepo;
    private final GroupRepo groupRepo;
    private final GroupChildrenMapper groupChildrenMapper;

    @Override
    public GroupChildrens enrollChild(EnrollChildDto dto) {
        Groups group = groupRepo.findById(dto.getGroupId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Группа не найдена"));

        long currentCount = groupChildrenRepo.countByGroupId(group.getId());
        if (currentCount >= group.getMaxChildrenCount()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Группа заполнена");
        }

        Childrens child = (Childrens) childrenRepo.findByFirstNameAndLastNameAndDateOfBirth(
                        dto.getFirstName(), dto.getLastName(), dto.getDateOfBirth())
                .orElseGet(() -> {
                    Childrens newChild = new Childrens();
                    newChild.setFirstName(dto.getFirstName());
                    newChild.setLastName(dto.getLastName());
                    newChild.setPatronymic(dto.getPatronymic());
                    newChild.setDateOfBirth(dto.getDateOfBirth());
                    return childrenRepo.save(newChild);
                });

        GroupChildrens groupChild = new GroupChildrens();
        groupChild.setChildren(child);
        groupChild.setGroup(group);
        groupChild.setStartDate(LocalDate.now().minusMonths(1));
        groupChild.setPrice(dto.getPrice() != null ? dto.getPrice() : group.getPrice());

        return groupChildrenRepo.save(groupChild);
    }

    @Override
    public GroupChildrensDto withdrawChild(Long id, WithdrawChildDto withdrawChildDto) {
        GroupChildrens groupChild = groupChildrenRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ребенок в группе не найден"));

        LocalDate endDate = (withdrawChildDto != null && withdrawChildDto.getEndDate() != null)
                ? withdrawChildDto.getEndDate()
                : LocalDate.now();

        groupChild.setEndDate(endDate);
        groupChildrenRepo.save(groupChild);

        return groupChildrenMapper.toDto(groupChild);
    }

}
