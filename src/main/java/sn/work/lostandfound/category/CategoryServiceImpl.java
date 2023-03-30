package sn.work.lostandfound.category;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sn.work.lostandfound.myException.NotFoundException;
import sn.work.lostandfound.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            CategoryConverter categoryConverter
    ){
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto, MultipartFile file) throws IOException {
        Category category = categoryConverter.convertToEntity(categoryDto);
        String filename = Utils.fileUpload(file);
        if(filename != null)
            category.setCategoryImage(filename);
        Category categorySaved = categoryRepository.save(category);
        return categoryConverter.convertToDto(categorySaved);
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {

        Category categoryToUpdate = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id " + id));

        categoryToUpdate.setLibelle(categoryDto.getLibelle());
        categoryToUpdate.setCode(categoryDto.getCode());
        categoryToUpdate.setCategoryImage(categoryDto.getCategoryImage());

        Category categorySaved = categoryRepository.save(categoryToUpdate);
        return categoryConverter.convertToDto(categorySaved);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(categoryConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findCategoryById(Long id) {
        return null;
    }

    @Override
    public Optional<CategoryDto> findCategoryByCode(String code) {
        return Optional.empty();
    }

}
