package com.keeper.sys_materiais.controller;

import com.keeper.sys_materiais.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.keeper.sys_materiais.model.Item;
import com.keeper.sys_materiais.repository.ItemRepository;
import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {
    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private LogService logService;

    @GetMapping("/list")
    public ResponseEntity<List<Item>> getAllItems() {
        try {
            logService.info("Listando todos os itens cadastrados");
            List<Item> items = itemRepo.findAll();
            logService.info("Total de itens retornados: " + items.size());
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            logService.error("Erro ao listar itens", e);
            throw e;
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        try {
            logService.info("Cadastrando novo item: " + item.getNome());

            if (item.getAtivo() == null) {
                item.setAtivo(true);
                logService.debug("Status 'ativo' definido como true por padrão");
            }

            if (item.getNome() == null || item.getNome().isBlank()) {
                logService.warn("Tentativa de cadastro de item sem nome");
                return ResponseEntity.badRequest().build();
            }

            Item savedItem = itemRepo.save(item);
            logService.info("✅ Item cadastrado com sucesso - ID: " + savedItem.getId() + ", Nome: " + savedItem.getNome());

            return ResponseEntity.ok(savedItem);
        } catch (Exception e) {
            logService.error("Erro ao cadastrar item: " + item.getNome(), e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        try {
            logService.info("Atualizando item com ID: " + id);

            if (!itemRepo.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            item.setId(id);
            Item updatedItem = itemRepo.save(item);
            logService.info("✅ Item atualizado com sucesso - ID: " + id);
            return ResponseEntity.ok(updatedItem);
        } catch (Exception e) {
            logService.error("Erro ao atualizar item com ID: " + id, e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        try {
            logService.info("Deletando item com ID: " + id);

            if (!itemRepo.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            itemRepo.deleteById(id);
            logService.info("✅ Item deletado com sucesso - ID: " + id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logService.error("Erro ao deletar item com ID: " + id, e);
            throw e;
        }
    }
}
