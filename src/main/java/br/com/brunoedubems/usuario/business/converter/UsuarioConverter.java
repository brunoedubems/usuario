package br.com.brunoedubems.usuario.business.converter;

import br.com.brunoedubems.usuario.business.dto.EnderecoDTO;
import br.com.brunoedubems.usuario.business.dto.TelefoneDTO;
import br.com.brunoedubems.usuario.business.dto.UsuarioDTO;
import br.com.brunoedubems.usuario.infrastructure.entity.Endereco;
import br.com.brunoedubems.usuario.infrastructure.entity.Telefone;
import br.com.brunoedubems.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS) {
        return enderecoDTOS.stream()
                .map(this::paraEndereco)
                .toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS) {
        return telefoneDTOS.stream()
                .map(this::paraTelefone)
                .toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }

    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(this::paraEnderecoDTO)
                .toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefone) {
        return telefone.stream()
                .map(this::paraTelefoneDTO)
                .toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario usuarioEntity) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuarioEntity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuarioEntity.getEmail())
                .enderecos(usuarioEntity.getEnderecos())
                .telefones(usuarioEntity.getTelefones())
                .build();
    }

    public Endereco updateEndereco(EnderecoDTO dto, Endereco enderecoEntity) {
        return Endereco.builder()
                .id(enderecoEntity.getId())
                .rua(dto.getRua() != null ? dto.getRua() : enderecoEntity.getRua())
                .cidade(dto.getCidade() != null ? dto.getCidade() : enderecoEntity.getCidade())
                .numero(dto.getNumero() != null ? dto.getNumero() : enderecoEntity.getNumero())
                .cep(dto.getCep() != null ? dto.getCep() : enderecoEntity.getCep())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : enderecoEntity.getComplemento())
                .estado(dto.getEstado() != null ? dto.getEstado() : enderecoEntity.getEstado())
                .build();
    }

    public Telefone updateTelefone(TelefoneDTO dto, Telefone telefoneEntity) {
        return Telefone.builder()
                .id(telefoneEntity.getId())
                .ddd(dto.getDdd() != null ? dto.getDdd() : telefoneEntity.getDdd())
                .numero(dto.getNumero() != null ? dto.getNumero() : telefoneEntity.getNumero())
                .build();
    }
}
