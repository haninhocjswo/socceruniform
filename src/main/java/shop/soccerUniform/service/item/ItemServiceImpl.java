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
    public void saveItem(ItemSaveForm itemSaveForm) throws IllegalAccessException {

    }

    @Transactional
    @Override
    public void editItem(ItemEditForm itemEditForm, Long itemId) throws IllegalAccessException {
        Item item = itemRepository.findById(itemId).get();
        Manager manager = managerRepository.findById(itemEditForm.getManagerId()).get();
        Category category = categoryRepository.findById(itemEditForm.getCategoryId()).get();

        item.editItem(itemEditForm.getName(), manager, category, itemEditForm.getManufacturer(), itemEditForm.getOrigin(),
                itemEditForm.getDescription(), itemEditForm.getPrice(), itemEditForm.getState());

        List<ItemOption> itemOptions = itemOptionRepository.findByItemId(itemId);
        Long firstItemOptionId = 0L;
        Long secondItemOptionId = 0L;
        if(itemOptions.size() > 0) {
            for (ItemOption itemOption : itemOptions) {
                if(itemOption.getOptionSort() == 1) {
                    itemOption.editOption(itemEditForm.getFirstOptionName());
                    firstItemOptionId = itemOption.getId();
                }

                if(itemOption.getOptionSort() == 2) {
                    itemOption.editOption(itemEditForm.getSecondOptionName());
                    secondItemOptionId = itemOption.getId();
                }
            }
        }

        itemOptionStockRepository.deletedByItemId(itemId);
        itemOptionValueRepository.deletedByItemId(itemId);

        Field[] fields = itemEditForm.getClass().getDeclaredFields();
        Map<String, Object> itemEditFormMap = new HashMap<>();
        for (Field field : fields) {
            if(field.get(itemEditForm) != null) {
                itemEditFormMap.put(field.getName(), field.get(itemEditForm));
            }
        }

        ItemOption firstItemOption = null;
        String stockSort = "";
        if(item.getOptionType() == OptionType.SINGLE) { // 옵션이 하나인 경우
            firstItemOption = itemOptions.get(0);

            // ITEM_OPTION_VALUE 생성
            String itemOptionValueName = "";
            for(int i = 1; i <= itemEditForm.getItemOption1ValueSize(); i++) {
                itemOptionValueName = "valueName1_" + i;
                if(itemEditFormMap.get(itemOptionValueName) == null) {
                    throw new RuntimeException("상품옵션 종류명을 확인해주세요");
                }

                itemOptionValueRepository.save(new ItemOptionValue(item, firstItemOption, String.valueOf(itemEditFormMap.get(itemOptionValueName)), i));
            }

            // ITEM_OPTION_STOCK 생성
            List<ItemOptionValue> itemOptionValues = itemOptionValueRepository.findByItemOptionId(firstItemOption.getId());
            String itemOptionStockName = "";
            for(int i = 0; i < itemOptionValues.size(); i++) {
                itemOptionStockName = "stock_" + (i+1) + "_0";
                stockSort = (i+1) + "_0";
                if(itemEditFormMap.get(itemOptionStockName) == null) {
                    throw new RuntimeException("상품 재고를 확인해주세요");
                }

                String stockDescription = firstItemOption.getOptionName() + " : " + itemOptionValues.get(i).getOptionValue();
                itemOptionStockRepository.save(new ItemOptionStock(item, itemOptionValues.get(i), null,
                        stockSort, (Integer) itemEditFormMap.get(itemOptionStockName), stockDescription));
            }
        }

        ItemOption secondItemOption = null;
        if(item.getOptionType() == OptionType.DOUBLE) { // 옵션이 두 개인 경우
            firstItemOption = itemOptions.get(0);
            secondItemOption = itemOptions.get(1);

            // ITEM_OPTION_VALUE 생성
            String itemOption1ValueName = "";
            for(int i = 1; i <= itemEditForm.getItemOption1ValueSize(); i++) {
                itemOption1ValueName = "valueName1_" + i;
                if(itemEditFormMap.get(itemOption1ValueName) == null) {
                    throw new RuntimeException("상품옵션 종류명을 확인해주세요");
                }

                itemOptionValueRepository.save(new ItemOptionValue(item, firstItemOption,
                        String.valueOf(itemEditFormMap.get(itemOption1ValueName)), i));
            }

            String itemOption2ValueName = "";
            for(int i = 1; i <= itemEditForm.getItemOption2ValueSize(); i++) {
                itemOption2ValueName = "valueName2_" + i;
                if(itemEditFormMap.get(itemOption2ValueName) == null) {
                    throw new RuntimeException("상품옵션 종류명을 확인해주세요");
                }

                itemOptionValueRepository.save(new ItemOptionValue(item, secondItemOption,
                        String.valueOf(itemEditFormMap.get(itemOption2ValueName)), i));
            }

            // ITEM_OPTION_STOCK 생성
            List<ItemOptionValue> itemOption1Values = itemOptionValueRepository.findByItemOptionId(firstItemOption.getId());
            List<ItemOptionValue> itemOption2Values = itemOptionValueRepository.findByItemOptionId(secondItemOption.getId());
            String itemOptionStockName = "";
            String stockDescription = "";
            for(int i = 0; i < itemOption1Values.size(); i++) {
                for(int k = 0; k < itemOption2Values.size(); k++) {
                    itemOptionStockName = "stock_" + (i+1) + "_" + (k+1);
                    stockSort = (i+1) + "_" + (k+1);
                    if(itemEditFormMap.get(itemOptionStockName) == null) {
                        throw new RuntimeException("상품 재고를 확인해주세요");
                    }

                    stockDescription = firstItemOption.getOptionName() + " : " + itemOption1Values.get(i).getOptionValue() + " | "
                            + secondItemOption.getOptionName() + " : " + itemOption2Values.get(k).getOptionValue();
                    itemOptionStockRepository.save(new ItemOptionStock(item, itemOption1Values.get(i), itemOption2Values.get(k),
                            stockSort, (Integer) itemEditFormMap.get(itemOptionStockName), stockDescription));
                }
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
        Item item = itemRepository.findById(itemId).get();

        ItemEditForm itemEditForm = new ItemEditForm();
        itemEditForm.setItemId(item.getId());
        itemEditForm.setName(item.getName());
        itemEditForm.setOptionType(item.getOptionType());
        itemEditForm.setPrice(item.getPrice());
        itemEditForm.setManagerId(item.getManager().getId());
        itemEditForm.setCategoryId(item.getCategory().getId());
        itemEditForm.setDescription(item.getDescription());
        itemEditForm.setState(item.getState());
        itemEditForm.setOrigin(item.getOrigin());
        itemEditForm.setManufacturer(item.getManufacturer());

        List<ItemOption> itemOptions = itemOptionRepository.findByItemId(item.getId());

        //싱글일 때
        if(itemOptions.size() == 1) {
            List<ItemOptionValue> itemOptionValues = itemOptionValueRepository.findByItemOptionId(itemOptions.get(0).getId());
            itemEditForm.setFirstOptionName(itemOptions.get(0).getOptionName());
            itemEditForm.setItemOption1ValueSize(itemOptionValues.size());

            itemEditForm = pushItemOptionValue(itemEditForm, itemOptionValues, itemOptions.get(0).getOptionSort());

            List<ItemOptionStock> itemOptionStocks = itemOptionStockRepository.findByItemId(itemId);
            if(itemOptionStocks.size() > 0) {
                for (ItemOptionStock itemOptionStock : itemOptionStocks) {
                    itemEditForm = pushItemOptionStock(itemEditForm, itemOptionStock.getSort(), itemOptionStock.getStock());
                }
            }
        }

        //더블일 때
        if(itemOptions.size() == 2) {
            for(int i = 0; i < itemOptions.size(); i++) {
                List<ItemOptionValue> itemOptionValues = itemOptionValueRepository.findByItemOptionId(itemOptions.get(i).getId());
                if(i == 0) {
                    itemEditForm.setFirstOptionName(itemOptions.get(i).getOptionName());
                    itemEditForm.setItemOption1ValueSize(itemOptionValues.size());
                } else if(i == 1) {
                    itemEditForm.setSecondOptionName(itemOptions.get(i).getOptionName());
                    itemEditForm.setItemOption2ValueSize(itemOptionValues.size());
                }

                itemEditForm = pushItemOptionValue(itemEditForm, itemOptionValues, itemOptions.get(i).getOptionSort());
            }
        }

        List<ItemOptionStock> itemOptionStocks = itemOptionStockRepository.findByItemId(itemId);
        if(itemOptionStocks.size() > 0) {
            for (ItemOptionStock itemOptionStock : itemOptionStocks) {
                itemEditForm = pushItemOptionStock(itemEditForm, itemOptionStock.getSort(), itemOptionStock.getStock());
            }
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
            log.info("중간");
            for(ItemOptionValue itemOptionValue : item.getItemOptionValues()) {
                itemOptionValue.getOptionValue();
            }
            itemForm.setItemOptionValues(item.getItemOptionValues());

            //itemForm.setItemOptionStocks(item.getItemOptionStocks());

            return itemForm;
        }

        return null;
    }

    @Override
    public Page<ItemForm> items(ItemSearchForm itemSearchForm, Pageable pageable) {
        return itemRepository.items(itemSearchForm, pageable);
    }

    public ItemEditForm pushItemOptionValue(ItemEditForm itemEditForm, List<ItemOptionValue> itemOptionValues, int sort) {
        if(itemOptionValues.size() > 0) {
            for(int i = 0; i < itemOptionValues.size(); i++) {
                if(sort == 1) {
                    if((i+1) == 1) itemEditForm.setValueName1_1(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 2) itemEditForm.setValueName1_2(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 3) itemEditForm.setValueName1_3(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 4) itemEditForm.setValueName1_4(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 5) itemEditForm.setValueName1_5(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 6) itemEditForm.setValueName1_6(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 7) itemEditForm.setValueName1_7(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 8) itemEditForm.setValueName1_8(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 9) itemEditForm.setValueName1_9(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 10) itemEditForm.setValueName1_10(itemOptionValues.get(i).getOptionValue());
                }

                if(sort == 2) {
                    if((i+1) == 1) itemEditForm.setValueName2_1(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 2) itemEditForm.setValueName2_2(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 3) itemEditForm.setValueName2_3(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 4) itemEditForm.setValueName2_4(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 5) itemEditForm.setValueName2_5(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 6) itemEditForm.setValueName2_6(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 7) itemEditForm.setValueName2_7(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 8) itemEditForm.setValueName2_8(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 9) itemEditForm.setValueName2_9(itemOptionValues.get(i).getOptionValue());
                    if((i+1) == 10) itemEditForm.setValueName2_10(itemOptionValues.get(i).getOptionValue());
                }
            }
        }

        return itemEditForm;
    }

    public ItemEditForm pushItemOptionStock(ItemEditForm itemEditForm, String stockSort, int stock) {
        if(stockSort.equals("1_0")) itemEditForm.setStock_1_0(stock);
        if(stockSort.equals("1_1")) itemEditForm.setStock_1_1(stock);
        if(stockSort.equals("1_2")) itemEditForm.setStock_1_2(stock);
        if(stockSort.equals("1_3")) itemEditForm.setStock_1_3(stock);
        if(stockSort.equals("1_4")) itemEditForm.setStock_1_4(stock);
        if(stockSort.equals("1_5")) itemEditForm.setStock_1_5(stock);
        if(stockSort.equals("1_6")) itemEditForm.setStock_1_6(stock);
        if(stockSort.equals("1_7")) itemEditForm.setStock_1_7(stock);
        if(stockSort.equals("1_8")) itemEditForm.setStock_1_8(stock);
        if(stockSort.equals("1_9")) itemEditForm.setStock_1_9(stock);
        if(stockSort.equals("1_10")) itemEditForm.setStock_1_10(stock);

        if(stockSort.equals("2_0")) itemEditForm.setStock_2_0(stock);
        if(stockSort.equals("2_1")) itemEditForm.setStock_2_1(stock);
        if(stockSort.equals("2_2")) itemEditForm.setStock_2_2(stock);
        if(stockSort.equals("2_3")) itemEditForm.setStock_2_3(stock);
        if(stockSort.equals("2_4")) itemEditForm.setStock_2_4(stock);
        if(stockSort.equals("2_5")) itemEditForm.setStock_2_5(stock);
        if(stockSort.equals("2_6")) itemEditForm.setStock_2_6(stock);
        if(stockSort.equals("2_7")) itemEditForm.setStock_2_7(stock);
        if(stockSort.equals("2_8")) itemEditForm.setStock_2_8(stock);
        if(stockSort.equals("2_9")) itemEditForm.setStock_2_9(stock);
        if(stockSort.equals("2_10")) itemEditForm.setStock_2_10(stock);

        if(stockSort.equals("3_0")) itemEditForm.setStock_3_0(stock);
        if(stockSort.equals("3_1")) itemEditForm.setStock_3_1(stock);
        if(stockSort.equals("3_2")) itemEditForm.setStock_3_2(stock);
        if(stockSort.equals("3_3")) itemEditForm.setStock_3_3(stock);
        if(stockSort.equals("3_4")) itemEditForm.setStock_3_4(stock);
        if(stockSort.equals("3_5")) itemEditForm.setStock_3_5(stock);
        if(stockSort.equals("3_6")) itemEditForm.setStock_3_6(stock);
        if(stockSort.equals("3_7")) itemEditForm.setStock_3_7(stock);
        if(stockSort.equals("3_8")) itemEditForm.setStock_3_8(stock);
        if(stockSort.equals("3_9")) itemEditForm.setStock_3_9(stock);
        if(stockSort.equals("3_10")) itemEditForm.setStock_3_10(stock);

        if(stockSort.equals("4_0")) itemEditForm.setStock_4_0(stock);
        if(stockSort.equals("4_1")) itemEditForm.setStock_4_1(stock);
        if(stockSort.equals("4_2")) itemEditForm.setStock_4_2(stock);
        if(stockSort.equals("4_3")) itemEditForm.setStock_4_3(stock);
        if(stockSort.equals("4_4")) itemEditForm.setStock_4_4(stock);
        if(stockSort.equals("4_5")) itemEditForm.setStock_4_5(stock);
        if(stockSort.equals("4_6")) itemEditForm.setStock_4_6(stock);
        if(stockSort.equals("4_7")) itemEditForm.setStock_4_7(stock);
        if(stockSort.equals("4_8")) itemEditForm.setStock_4_8(stock);
        if(stockSort.equals("4_9")) itemEditForm.setStock_4_9(stock);
        if(stockSort.equals("4_10")) itemEditForm.setStock_4_10(stock);

        if(stockSort.equals("5_0")) itemEditForm.setStock_5_0(stock);
        if(stockSort.equals("5_1")) itemEditForm.setStock_5_1(stock);
        if(stockSort.equals("5_2")) itemEditForm.setStock_5_2(stock);
        if(stockSort.equals("5_3")) itemEditForm.setStock_5_3(stock);
        if(stockSort.equals("5_4")) itemEditForm.setStock_5_4(stock);
        if(stockSort.equals("5_5")) itemEditForm.setStock_5_5(stock);
        if(stockSort.equals("5_6")) itemEditForm.setStock_5_6(stock);
        if(stockSort.equals("5_7")) itemEditForm.setStock_5_7(stock);
        if(stockSort.equals("5_8")) itemEditForm.setStock_5_8(stock);
        if(stockSort.equals("5_9")) itemEditForm.setStock_5_9(stock);
        if(stockSort.equals("5_10")) itemEditForm.setStock_5_10(stock);

        if(stockSort.equals("6_0")) itemEditForm.setStock_6_0(stock);
        if(stockSort.equals("6_1")) itemEditForm.setStock_6_1(stock);
        if(stockSort.equals("6_2")) itemEditForm.setStock_6_2(stock);
        if(stockSort.equals("6_3")) itemEditForm.setStock_6_3(stock);
        if(stockSort.equals("6_4")) itemEditForm.setStock_6_4(stock);
        if(stockSort.equals("6_5")) itemEditForm.setStock_6_5(stock);
        if(stockSort.equals("6_6")) itemEditForm.setStock_6_6(stock);
        if(stockSort.equals("6_7")) itemEditForm.setStock_6_7(stock);
        if(stockSort.equals("6_8")) itemEditForm.setStock_6_8(stock);
        if(stockSort.equals("6_9")) itemEditForm.setStock_6_9(stock);
        if(stockSort.equals("6_10")) itemEditForm.setStock_6_10(stock);

        if(stockSort.equals("7_0")) itemEditForm.setStock_7_0(stock);
        if(stockSort.equals("7_1")) itemEditForm.setStock_7_1(stock);
        if(stockSort.equals("7_2")) itemEditForm.setStock_7_2(stock);
        if(stockSort.equals("7_3")) itemEditForm.setStock_7_3(stock);
        if(stockSort.equals("7_4")) itemEditForm.setStock_7_4(stock);
        if(stockSort.equals("7_5")) itemEditForm.setStock_7_5(stock);
        if(stockSort.equals("7_6")) itemEditForm.setStock_7_6(stock);
        if(stockSort.equals("7_7")) itemEditForm.setStock_7_7(stock);
        if(stockSort.equals("7_8")) itemEditForm.setStock_7_8(stock);
        if(stockSort.equals("7_9")) itemEditForm.setStock_7_9(stock);
        if(stockSort.equals("7_10")) itemEditForm.setStock_7_10(stock);

        if(stockSort.equals("8_0")) itemEditForm.setStock_8_0(stock);
        if(stockSort.equals("8_1")) itemEditForm.setStock_8_1(stock);
        if(stockSort.equals("8_2")) itemEditForm.setStock_8_2(stock);
        if(stockSort.equals("8_3")) itemEditForm.setStock_8_3(stock);
        if(stockSort.equals("8_4")) itemEditForm.setStock_8_4(stock);
        if(stockSort.equals("8_5")) itemEditForm.setStock_8_5(stock);
        if(stockSort.equals("8_6")) itemEditForm.setStock_8_6(stock);
        if(stockSort.equals("8_7")) itemEditForm.setStock_8_7(stock);
        if(stockSort.equals("8_8")) itemEditForm.setStock_8_8(stock);
        if(stockSort.equals("8_9")) itemEditForm.setStock_8_9(stock);
        if(stockSort.equals("8_10")) itemEditForm.setStock_8_10(stock);

        if(stockSort.equals("9_0")) itemEditForm.setStock_9_0(stock);
        if(stockSort.equals("9_1")) itemEditForm.setStock_9_1(stock);
        if(stockSort.equals("9_2")) itemEditForm.setStock_9_2(stock);
        if(stockSort.equals("9_3")) itemEditForm.setStock_9_3(stock);
        if(stockSort.equals("9_4")) itemEditForm.setStock_9_4(stock);
        if(stockSort.equals("9_5")) itemEditForm.setStock_9_5(stock);
        if(stockSort.equals("9_6")) itemEditForm.setStock_9_6(stock);
        if(stockSort.equals("9_7")) itemEditForm.setStock_9_7(stock);
        if(stockSort.equals("9_8")) itemEditForm.setStock_9_8(stock);
        if(stockSort.equals("9_9")) itemEditForm.setStock_9_9(stock);
        if(stockSort.equals("9_10")) itemEditForm.setStock_9_10(stock);

        if(stockSort.equals("10_0")) itemEditForm.setStock_10_0(stock);
        if(stockSort.equals("10_1")) itemEditForm.setStock_10_1(stock);
        if(stockSort.equals("10_2")) itemEditForm.setStock_10_2(stock);
        if(stockSort.equals("10_3")) itemEditForm.setStock_10_3(stock);
        if(stockSort.equals("10_4")) itemEditForm.setStock_10_4(stock);
        if(stockSort.equals("10_5")) itemEditForm.setStock_10_5(stock);
        if(stockSort.equals("10_6")) itemEditForm.setStock_10_6(stock);
        if(stockSort.equals("10_7")) itemEditForm.setStock_10_7(stock);
        if(stockSort.equals("10_8")) itemEditForm.setStock_10_8(stock);
        if(stockSort.equals("10_9")) itemEditForm.setStock_10_9(stock);
        if(stockSort.equals("10_10")) itemEditForm.setStock_10_10(stock);

        return itemEditForm;
    }
}
