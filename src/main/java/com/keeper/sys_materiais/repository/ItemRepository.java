package com.keeper.sys_materiais.repository;

import com.keeper.sys_materiais.model.Item;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository {
    List<Item> findAll();
    Item save(Item item);
    List<Item> findByAtivoTrue();
}
