package sn.work.lostandfound.category;

import org.springframework.stereotype.Component;
import sn.work.lostandfound.objet.Objet;
import sn.work.lostandfound.objet.ObjetConverter;
import sn.work.lostandfound.objet.ObjetDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {
    public ObjetConverter objetConverter;
    public CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setLibelle(category.getLibelle());
        categoryDto.setCode(category.getCode());
        if(category.getCategoryImage() != null)
            categoryDto.setCategoryImage(category.getCategoryImage());
        if(category.getObjetList() != null) {
            List<ObjetDto> objetDtoList = new ArrayList<>();
            for (Objet objet: category.getObjetList()) {
                objetDtoList.add(objetConverter.convertToDto(objet));
            }
            categoryDto.setObjetDtoList(objetDtoList);
        }
        return categoryDto;
    }

    public Category convertToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setLibelle(categoryDto.getLibelle());
        category.setCode(categoryDto.getCode());
        if(categoryDto.getCategoryImage() != null)
            category.setCategoryImage(categoryDto.getCategoryImage());
        return category;
    }
}

