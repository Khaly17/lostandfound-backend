package sn.work.lostandfound.category;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto, MultipartFile file) throws IOException;
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
    List<CategoryDto> findAllCategories();
    CategoryDto findCategoryById(Long id);
    Optional<CategoryDto> findCategoryByCode(String code);

}

