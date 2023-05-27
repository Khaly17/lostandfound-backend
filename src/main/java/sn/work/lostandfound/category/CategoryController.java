package sn.work.lostandfound.category;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

        private static String BASE_IMAGE_PATH = "/app/images/";
        @GetMapping("/{id}/image")
        public ResponseEntity<byte[]> getCategoryImage(@PathVariable Long id) throws IOException {
            CategoryDto category = categoryService.findCategoryById(id);

            if (category != null && category.getCategoryImage() != null) {
                // Lire le fichier d'image depuis le volume en utilisant le chemin d'image
                File imageFile = new File(category.getCategoryImage());

                // Convertir le fichier d'image en tableau de bytes
                byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

                // Définir les en-têtes de la réponse
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // Remplacez par le type MIME approprié

                return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
            }

            return ResponseEntity.notFound().build();
        }


}
