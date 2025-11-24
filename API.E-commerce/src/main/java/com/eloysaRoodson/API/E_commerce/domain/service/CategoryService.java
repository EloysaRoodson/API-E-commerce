package com.eloysaRoodson.API.E_commerce.domain.service;

import com.eloysaRoodson.API.E_commerce.domain.entity.Category;
import com.eloysaRoodson.API.E_commerce.domain.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
    }
    
    @Transactional
    public Category create(Category category) {
        if (categoryRepository.existsByNome(category.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com este nome");
        }
        return categoryRepository.save(category);
    }
    
    @Transactional
    public Category update(Long id, Category category) {
        Category existingCategory = findById(id);
        
        if (!existingCategory.getNome().equals(category.getNome()) 
            && categoryRepository.existsByNome(category.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com este nome");
        }
        
        existingCategory.setNome(category.getNome());
        existingCategory.setDescricao(category.getDescricao());
        
        return categoryRepository.save(existingCategory);
    }
    
    @Transactional
    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}