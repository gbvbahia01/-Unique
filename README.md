# -Unique
Create a JPA query to consult for unique fields.

##Using
To use you annote your entities like:
```
@Entity
@Table(name = "sub_category", schema = "products", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "category_id"})})
@Unique(fields = {"name", "category"})
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name", length = 40, nullable = false, unique = false)
    private String name;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "ID")
    @NotNull
    private Category category;
    
    ...
    }
    
    
