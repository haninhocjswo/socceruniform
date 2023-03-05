package shop.soccerUniform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.ItemSearchForm;
import shop.soccerUniform.repository.item.ItemRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public void saveItem(ItemForm itemForm) {

    }

    @Override
    public void editItem(ItemForm itemForm, Long itemId) {

    }

    @Override
    public void deletedItem(Long itemId) {

    }

    @Override
    public ItemForm detailItem(Long itemId) {
        return null;
    }

    @Override
    public Page<ItemForm> items(ItemSearchForm itemSearchForm, Pageable pageable) {
        return itemRepository.items(itemSearchForm, pageable);
    }
}
