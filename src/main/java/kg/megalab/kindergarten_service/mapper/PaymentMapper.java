package kg.megalab.kindergarten_service.mapper;

import kg.megalab.kindergarten_service.dto.PaymentsDto;
import kg.megalab.kindergarten_service.model.GroupChildrens;
import kg.megalab.kindergarten_service.model.Payments;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentsDto toDto(Payments payment) {
        PaymentsDto dto = new PaymentsDto();
        dto.setGroupChildrenId(payment.getGroupChildrens().getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        return dto;
    }

    public Payments toEntity(PaymentsDto dto, GroupChildrens groupChildrens) {
        Payments payment = new Payments();
        payment.setGroupChildrens(groupChildrens);
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        return payment;
    }
}
