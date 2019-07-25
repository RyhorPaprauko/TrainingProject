package by.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "booking", schema = "bookstore_storage")
public class Booking implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_completed", nullable = false)
    private Boolean completed;

    @Column(name = "is_processed", nullable = false)
    private Boolean processed;

    @Builder.Default
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "booking_book", schema = "bookstore_storage",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books = new ArrayList<>();

    public Boolean isCompleted() {
        return completed;
    }

    public Boolean isProcessed() {
        return processed;
    }

    public Double getTotalPrice() {
        return books.stream()
                .mapToDouble(Book::getPrice)
                .sum();
    }
}
