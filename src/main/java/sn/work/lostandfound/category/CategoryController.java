package sn.work.lostandfound.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/find")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;
    @PostMapping("/category/create")
    public CategoryDto addCategory(
            @ModelAttribute CategoryDto categoryDto,
            @RequestParam("file")  MultipartFile file) throws IOException {
        return categoryService.addCategory(categoryDto, file);
    }
}
