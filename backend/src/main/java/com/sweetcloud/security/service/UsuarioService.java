package com.sweetcloud.security.service;

import com.sweetcloud.exception.BusinessException;
import com.sweetcloud.security.model.Usuario;
import com.sweetcloud.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
        // ✅ Convertir roles a GrantedAuthority
        Collection<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());
        
        return new User(usuario.getUsername(), usuario.getPassword(), authorities);
    }

    public Usuario registrarUsuario(String username, String password) {
        // ✅ Validar si el usuario ya existe
        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new BusinessException("El usuario '" + username + "' ya existe");
        }

        // ✅ Crear nuevo usuario
        Usuario nuevo = new Usuario();
        nuevo.setUsername(username);
        nuevo.setPassword(passwordEncoder.encode(password));
        
        // ✅ Guardar y retornar
        return usuarioRepository.save(nuevo);
    }
}