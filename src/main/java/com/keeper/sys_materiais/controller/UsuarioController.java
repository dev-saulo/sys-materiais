package com.keeper.sys_materiais.controller;

import com.keeper.sys_materiais.service.LogService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import com.keeper.sys_materiais.model.Usuario;
import com.keeper.sys_materiais.repository.UsuarioRepository;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private LogService logService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/list")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        try {
            logService.info("Listando todos os usuários");
            List<Usuario> usuarios = usuarioRepo.findAll();
            logService.info("Total de usuários retornados: " + usuarios.size());
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            logService.error("Erro ao listar usuários", e);
            throw e;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        try {
            logService.info("Cadastrando novo usuário: " + usuario.getNome() + " (Matrícula: " + usuario.getMatricula() + ")");

            if (usuario.getAtivo() == null) {
                usuario.setAtivo(true);
                logService.debug("Status 'ativo' definido como true por padrão");
            }

            if (usuario.getMatricula() == null || usuario.getMatricula().isBlank()) {
                logService.warn("Tentativa de cadastro sem matrícula");
                return ResponseEntity.badRequest().build();
            }

            // Validar se já existe usuário com esta matrícula
            Usuario existente = usuarioRepo.findByMatricula(usuario.getMatricula());
            if (existente != null) {
                logService.warn("Matrícula já cadastrada: " + usuario.getMatricula());
                return ResponseEntity.status(409).build(); // Conflict
            }

            Usuario savedUsuario = usuarioRepo.save(usuario);
            logService.info("✅ Usuário cadastrado com sucesso - ID: " + savedUsuario.getId() + ", Matrícula: " + savedUsuario.getMatricula());

            return ResponseEntity.ok(savedUsuario);
        } catch (Exception e) {
            logService.error("Erro ao cadastrar usuário: " + usuario.getMatricula(), e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            logService.info("Atualizando usuário com ID: " + id);

            if (!usuarioRepo.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            usuario.setId(id);
            Usuario savedUsuario = usuarioRepo.save(usuario);

            // Força o flush das mudanças para o banco e recarrega as colunas gerenciadas pelo banco
            entityManager.flush();
            entityManager.refresh(savedUsuario);

            logService.info("✅ Usuário atualizado com sucesso - ID: " + id);
            return ResponseEntity.ok(savedUsuario);
        } catch (Exception e) {
            logService.error("Erro ao atualizar usuário com ID: " + id, e);
            throw e;
        }
    }
}
