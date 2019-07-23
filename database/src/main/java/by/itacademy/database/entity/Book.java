package by.itacademy.database.entity;

import by.itacademy.database.entity.enam.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book", schema = "bookstore_storage")
public class Book implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "about")
    private String about;

    @Column(name = "img_link")
    private String image;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(name = "book_author", schema = "bookstore_storage",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();
}
