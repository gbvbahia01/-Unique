# -Unique
Create a JPA query to consult for unique fields.

##Using
To use: annote your entities using @Unique like:
```java
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
    }
```

Instantiate a UniqueParans object:
`UniqueParans instance = new UniqueParans();`

And get a javax.persistence.Query object:
`Query query = uniqueParans.getJpaQuery(entity, entityManager);`

See UniqueParansJpaQueryTest object to get a full example.

###Maven
Donwload the project from here, execute mvn install in same folder and add in your pom.xml:
```XML
<dependency>
   <groupId>br.com.gbvbahia</groupId>
   <artifactId>Unique</artifactId>
   <version>1.0</version>
</dependency>
```
