package sn.work.lostandfound.category;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sn.work.lostandfound.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        List<CategoryDto> categoryDtoList = categoryService.findAllCategories();
        return categoryDtoList;
    }
    @DeleteMapping("/category/delete/{categoryId}")
    public void deleteCategory(@PathVariable("CategoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/category/image/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        String imagePath = "/app/images/category-images/" + imageName;

        try {
            Resource imageResource = new FileSystemResource(imagePath);

            if (imageResource.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);
                return new ResponseEntity<>(imageResource, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

