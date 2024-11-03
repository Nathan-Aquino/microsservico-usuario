package org.usuario.servico_usuario.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.usuario.servico_usuario.dtos.UsuarioDTO;
import org.usuario.servico_usuario.entities.UsuarioEntity;
import org.usuario.servico_usuario.repositories.UsuarioRepository;

@Component
public class UsuarioService {
    
    @Autowired
    public UsuarioRepository repository;

    public List<UsuarioEntity> getAllUsuarios () {
        return repository.findAll();
    }

    public UsuarioEntity getUsuarioById (Long id) {
        return repository.findById(id).orElseGet(() -> null);
    }

    public UsuarioEntity saveUsuario (UsuarioDTO usuario) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        Date dataInicioSQL = new Date(formato.parse(usuario.getDataCadastro()).getTime());

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setDataCadastro(dataInicioSQL);

        return repository.save(usuarioEntity);
    }
}
