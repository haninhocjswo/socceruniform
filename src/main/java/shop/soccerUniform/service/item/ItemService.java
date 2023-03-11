package shop.soccerUniform.service.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.ItemSaveForm;
import shop.soccerUniform.entity.dto.ItemSearchForm;

public interface ItemService {
    public void saveItem(ItemSaveForm itemForm) throws IllegalAccessException;
    public void editItem(ItemSaveForm itemForm, Long itemId);
    public void deletedItem(Long itemId);
    public ItemSaveForm detailItem(Long itemId);
    public Page<ItemForm> items(ItemSearchForm itemSearchForm, Pageable pageable);

}
