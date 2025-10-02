package kg.megalab.kindergarten_service.repository;

import kg.megalab.kindergarten_service.model.GroupChildrens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupChildrenRepo extends JpaRepository<GroupChildrens, Long> {
    long countByGroupId(Long id);

    List<GroupChildrens> findByChildren_Id(Long childId);


}
