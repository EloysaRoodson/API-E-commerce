package com.eloysaRoodson.API.E_commerce.domain.service;

import com.eloysaRoodson.API.E_commerce.domain.entity.Category;
import com.eloysaRoodson.API.E_commerce.domain.entity.Product;
import com.eloysaRoodson.API.E_commerce.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    
    @Transactional(readOnly = true)
    public Page<Product> findByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoria_Id(categoryId, pageable);
    }
    
    @Transactional(readOnly = true)
    public Page<Product> searchByName(String name, Pageable pageable) {
        return productRepository.findByNomeContainingIgnoreCase(name, pageable);
    }
    
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }
    
    @Transactional
    public Product create(Product product) {
        Category category = categoryService.findById(product.getCategoria().getId());
        product.setCategoria(category);
        return productRepository.save(product);
    }
    
    @Transactional
    public Product update(Long id, Product product) {
        Product existingProduct = findById(id);
        Category category = categoryService.findById(product.getCategoria().getId());
        
        existingProduct.setNome(product.getNome());
        existingProduct.setDescricao(product.getDescricao());
        existingProduct.setPreco(product.getPreco());
        existingProduct.setQuantidadeEstoque(product.getQuantidadeEstoque());
        existingProduct.setCategoria(category);
        existingProduct.setUrlImagem(product.getUrlImagem());
        
        return productRepository.save(existingProduct);
    }
    
    @Transactional
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
    
    @Transactional
    public void updateStock(Long id, int quantity) {
        Product product = findById(id);
        int newStock = product.getQuantidadeEstoque() - quantity;
        
        if (newStock < 0) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque");
        }
        
        product.setQuantidadeEstoque(newStock);
        productRepository.save(product);
    }
}