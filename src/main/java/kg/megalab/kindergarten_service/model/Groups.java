package kg.megalab.kindergarten_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "groups")
@Data
@NoArgsConstructor
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "max_children_count")
    private Integer maxChildrenCount;
    private double price;

    @ManyToOne
    @JoinColumn(name = "nanny_id", referencedColumnName = "id")
    private Teachers nanny;

    @ManyToOne
    @JoinColumn(name = "group_category_id", referencedColumnName = "id")
    private GroupCategories groupCategory;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teachers teacher;
}
