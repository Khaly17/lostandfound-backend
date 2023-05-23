package sn.work.lostandfound.category;

import org.springframework.stereotype.Component;
import sn.work.lostandfound.objet.Objet;
import sn.work.lostandfound.objet.ObjetConverter;
import sn.work.lostandfound.objet.ObjetDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {
    public ObjetConverter objetConverter;
    public CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        objetConverter = new ObjetConverter();
        categoryDto.setId(category.getId());
        categoryDto.setLibelle(category.getLibelle());
        categoryDto.setCode(category.getCode());
        if(category.getCategoryImage() != null)
            categoryDto.setCategoryImage(category.getCategoryImage());
        if(category.getObjetList() != null) {
            List<ObjetDto> objetDtoList =  category.getObjetList()
                                                        .stream()
                                                        .map(objetConverter::convertToDto)
                                                        .collect(Collectors.toList());
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

