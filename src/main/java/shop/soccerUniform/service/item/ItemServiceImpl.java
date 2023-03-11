package shop.soccerUniform.service.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.ItemSearchForm;
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
    public void saveItem(ItemForm itemForm) throws IllegalAccessException {
        Manager manager = managerRepository.findById(itemForm.getManagerId()).orElseGet(() -> {
            throw new RuntimeException("선택하신 입점업체가 존재하지 않습니다.");
        });
        Category category = categoryRepository.findById(itemForm.getCategoryId()).orElseGet(() -> {
            throw new RuntimeException("선택하신 카테고리가 존재하지 않습니다.");
        });

        Map<String, Object> formMap = new HashMap<>();
        Field[] fields = itemForm.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.get(itemForm) != null) {
                formMap.put(field.getName(), field.get(itemForm));
            }
        }

        log.info("formMap={}" ,formMap.entrySet());

        Item itemVO = new Item(manager, category, itemForm.getManufacturer(), itemForm.getOrigin(), itemForm.getDescription(), itemForm.getName(),
                                        itemForm.getOptionType(), itemForm.getPrice(), itemForm.getState());
        itemVO.addDate(LocalDateTime.now(), LocalDateTime.now());

        Item item = itemRepository.save(itemVO);

        ItemOption firstItemOption = null;
        if(item.getOptionType() == OptionType.SINGLE) { // 옵션이 하나인 경우
            // ITEM_OPTION 생성
            firstItemOption = itemOptionRepository.save(new ItemOption(item, itemForm.getFirstOptionName(), 1));

            // ITEM_OPTION_VALUE 생성
            String itemOptionValueName = "";
            for(int i = 1; i <= itemForm.getItemOption1ValueSize(); i++) {
                itemOptionValueName = "valueName1_" + i;
                if(formMap.get(itemOptionValueName) == null) {
                    throw new RuntimeException("상품옵션 종류명을 확인해주세요");
                }

                itemOptionValueRepository.save(new ItemOptionValue(item, firstItemOption, String.valueOf(formMap.get(itemOptionValueName)), i));
            }

            // ITEM_OPTION_STOCK 생성
            List<ItemOptionValue> itemOptionValues = itemOptionValueRepository.findByItemOptionId(firstItemOption.getId());
            String itemOptionStockName = "";
            for(int i = 0; i < itemOptionValues.size(); i++) {
                itemOptionStockName = "stock_" + i + "_0";
                if(formMap.get(itemOptionStockName) == null) {
                    throw new RuntimeException("상품 재고를 확인해주세요");
                }

                itemOptionStockRepository.save(new ItemOptionStock(item, itemOptionValues.get(i), null, (Integer) formMap.get(itemOptionStockName)));
            }
        }

        ItemOption secondItemOption = null;
        if(item.getOptionType() == OptionType.DOUBLE) { // 옵션이 두 개인 경우
            // ITEM_OPTION 생성
            firstItemOption = itemOptionRepository.save(new ItemOption(item, itemForm.getFirstOptionName(), 1));
            secondItemOption = itemOptionRepository.save(new ItemOption(item, itemForm.getSecondOptionName(), 2));

            // ITEM_OPTION_VALUE 생성
            String itemOption1ValueName = "";
            for(int i = 1; i <= itemForm.getItemOption1ValueSize(); i++) {
                itemOption1ValueName = "valueName1_" + i;
                if(formMap.get(itemOption1ValueName) == null) {
                    throw new RuntimeException("상품옵션 종류명을 확인해주세요");
                }

                itemOptionValueRepository.save(new ItemOptionValue(item, firstItemOption, String.valueOf(formMap.get(itemOption1ValueName)), i));
            }

            String itemOption2ValueName = "";
            for(int i = 1; i <= itemForm.getItemOption2ValueSize(); i++) {
                itemOption2ValueName = "valueName2_" + i;
                if(formMap.get(itemOption2ValueName) == null) {
                    throw new RuntimeException("상품옵션 종류명을 확인해주세요");
                }

                itemOptionValueRepository.save(new ItemOptionValue(item, firstItemOption, String.valueOf(formMap.get(itemOption2ValueName)), i));
            }

            // ITEM_OPTION_STOCK 생성
            List<ItemOptionValue> itemOption1Values = itemOptionValueRepository.findByItemOptionId(firstItemOption.getId());
            List<ItemOptionValue> itemOption2Values = itemOptionValueRepository.findByItemOptionId(secondItemOption.getId());
            String itemOptionStockName = "";
            for(int i = 1; i <= itemOption1Values.size(); i++) {
                for(int k = 1; k <= itemOption2Values.size(); k++) {
                    itemOptionStockName = "stock_" + i + "_" + k;
                    if(formMap.get(itemOptionStockName) == null) {
                        throw new RuntimeException("상품 재고를 확인해주세요");
                    }
                    itemOptionStockRepository.save(new ItemOptionStock(item, itemOption1Values.get(i), itemOption2Values.get(k), (Integer) formMap.get(itemOptionStockName)));
                }
            }
        }
    }

    @Transactional
    @Override
    public void editItem(ItemForm itemForm, Long itemId) {

    }

    @Transactional
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
