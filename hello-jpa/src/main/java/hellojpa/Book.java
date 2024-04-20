package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//@DiscriminatorValue("B")
public class Book extends Item{
    private String isbn;
    private String author;
}
