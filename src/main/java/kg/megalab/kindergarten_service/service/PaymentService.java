package kg.megalab.kindergarten_service.service;

import jakarta.validation.Valid;
import kg.megalab.kindergarten_service.dto.PaymentsDto;
import kg.megalab.kindergarten_service.dto.PreviousMonthDebtDto;

public interface PaymentService {
    PaymentsDto addPayment(@Valid PaymentsDto paymentsDto);

    PreviousMonthDebtDto getPreviousMonthDebt(Long childId);
}
