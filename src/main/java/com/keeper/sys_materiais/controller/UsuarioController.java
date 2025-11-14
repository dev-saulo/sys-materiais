package com.keeper.sys_materiais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.keeper.sys_materiais.model.Usuario;
import com.keeper.sys_materiais.repository.UsuarioRepository;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    @Qualifier("usuarioRepositoryImpl")
    private UsuarioRepository usuarioRepo;

    @GetMapping("/list")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepo.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/add")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioRepo.save(usuario);
        return ResponseEntity.ok(savedUsuario);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String matricula, @RequestBody Usuario usuario) {
        usuario.setMatricula(matricula);
        Usuario updatedUsuario = usuarioRepo.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }
}
