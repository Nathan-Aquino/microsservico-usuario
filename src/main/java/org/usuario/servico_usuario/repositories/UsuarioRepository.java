package org.usuario.servico_usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.usuario.servico_usuario.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

}
