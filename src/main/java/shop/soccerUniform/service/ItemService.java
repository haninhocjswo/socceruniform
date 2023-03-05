package shop.soccerUniform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.ItemSearchForm;

public interface ItemService {
    public void saveItem(ItemForm itemForm);
    public void editItem(ItemForm itemForm, Long itemId);
    public void deletedItem(Long itemId);
    public ItemForm detailItem(Long itemId);
    public Page<ItemForm> items(ItemSearchForm itemSearchForm, Pageable pageable);

}
