package kg.megalab.kindergarten_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_children_id", nullable = false)
    private GroupChildrens groupChildrens;
    @Column(nullable = false)
    private Double amount;
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;
}
