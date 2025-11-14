package com.keeper.sys_materiais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.keeper.sys_materiais.model.Solicitacao;
import com.keeper.sys_materiais.repository.SolicitacaoRepository;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {
    @Autowired
    @Qualifier("solicitacaoRepositoryImpl")
    private SolicitacaoRepository solicitacaoRepo;

    @GetMapping("/list")
    public ResponseEntity<List<Solicitacao>> getAllSolicitacoes() {
        List<Solicitacao> solicitacoes = solicitacaoRepo.findAll();
        return ResponseEntity.ok(solicitacoes);
    }

    @PostMapping("/add")
    public ResponseEntity<Solicitacao> addSolicitacao(@RequestBody Solicitacao solicitacao) {
        solicitacao.setData(LocalDateTime.now());
        Solicitacao savedSolicitacao = solicitacaoRepo.save(solicitacao);
        return ResponseEntity.ok(savedSolicitacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitacao(@PathVariable Long id) {
        solicitacaoRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}