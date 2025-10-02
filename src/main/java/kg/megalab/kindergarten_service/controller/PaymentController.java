package kg.megalab.kindergarten_service.controller;

import jakarta.validation.Valid;
import kg.megalab.kindergarten_service.dto.PaymentsDto;
import kg.megalab.kindergarten_service.dto.PreviousMonthDebtDto;
import kg.megalab.kindergarten_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentsDto> createPayment(@Valid @RequestBody PaymentsDto paymentsDto) {
        PaymentsDto savedPayment = paymentService.addPayment(paymentsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }

    @GetMapping("/previous-month/{childId}")
    public ResponseEntity<PreviousMonthDebtDto> getPreviousMonthDebt(@PathVariable Long childId) {
        PreviousMonthDebtDto debtDto = paymentService.getPreviousMonthDebt(childId);
        return ResponseEntity.ok(debtDto);
    }
}
