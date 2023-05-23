package sn.work.lostandfound.objet;

import java.time.LocalDate;

import lombok.*;
import sn.work.lostandfound.category.CategoryDto;
import sn.work.lostandfound.person.PersonDto;

//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ObjetDto {
    private Long id;
    private String objetName;
    private String description;
    private String createdDate;
    private String updatedDate;
    private Boolean status;
    private PersonDto person;
    private CategoryDto category;
    private String photo;
    private String objetNumber;
    private String token;
   
}

