package kg.megalab.kindergarten_service.repository;

import kg.megalab.kindergarten_service.model.GroupChildrens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupChildrenRepo extends JpaRepository<GroupChildrens, Long> {
    long countByGroupId(Long id);
}
