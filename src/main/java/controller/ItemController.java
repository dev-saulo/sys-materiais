package controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import model.Item;
import repository.ItemRepository;

import java.util.List;

@Component
@RestController
@RequestMapping("/itens")
public class ItemController {
    @Autowired
    @Qualifier("itemRepositoryImpl")
    private ItemRepository itemRepo;

    @GetMapping("/list")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemRepo.findAll();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/add")
    public void add(@RequestBody Item item) {
        itemRepo.save(item);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        itemRepo.save(item);
    }
}
