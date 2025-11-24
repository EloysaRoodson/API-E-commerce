package com.eloysaRoodson.API.E_commerce.api.controller;

import com.eloysaRoodson.API.E_commerce.api.dto.UserDTO;
import com.eloysaRoodson.API.E_commerce.api.dto.UserRegistrationDTO;
import com.eloysaRoodson.API.E_commerce.api.dto.UserUpdateDTO;
import com.eloysaRoodson.API.E_commerce.domain.entity.User;
import com.eloysaRoodson.API.E_commerce.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        User user = modelMapper.map(registrationDTO, User.class);
        User saved = userService.register(user);

        UserDTO dto = modelMapper.map(saved, UserDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "mensagem", "Usuário adicionado com sucesso!",
                        "usuario", dto
                ));
    }

   
   
    @GetMapping
    public ResponseEntity<Map<String, Object>> listAll() {
        List<User> users = userService.findAll();
        List<UserDTO> dtos = users.stream()
            .map(u -> modelMapper.map(u, UserDTO.class))
            .toList();

        return ResponseEntity.ok(
             Map.of(
                    "mensagem", "Lista de usuários carregada com sucesso.",
                    "quantidade", dtos.size(),
                    "usuarios", dtos
            )
    );
}



    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDTO dto = modelMapper.map(user, UserDTO.class);

     return ResponseEntity.ok(
            Map.of(
                    "mensagem", "Usuário encontrado com sucesso.",
                    "usuario", dto
            )
    );
}


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable Long id,
            @Valid @RequestBody User dto) {
        User updated = userService.update(id, dto);
        UserDTO response = modelMapper.map(updated, UserDTO.class);

        return ResponseEntity.ok(
            Map.of(
                    "mensagem", "Usuário atualizado com sucesso.",
                    "usuario_atualizado", response
            )
    );
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.ok(
            Map.of(
                    "mensagem", "Usuário deletado com sucesso.",
                    "usuario_id", id
            )
    );
    }
}