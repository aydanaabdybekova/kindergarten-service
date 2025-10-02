package kg.megalab.kindergarten_service.service.impl;

import kg.megalab.kindergarten_service.dto.PaymentsDto;
import kg.megalab.kindergarten_service.dto.PreviousMonthDebtDto;
import kg.megalab.kindergarten_service.mapper.PaymentMapper;
import kg.megalab.kindergarten_service.model.GroupChildrens;
import kg.megalab.kindergarten_service.model.Payments;
import kg.megalab.kindergarten_service.repository.GroupChildrenRepo;
import kg.megalab.kindergarten_service.repository.PaymentRepo;
import kg.megalab.kindergarten_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final GroupChildrenRepo groupChildrenRepo;
    private final PaymentMapper paymentMapper;

    @Transactional
    @Override
    public PaymentsDto addPayment(PaymentsDto paymentsDto) {
        var groupChild = groupChildrenRepo.findById(paymentsDto.getGroupChildrenId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Запись ребенка в группе не найдена"));

        Payments payment = new Payments();
        payment.setGroupChildrens(groupChild);
        payment.setAmount(paymentsDto.getAmount());
        payment.setPaymentDate(paymentsDto.getPaymentDate());

        Payments saved = paymentRepo.save(payment);
        return paymentMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public PreviousMonthDebtDto getPreviousMonthDebt(Long childId) {
        YearMonth previousMonth = YearMonth.now().minusMonths(1);
        LocalDate startOfMonth = previousMonth.atDay(1);
        LocalDate endOfMonth = previousMonth.atEndOfMonth();

        List<GroupChildrens> groupChildrenList = groupChildrenRepo.findByChildren_Id(childId);

        GroupChildrens groupChild = groupChildrenList.stream()
                .filter(gc -> !gc.getStartDate().isAfter(endOfMonth) &&
                        (gc.getEndDate() == null || !gc.getEndDate().isBefore(startOfMonth)))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Ребенок не найден или не был активен в прошлом месяце"));

        double price = groupChild.getPrice() != null
                ? groupChild.getPrice()
                : groupChild.getGroup().getPrice();

        double totalPaid = paymentRepo.findByGroupChildrensAndPaymentDateBetween(
                        groupChild, startOfMonth, endOfMonth)
                .stream()
                .mapToDouble(Payments::getAmount)
                .sum();

        double result = Math.max(price - totalPaid, 0); // не может быть отрицательным

        PreviousMonthDebtDto dto = new PreviousMonthDebtDto();
        dto.setChildId(childId);
        dto.setAmountDue((int) Math.round(result));

        return dto;
    }
}
