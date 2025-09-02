package br.com.brunoedubems.usuario.business;

import br.com.brunoedubems.usuario.business.converter.UsuarioConverter;
import br.com.brunoedubems.usuario.business.dto.UsuarioDTO;
import br.com.brunoedubems.usuario.infrastructure.entity.Usuario;
import br.com.brunoedubems.usuario.infrastructure.exceptions.ConflictException;
import br.com.brunoedubems.usuario.infrastructure.exceptions.ResourceNotFoundException;
import br.com.brunoedubems.usuario.infrastructure.repository.UsuarioRepository;
import br.com.brunoedubems.usuario.infrastructure.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioConverter usuarioConverter, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioConverter = usuarioConverter;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExistente(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }


    public void emailExistente(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }


    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        try {
            return usuarioConverter.paraUsuarioDTO(
                    usuarioRepository.findByEmail(email).orElseThrow(
                            () -> new ResourceNotFoundException("Email não encontrado " + email)
                    ));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email não encontrado " + email);
        }
    }

    public void deletaUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO usuarioDTO) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        usuarioDTO.setSenha(usuarioDTO.getSenha()  != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);

        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não localizado"));

        Usuario usuario = usuarioConverter.updateUsuario(usuarioDTO, usuarioEntity);

        usuario.setSenha(passwordEncoder.encode(usuario.getPassword()));

        return  usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
