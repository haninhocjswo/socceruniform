package shop.soccerUniform.repository.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.ItemSaveForm;
import shop.soccerUniform.entity.dto.ItemSearchForm;

public interface ItemQueryRepository {
    public Page<ItemForm> items(ItemSearchForm itemSearchForm, Pageable pageable);
}
