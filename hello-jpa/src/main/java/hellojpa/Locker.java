package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.hibernate.mapping.PrimaryKey;

@Entity
public class Locker {
    @Id @GeneratedValue
    private Long id;
    private String name;
}
