package sn.work.lostandfound.category;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/find")
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    public CategoryController(CategoryServiceImpl categoryService){
        this.categoryService = categoryService;
    }
    @PostMapping("/category/create")
    public CategoryDto addCategory(
            @ModelAttribute CategoryDto categoryDto,
            @RequestParam("file")  MultipartFile file
    ) throws IOException {
        return categoryService.addCategory(categoryDto, file);
    }
    @PutMapping("/category/update/{categoryId}")
    public CategoryDto updateCategory(
            @PathVariable("categoryId") Long categoryId,
            CategoryDto categoryDto
    ){
        return categoryService.updateCategory(categoryId, categoryDto);
    }
    @GetMapping("/category/all")
    public List<CategoryDto> findAllCategories(){
        return categoryService.findAllCategories();
    }
    @DeleteMapping("/category/delete/{categoryId}")
    public void deleteCategory(@PathVariable("CategoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
}
