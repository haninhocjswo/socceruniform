package shop.soccerUniform.service.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.entity.dto.*;
import shop.soccerUniform.entity.enumtype.OptionType;
import shop.soccerUniform.repository.category.CategoryRepository;
import shop.soccerUniform.repository.item.ItemRepository;
import shop.soccerUniform.repository.itemOption.ItemOptionRepository;
import shop.soccerUniform.repository.itemOptionStock.ItemOptionStockRepository;
import shop.soccerUniform.repository.itemOptionValue.ItemOptionValueRepository;
import shop.soccerUniform.repository.manager.ManagerRepository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ItemOptionRepository itemOptionRepository;
    private final ItemOptionValueRepository itemOptionValueRepository;
    private final ItemOptionStockRepository itemOptionStockRepository;
    private final ManagerRepository managerRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public void saveItem(ItemSaveForm itemSaveForm) {
        // ITEM 생성
        Optional<Manager> managerOptional = managerRepository.findById(itemSaveForm.getManagerId());
        if(managerOptional.isEmpty()) throw new RuntimeException("입점업체가 존재하지 않습니다.");
        Manager manager = managerOptional.get();

        Optional<Category> categoryOptional = categoryRepository.findById(itemSaveForm.getCategoryId());
        if(categoryOptional.isEmpty()) throw new RuntimeException("카테고리가 존재하지 않습니다.");
        Category category = categoryOptional.get();

        Item item = new Item(itemSaveForm.getName(), manager, category, itemSaveForm.getManufacturer(),
                itemSaveForm.getOrigin(), itemSaveForm.getDescription(), itemSaveForm.getOptionType(),
                itemSaveForm.getPrice(), itemSaveForm.getState());
        Item savedItem = itemRepository.save(item);

        if(itemSaveForm.getOptionType() == OptionType.SINGLE) {
            ItemOption savedItemOption = itemOptionRepository
                    .save(new ItemOption(savedItem, itemSaveForm.getFirstOptionName(), 1));
            // TODO 
        }

        if(itemSaveForm.getOptionType() == OptionType.DOUBLE) {

        }
    }

    @Transactional
    @Override
    public void editItem(ItemEditForm itemEditForm, Long itemId) throws IllegalAccessException {

    }

    @Transactional
    @Override
    public void deletedItem(Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        item.deleteItem();
    }

    @Override
    public ItemEditForm detailItem(Long itemId) {
        return null;
    }

    @Override
    public FrontItemForm showItem(Long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if(itemOptional.isPresent()) {
            Item item = itemOptional.get();
            FrontItemForm itemForm = new FrontItemForm();
            itemForm.setItemId(item.getId());
            itemForm.setName(item.getName());
            itemForm.setPrice(item.getPrice());
            itemForm.setManager(item.getManager());
            itemForm.setCategory(item.getCategory());
            itemForm.setOrigin(item.getOrigin());
            itemForm.setOptionType(item.getOptionType());
            itemForm.setManufacturer(item.getManufacturer());
            itemForm.setDescription(item.getDescription());

            for(ItemOption itemOption : item.getItemOptions()) {
                itemOption.getOptionName();
            }
            itemForm.setItemOptions(item.getItemOptions());
            for(ItemOptionValue itemOptionValue : item.getItemOptionValues()) {
                itemOptionValue.getOptionValue();
            }
            itemForm.setItemOptionValues(item.getItemOptionValues());

            return itemForm;
        }
        return null;
    }

    @Override
    public Page<ItemForm> items(ItemSearchForm itemSearchForm, Pageable pageable) {
        return itemRepository.items(itemSearchForm, pageable);
    }
}
