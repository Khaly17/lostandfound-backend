package sn.work.lostandfound.objet;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import sn.work.lostandfound.category.Category;
import sn.work.lostandfound.person.Person;

@Entity
@Table(name = "objet")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Objet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String objetName;

    private String description;

    private String createdDate;
  
    private String updatedDate;
    
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    private String photo;
    @Column(name = "objet_number", unique = true)
    private String objetNumber;

}
