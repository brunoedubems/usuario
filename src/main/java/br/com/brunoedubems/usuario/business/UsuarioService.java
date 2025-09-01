package br.com.brunoedubems.usuario.business;

import br.com.brunoedubems.usuario.business.converter.UsuarioConverter;
import br.com.brunoedubems.usuario.business.dto.UsuarioDTO;
import br.com.brunoedubems.usuario.infrastructure.entity.Usuario;
import br.com.brunoedubems.usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioConverter usuarioConverter) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioConverter = usuarioConverter;
    }

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
