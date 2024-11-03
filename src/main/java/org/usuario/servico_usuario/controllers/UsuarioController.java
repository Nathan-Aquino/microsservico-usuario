package org.usuario.servico_usuario.controllers;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.usuario.servico_usuario.dtos.UsuarioDTO;
import org.usuario.servico_usuario.entities.UsuarioEntity;
import org.usuario.servico_usuario.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    public UsuarioService service;

    @GetMapping
    public List<UsuarioEntity> getUsuarios() {
        logger.info("Requisitando todos os usuarios");
        List<UsuarioEntity> usuarios = service.getAllUsuarios();
        logger.info("Total de usuarios encontrados: {}", usuarios.size());
        return usuarios;
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<UsuarioEntity> getUsuarioById(@PathVariable Long id) {
        logger.info("Requisitando usuario com ID: {}", id);
        UsuarioEntity usuario = service.getUsuarioById(id);

        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    
    @PostMapping
    public UsuarioEntity postMethodName(@RequestBody UsuarioDTO usuario) throws ParseException {        
        logger.info("Salvando novo usuario.");
        UsuarioEntity savedUsuario = service.saveUsuario(usuario);
        logger.info("Usuario de id {} salvo com sucesso", savedUsuario.getId());
        return savedUsuario;
    }
    
}
