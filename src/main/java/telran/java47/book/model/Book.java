package telran.java47.book.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "isbn")
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

    private static final long serialVersionUID = 3189342446960430297L;

    @Id
    String isbn;
    String title;
    @ManyToMany
    @JoinTable(
            name = "BOOK_AUTHORS",
            joinColumns = @JoinColumn(name = "BOOK_ISBN"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORS_NAME")
    )
    Set<Author> authors;
    @ManyToOne
    Publisher publisher;

}
