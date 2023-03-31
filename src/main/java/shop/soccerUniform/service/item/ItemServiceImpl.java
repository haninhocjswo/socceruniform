package shop.soccerUniform.service.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
        item.addDate(LocalDateTime.now(), LocalDateTime.now());
        Item savedItem = itemRepository.save(item);

        if(itemSaveForm.getOptionType() == OptionType.SINGLE) {
            ItemOption savedItemOption = itemOptionRepository
                    .save(new ItemOption(savedItem, itemSaveForm.getFirstOptionName(), 1));

            String[] option1Values = itemSaveForm.getOption1Values().split(",");
            if(option1Values.length > 0) throw new RuntimeException("옵션1 옵션값을 확인해주세요.");
            for(int i = 0; i < option1Values.length; i++) {
                itemOptionValueRepository.save(new ItemOptionValue(savedItem, savedItemOption, option1Values[i], i + 1));
            }

            List<ItemOptionValue> optionValues = itemOptionValueRepository.findByItemOptionId(savedItemOption.getId());
            List<ItemOptionStockForm> itemStocks = itemSaveForm.getItemStocks();
            if(itemStocks.size() <= 0) throw new RuntimeException("옵션의 재고값을 확인해주세요.");
            for (ItemOptionStockForm itemStock : itemStocks) {
                String[] sortSplitList = itemStock.getSort().split("_");
                if(sortSplitList.length != 2) throw new RuntimeException("옵션값과 재고의 데이터가 맞지 않습니다.");
                itemOptionStockRepository.save(new ItemOptionStock(savedItem, optionValues.get(Integer.parseInt(sortSplitList[0]) - 1),
                        null, itemStock.getSort(), itemStock.getStock(), itemStock.getDescription()));
            }
        }

        if(itemSaveForm.getOptionType() == OptionType.DOUBLE) {
            ItemOption savedItemOption1 = itemOptionRepository
                    .save(new ItemOption(savedItem, itemSaveForm.getFirstOptionName(), 1));
            ItemOption savedItemOption2 = itemOptionRepository
                    .save(new ItemOption(savedItem, itemSaveForm.getSecondOptionName(), 2));

            String[] option1Values = itemSaveForm.getOption1Values().split(",");
            if(option1Values.length <= 0) throw new RuntimeException("옵션1 옵션값을 확인해주세요.");
            for(int i = 0; i < option1Values.length; i++) {
                itemOptionValueRepository.save(new ItemOptionValue(savedItem, savedItemOption1, option1Values[i], i + 1));
            }
            String[] option2Values = itemSaveForm.getOption2Values().split(",");
            if(option2Values.length <= 0) throw new RuntimeException("옵션2 옵션값을 확인해주세요.");
            for(int k = 0; k < option2Values.length; k++) {
                itemOptionValueRepository.save(new ItemOptionValue(savedItem, savedItemOption2, option2Values[k], k + 1));
            }

            List<ItemOptionValue> option1ValueList = itemOptionValueRepository.findByItemOptionId(savedItemOption1.getId());
            List<ItemOptionValue> option2ValueList = itemOptionValueRepository.findByItemOptionId(savedItemOption2.getId());
            List<ItemOptionStockForm> itemStocks = itemSaveForm.getItemStocks();
            if(itemStocks.size() <= 0) throw new RuntimeException("옵션의 재고값을 확인해주세요.");
            for (ItemOptionStockForm itemStock : itemStocks) {
                String[] sortSplitList = itemStock.getSort().split("_");
                log.info("확인1={}", itemStock.getSort());
                log.info("확인2={}", sortSplitList.length);
                if(sortSplitList.length != 2) throw new RuntimeException("옵션값과 재고의 데이터가 맞지 않습니다.");
                itemOptionStockRepository.save(new ItemOptionStock(savedItem, option1ValueList.get(Integer.parseInt(sortSplitList[0]) - 1),
                        option2ValueList.get(Integer.parseInt(sortSplitList[1]) - 1), itemStock.getSort(), itemStock.getStock(), itemStock.getDescription()));
            }
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
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if(itemOptional.isEmpty()) throw new RuntimeException("해당 상품을 찾을 수 없습니다.");

        ItemEditForm itemEditForm = new ItemEditForm();
        Item item = itemOptional.get();
        for(ItemOption itemOption : item.getItemOptions()) {
            if(itemOption.getOptionSort() == 1) itemEditForm.setFirstOptionName(itemOption.getOptionName());
            if(itemOption.getOptionSort() == 2) itemEditForm.setSecondOptionName(itemOption.getOptionName());
        }

        String option1Values = "";
        String option2Values = "";
        for(ItemOptionValue itemOptionValue : item.getItemOptionValues()) {
            if(itemOptionValue.getItemOption().getOptionSort() == 1) {
                if(itemOptionValue.getOptionValueSort() == 1) {
                    option1Values += itemOptionValue.getOptionValue();
                } else {
                    option1Values += "," + itemOptionValue.getOptionValue();
                }
            }
            if(itemOptionValue.getItemOption().getOptionSort() == 2) {
                if(itemOptionValue.getOptionValueSort() == 1) {
                    option2Values += itemOptionValue.getOptionValue();
                } else {
                    option2Values += "," + itemOptionValue.getOptionValue();
                }
            }
        }
        itemEditForm.setOption1Values(option1Values);
        itemEditForm.setOption2Values(option2Values);

        for(ItemOptionStock itemOptionStock : item.getItemOptionStocks()) {
            ItemOptionStockForm stockForm = new ItemOptionStockForm();
            stockForm.setItemOptionStockId(itemOptionStock.getId());
            stockForm.setItemId(item.getId());
            stockForm.setFirstItemOptionId(itemOptionStock.getFirstOptionValue().getId());
            stockForm.setSecondItemOptionId(itemOptionStock.getSecondOptionValue().getId());
            stockForm.setAddPrice(0);
            stockForm.setStock(itemOptionStock.getStock());
            stockForm.setSort(itemOptionStock.getSort());
            stockForm.setDescription(itemOptionStock.getDescription());
            if(item.getOptionType() == OptionType.SINGLE) {
                String[] descSplit = itemOptionStock.getDescription().split(" : ");
                stockForm.setFirstOptionName(descSplit[0]);
                stockForm.setFirstOptionValueName(descSplit[1]);
            }
            if(item.getOptionType() == OptionType.DOUBLE) {
                String[] descSplit = itemOptionStock.getDescription().split(" | ");
                String[] firstDescSplit = descSplit[0].split(" : ");
                String[] secondDescSplit = descSplit[1].split(" : ");
                stockForm.setFirstOptionName(firstDescSplit[0]);
                stockForm.setFirstOptionValueName(firstDescSplit[1]);
                stockForm.setSecondOptionName(secondDescSplit[0]);
                stockForm.setSecondOptionValueName(secondDescSplit[1]);
            }
            itemEditForm.getItemStocks().add(stockForm);
        }

        return itemEditForm;
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
