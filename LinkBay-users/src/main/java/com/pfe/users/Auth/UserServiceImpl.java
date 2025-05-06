package com.pfe.users.Auth;

import com.pfe.users.Utilisateur;
import com.pfe.users.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UserServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur createUser(Utilisateur utilisateur) {
        // Basic validation
        if (utilisateur == null) {
            throw new IllegalArgumentException("Utilisateur cannot be null");
        }
        if (utilisateur.getEmail() == null || utilisateur.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        // Check if email already exists
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + utilisateur.getEmail());
        }
        // Hash password
        String hashedPassword = BCrypt.hashpw(utilisateur.getPassword(), BCrypt.gensalt());
        utilisateur.setPassword(hashedPassword);
        
        // Save the user
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }
}