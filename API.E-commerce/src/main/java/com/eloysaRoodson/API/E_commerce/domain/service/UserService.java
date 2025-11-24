package com.eloysaRoodson.API.E_commerce.domain.service;

import com.eloysaRoodson.API.E_commerce.domain.entity.User;
import com.eloysaRoodson.API.E_commerce.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

   
    @Transactional
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

   
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

  
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    @Transactional(readOnly = true)
    public boolean isPasswordValid(User user, String rawSenha) {
        return passwordEncoder.matches(rawSenha, user.getSenha());
    }

   
    @Transactional
    public User update(Long id, User newData) {
        User existing = findById(id); 

        existing.setNome(newData.getNome());
        existing.setTelefone(newData.getTelefone());
        existing.setEndereco(newData.getEndereco());

       
        if (newData.getSenha() != null && !newData.getSenha().isBlank()) {
            existing.setSenha(passwordEncoder.encode(newData.getSenha()));
        }

        return userRepository.save(existing);
    }

  
    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }
}
