package kg.megalab.kindergarten_service.model;

import jakarta.persistence.*;
import kg.megalab.kindergarten_service.enums.TeacherDegree;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
public class Teachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_degree", nullable = false)
    private TeacherDegree teacherDegree;
    @Column(nullable = false)
    private Boolean active;

}
