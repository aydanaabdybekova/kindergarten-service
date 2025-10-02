package kg.megalab.kindergarten_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "group_categories")
@Data
@NoArgsConstructor
public class GroupCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Boolean active;
    @Column(nullable = false)
    private double price;
}
