package org.example.controller;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Usuario createUser(Usuario usuario) {
        return userRepository.save(usuario);
    }

    public Usuario updateUser(Long id, Usuario usuario) {
        if (userRepository.existsById(id)) {
            usuario.setId(id);
            return userRepository.save(usuario);
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}