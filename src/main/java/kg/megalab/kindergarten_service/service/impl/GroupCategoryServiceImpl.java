package kg.megalab.kindergarten_service.service.impl;

import kg.megalab.kindergarten_service.dto.GroupCategoriesDto;
import kg.megalab.kindergarten_service.mapper.GroupCategoryMapper;
import kg.megalab.kindergarten_service.model.GroupCategories;
import kg.megalab.kindergarten_service.repository.GroupCategoryRepo;
import kg.megalab.kindergarten_service.service.GroupCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GroupCategoryServiceImpl implements GroupCategoryService {

    private final GroupCategoryRepo groupCategoryRepo;
    private final GroupCategoryMapper groupCategoryMapper;

    @Override
    public GroupCategories create(GroupCategoriesDto groupCategoriesDto) {
        if (groupCategoryRepo.existsByName(groupCategoriesDto.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Категория с таким названием уже существует");
        }
        GroupCategories category = groupCategoryMapper.toEntity(groupCategoriesDto);
        return groupCategoryRepo.save(category);

    }

    @Override
    public GroupCategories update(Long id, GroupCategoriesDto groupCategoriesDto) {
        GroupCategories groupCategories = groupCategoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория не найдена"));

        if (groupCategoriesDto.getName() == null || groupCategoriesDto.getName().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Название категории не может быть пустым");
        }

        groupCategoryMapper.updateEntityFromDto(groupCategoriesDto, groupCategories);
        return groupCategoryRepo.save(groupCategories);

    }

    @Override
    public GroupCategories delete(Long id) {
        GroupCategories groupCategories = groupCategoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория не найдена"));

        groupCategoryRepo.delete(groupCategories);
        return groupCategories;
    }

    @Override
    public GroupCategories getById(Long id) {
        return groupCategoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Категория не найдена"));
    }

    @Override
    public Map<String, Object> getAll(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверные параметры пагинации");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<GroupCategories> categoryPage = groupCategoryRepo.findAll(pageable);

        List<GroupCategoriesDto> categories = categoryPage.getContent()
                .stream()
                .map(groupCategoryMapper::toDto)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories);
        response.put("totalItems", categoryPage.getTotalElements());
        return response;
    }


}
