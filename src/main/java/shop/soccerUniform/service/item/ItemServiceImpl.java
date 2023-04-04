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
import shop.soccerUniform.entity.enumtype.UseYn;
import shop.soccerUniform.repository.category.CategoryRepository;
import shop.soccerUniform.repository.item.ItemRepository;
import shop.soccerUniform.repository.itemOption.ItemOptionRepository;
import shop.soccerUniform.repository.itemOptionStock.ItemOptionStockRepository;
import shop.soccerUniform.repository.itemOptionValue.ItemOptionValueRepository;
import shop.soccerUniform.repository.manager.ManagerRepository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
                    .save(new ItemOption(savedItem, itemSaveForm.getFirstOptionName(), 1, UseYn.Y));

            String[] option1Values = itemSaveForm.getOption1Values().split(",");
            if(option1Values.length == 0) throw new RuntimeException("옵션1 옵션값을 확인해주세요.");
            for(int i = 0; i < option1Values.length; i++) {
                itemOptionValueRepository.save(new ItemOptionValue(savedItem, savedItemOption, option1Values[i], i + 1, UseYn.Y));
            }

            List<ItemOptionValue> optionValues = itemOptionValueRepository.findByItemOptionId(savedItemOption.getId());
            List<ItemOptionStockForm> itemStocks = itemSaveForm.getItemStocks();
            if(itemStocks.size() == 0) throw new RuntimeException("옵션의 재고값을 확인해주세요.");
            for (ItemOptionStockForm itemStock : itemStocks) {
                String[] sortSplitList = itemStock.getSort().split("_");
                if(sortSplitList.length != 2) throw new RuntimeException("옵션값과 재고의 데이터가 맞지 않습니다.");
                itemOptionStockRepository.save(new ItemOptionStock(savedItem, optionValues.get(Integer.parseInt(sortSplitList[0]) - 1),
                        null, itemStock.getSort(), itemStock.getStock(), itemStock.getDescription()));
            }
        }

        if(itemSaveForm.getOptionType() == OptionType.DOUBLE) {
            ItemOption savedItemOption1 = itemOptionRepository
                    .save(new ItemOption(savedItem, itemSaveForm.getFirstOptionName(), 1, UseYn.Y));
            ItemOption savedItemOption2 = itemOptionRepository
                    .save(new ItemOption(savedItem, itemSaveForm.getSecondOptionName(), 2, UseYn.Y));

            String[] option1Values = itemSaveForm.getOption1Values().split(",");
            if(option1Values.length == 0) throw new RuntimeException("옵션1 옵션값을 확인해주세요.");
            for(int i = 0; i < option1Values.length; i++) {
                itemOptionValueRepository.save(new ItemOptionValue(savedItem, savedItemOption1, option1Values[i], i + 1, UseYn.Y));
            }
            String[] option2Values = itemSaveForm.getOption2Values().split(",");
            if(option2Values.length == 0) throw new RuntimeException("옵션2 옵션값을 확인해주세요.");
            for(int k = 0; k < option2Values.length; k++) {
                itemOptionValueRepository.save(new ItemOptionValue(savedItem, savedItemOption2, option2Values[k], k + 1, UseYn.Y));
            }

            List<ItemOptionValue> option1ValueList = itemOptionValueRepository.findByItemOptionId(savedItemOption1.getId());
            List<ItemOptionValue> option2ValueList = itemOptionValueRepository.findByItemOptionId(savedItemOption2.getId());
            List<ItemOptionStockForm> itemStocks = itemSaveForm.getItemStocks();
            if(itemStocks.size() == 0) throw new RuntimeException("옵션의 재고값을 확인해주세요.");
            for (ItemOptionStockForm itemStock : itemStocks) {
                String[] sortSplitList = itemStock.getSort().split("_");
                if(sortSplitList.length != 2) throw new RuntimeException("옵션값과 재고의 데이터가 맞지 않습니다.");
                itemOptionStockRepository.save(new ItemOptionStock(savedItem, option1ValueList.get(Integer.parseInt(sortSplitList[0]) - 1),
                        option2ValueList.get(Integer.parseInt(sortSplitList[1]) - 1), itemStock.getSort(), itemStock.getStock(), itemStock.getDescription()));
            }
        }
    }

    @Transactional
    @Override
    public void editItem(ItemEditForm itemEditForm, Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));
        Manager manager = managerRepository.findById(itemEditForm.getManagerId()).orElseThrow(() -> new RuntimeException("입점업체가 존재하지 않습니다."));
        Category category = categoryRepository.findById(itemEditForm.getCategoryId()).orElseThrow(() -> new RuntimeException("카테고리가 존재하지 않습니다."));
        item.editItem(itemEditForm.getName(), manager, category, itemEditForm.getManufacturer(), itemEditForm.getOrigin(), itemEditForm.getDescription(), itemEditForm.getPrice(), itemEditForm.getState(), itemEditForm.getOptionType());
        item.editDate(LocalDateTime.now());

        List<ItemOption> itemOptions = item.getItemOptions();
        ItemOption firstOption = null;
        ItemOption secondOption = null;
        for (ItemOption itemOption : itemOptions) {
            if(itemOption.getOptionSort() == 1) {
                itemOption.editOption(itemEditForm.getFirstOptionName());
                firstOption = itemOption;
            }
            if(itemOption.getOptionSort() == 2) {
                itemOption.editOption(itemEditForm.getSecondOptionName());
                secondOption = itemOption;
            }
        }

        // ItemOptionValue와 ItemOptionStock 삭제
        List<ItemOptionValue> delFirstOptionValues = firstOption.getItemOptionValues();
        if(delFirstOptionValues.size() > 0) {
            for (ItemOptionValue firstOptionValue : delFirstOptionValues) {
                firstOptionValue.delItemOptionValue();
            }
        }
        if(itemEditForm.getOptionType() == OptionType.DOUBLE && secondOption != null) {
            List<ItemOptionValue> delSecondOptionValues = secondOption.getItemOptionValues();
            if(delSecondOptionValues.size() > 0) {
                for (ItemOptionValue secondOptionValue : delSecondOptionValues) {
                    secondOptionValue.delItemOptionValue();
                }
            }
        }

        List<ItemOptionStock> delItemOptionStocks = itemOptionStockRepository.findByItemId(itemId);
        if(delItemOptionStocks.size() > 0) {
            for (ItemOptionStock delItemOptionStock : delItemOptionStocks) {
                delItemOptionStock.delItemOptionStock();
            }
        }

        // 입력받은 데이터로 생성
        String[] option1ValueNames = itemEditForm.getOption1Values().split(",");
        if(option1ValueNames.length == 0) throw new RuntimeException("옵션1 옵션값을 확인해주세요.");
        for(int i = 0; i < option1ValueNames.length; i++) {
            ItemOptionValue savedItemOptionValue = itemOptionValueRepository.save(new ItemOptionValue(item, firstOption, option1ValueNames[i], i + 1, UseYn.Y));
        }
        if(itemEditForm.getOptionType() == OptionType.DOUBLE && secondOption != null) {
            String[] option2ValueNames = itemEditForm.getOption2Values().split(",");
            if(option2ValueNames.length == 0) throw new RuntimeException("옵션2 옵션값을 확인해주세요.");
            for(int i = 0; i < option2ValueNames.length; i++) {
                itemOptionValueRepository.save(new ItemOptionValue(item, secondOption, option2ValueNames[i], i + 1, UseYn.Y));
            }
        }

        List<ItemOptionValue> firstOptionValues = itemOptionValueRepository.findByItemOptionId(firstOption.getId());
        if(firstOptionValues.size() == 0) throw new RuntimeException("옵션1 옵션값을 확인해주세요");
        if(itemEditForm.getOptionType() == OptionType.SINGLE) {
            for(int i = 0; i < itemEditForm.getItemStocks().size(); i++) {
                itemOptionStockRepository.save(new ItemOptionStock(item, firstOptionValues.get(i), null, itemEditForm.getItemStocks().get(i).getSort(),
                        itemEditForm.getItemStocks().get(i).getStock(), itemEditForm.getItemStocks().get(i).getDescription()));
            }
        }
        if(itemEditForm.getOptionType() == OptionType.DOUBLE && secondOption != null) {
            List<ItemOptionValue> secondOptionValues = itemOptionValueRepository.findByItemOptionId(secondOption.getId());
            if(secondOptionValues.size() == 0) throw new RuntimeException("옵션2 옵션값을 확인해주세요");
            for(int i = 0; i < itemEditForm.getItemStocks().size(); i++) {
                String[] sortSplit = itemEditForm.getItemStocks().get(i).getSort().split("_");
                itemOptionStockRepository.save(new ItemOptionStock(item, firstOptionValues.get(Integer.parseInt(sortSplit[0]) - 1), secondOptionValues.get(Integer.parseInt(sortSplit[1]) - 1),
                        itemEditForm.getItemStocks().get(i).getSort(), itemEditForm.getItemStocks().get(i).getStock(), itemEditForm.getItemStocks().get(i).getDescription()));
            }
        }
    }

    @Transactional
    @Override
    public void deletedItem(Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        item.deleteItem();
    }

    @Override
    public ItemEditForm detailItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));

        ItemEditForm itemEditForm = new ItemEditForm();
        itemEditForm.setItemId(item.getId());
        itemEditForm.setName(item.getName());
        itemEditForm.setPrice(item.getPrice());
        itemEditForm.setManagerId(item.getManager().getId());
        itemEditForm.setCategoryId(item.getCategory().getId());
        itemEditForm.setManufacturer(item.getManufacturer());
        itemEditForm.setOrigin(item.getOrigin());
        itemEditForm.setOptionType(item.getOptionType());
        itemEditForm.setState(item.getState());
        itemEditForm.setDescription(item.getDescription());

        List<ItemOption> itemOptions = item.getItemOptions();
        ItemOption firstOption = null;
        ItemOption secondOption = null;
        for (ItemOption itemOption : itemOptions) {
            if(itemOption.getOptionSort() == 1) {
                itemEditForm.setFirstOptionName(itemOption.getOptionName());
                firstOption = itemOption;
            }
            if(itemOption.getOptionSort() == 2 && itemEditForm.getOptionType() == OptionType.DOUBLE) {
                itemEditForm.setSecondOptionName(itemOption.getOptionName());
                secondOption = itemOption;
            }
        }

        String option1ValueNames = "";
        String option2ValueNames = "";
        List<ItemOptionValue> itemOptionValues = item.getItemOptionValues();
        for (ItemOptionValue itemOptionValue : itemOptionValues) {
            if(itemOptionValue.getUseYn() == UseYn.Y) {
                if(firstOption.getId() == itemOptionValue.getItemOption().getId()) {
                    if(itemOptionValue.getOptionValueSort() == 1) {
                        option1ValueNames += itemOptionValue.getOptionValue();
                    } else {
                        option1ValueNames += "," + itemOptionValue.getOptionValue();
                    }
                }
                if(item.getOptionType() == OptionType.DOUBLE) {
                    if(secondOption.getId() == itemOptionValue.getItemOption().getId()) {
                        if(itemOptionValue.getOptionValueSort() == 1) {
                            option2ValueNames += itemOptionValue.getOptionValue();
                        } else {
                            option2ValueNames += "," + itemOptionValue.getOptionValue();
                        }
                    }
                }
            }
        }
        itemEditForm.setOption1Values(option1ValueNames);
        if(itemEditForm.getOptionType() == OptionType.DOUBLE) itemEditForm.setOption2Values(option2ValueNames);

        String[] option1ValueNameSplit = option1ValueNames.split(",");
        String[] option2ValueNameSplit = option2ValueNames.split(",");
        List<ItemOptionStock> itemOptionStocks = item.getItemOptionStocks();
        List<ItemOptionStockForm> stockForms = new ArrayList<>();
        for (ItemOptionStock itemOptionStock : itemOptionStocks) {
            if(itemOptionStock.getUseYn() == UseYn.Y) {
                ItemOptionStockForm stockForm = new ItemOptionStockForm();
                stockForm.setStock(itemOptionStock.getStock());
                stockForm.setItemOptionStockId(itemOptionStock.getId());
                stockForm.setSort(itemOptionStock.getSort());
                stockForm.setDescription(itemOptionStock.getDescription());
                String[] sortSplit = itemOptionStock.getSort().split("_");
                stockForm.setFirstOptionName(firstOption.getOptionName());
                stockForm.setFirstOptionValueName(option1ValueNameSplit[Integer.parseInt(sortSplit[0]) - 1]);
                if(item.getOptionType() == OptionType.DOUBLE) {
                    stockForm.setSecondOptionName(secondOption.getOptionName());
                    stockForm.setSecondOptionValueName(option2ValueNameSplit[Integer.parseInt(sortSplit[1]) - 1]);
                }
                stockForms.add(stockForm);
            }
        }
        itemEditForm.setItemStocks(stockForms);

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
