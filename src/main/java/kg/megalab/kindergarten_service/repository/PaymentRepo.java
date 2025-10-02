package kg.megalab.kindergarten_service.repository;

import kg.megalab.kindergarten_service.model.GroupChildrens;
import kg.megalab.kindergarten_service.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;
import java.time.LocalDate;
import java.util.List;

public interface PaymentRepo extends JpaRepository<Payments, Long> {
    List<Payments> findByGroupChildrensAndPaymentDateBetween(GroupChildrens groupChild, LocalDate startOfMonth, LocalDate endOfMonth);
}
