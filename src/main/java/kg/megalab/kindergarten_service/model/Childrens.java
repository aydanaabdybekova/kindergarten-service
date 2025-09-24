package kg.megalab.kindergarten_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "childrens")
@Data
@NoArgsConstructor
public class Childrens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    private String patronymic;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
}
