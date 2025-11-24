package com.eloysaRoodson.API.E_commerce.api.controller;

import com.eloysaRoodson.API.E_commerce.api.dto.ProductDTO;
import com.eloysaRoodson.API.E_commerce.domain.entity.Category;
import com.eloysaRoodson.API.E_commerce.domain.entity.Product;
import com.eloysaRoodson.API.E_commerce.domain.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);
        Page<ProductDTO> dtos = products.map(product -> modelMapper.map(product, ProductDTO.class));
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ProductDTO>> findByCategory(
            @PathVariable Long categoryId,
            Pageable pageable) {
        Page<Product> products = productService.findByCategory(categoryId, pageable);
        Page<ProductDTO> dtos = products.map(product -> modelMapper.map(product, ProductDTO.class));
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductDTO>> searchByName(
            @RequestParam String name,
            Pageable pageable) {
        Page<Product> products = productService.searchByName(name, pageable);
        Page<ProductDTO> dtos = products.map(product -> modelMapper.map(product, ProductDTO.class));
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setCategoria(new Category());
        product.getCategoria().setId(productDTO.getIdCategoria());

        product = productService.create(product);
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "mensagem", " Produto adicionado com sucesso!",
                        "produto", dto
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO) {

        Product product = modelMapper.map(productDTO, Product.class);
        product.setCategoria(new Category());
        product.getCategoria().setId(productDTO.getIdCategoria());

        product = productService.update(id, product);
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);

        return ResponseEntity.ok(Map.of(
                "mensagem", " Produto atualizado com sucesso!",
                "produto", dto
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(Map.of("mensagem", " Produto deletado com sucesso!"));
    }
}
