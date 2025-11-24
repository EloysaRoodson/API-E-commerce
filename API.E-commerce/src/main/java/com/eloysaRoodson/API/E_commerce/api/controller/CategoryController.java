package com.eloysaRoodson.API.E_commerce.api.controller;

import com.eloysaRoodson.API.E_commerce.api.dto.CategoryDTO;
import com.eloysaRoodson.API.E_commerce.domain.entity.Category;
import com.eloysaRoodson.API.E_commerce.domain.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> dtos = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        CategoryDTO dto = modelMapper.map(category, CategoryDTO.class);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        category = categoryService.create(category);
        CategoryDTO dto = modelMapper.map(category, CategoryDTO.class);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "mensagem", " Nova categoria adicionada com sucesso!",
                        "categoria", dto
                ));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        category = categoryService.update(id, category);
        CategoryDTO dto = modelMapper.map(category, CategoryDTO.class);
        
        return ResponseEntity.ok(Map.of(
                "mensagem", " Categoria atualizada com sucesso!",
                "categoria", dto
        ));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(Map.of("mensagem", " Categoria removida com sucesso!"));
    }
}
