package sn.work.lostandfound.category;

import lombok.*;
import sn.work.lostandfound.objet.ObjetDto;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {
    private Long id;
    private String libelle;
    private String code;
    private String categoryImage;
    private List<ObjetDto> objetDtoList;
}


