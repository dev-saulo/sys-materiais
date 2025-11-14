package repository;

import com.keeper.sys_materiais.model.Item;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final List<Item> items = new ArrayList<>();
    private Long currentId = 1L;

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public Item save(Item item) {
        if (item.getId() == null) {
            item.setId(currentId++);
            items.add(item);
        } else {
            // Atualizar item existente
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getId().equals(item.getId())) {
                    items.set(i, item);
                    break;
                }
            }
        }
        return item;
    }

    @Override
    public List<Item> findByAtivoTrue() {
        return items.stream()
                .filter(item -> Boolean.TRUE.equals(item.getAtivo()))
                .collect(java.util.stream.Collectors.toList());
    }
}
