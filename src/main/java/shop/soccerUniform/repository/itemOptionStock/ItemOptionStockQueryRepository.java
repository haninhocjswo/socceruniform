package shop.soccerUniform.repository.itemOptionStock;

import shop.soccerUniform.entity.ItemOptionStock;
import shop.soccerUniform.entity.ItemOptionValue;

import java.util.List;
import java.util.Optional;

public interface ItemOptionStockQueryRepository {
    public List<ItemOptionStock> findByItemId(Long itemId);

    public Optional<ItemOptionStock> findByFirstOptionValueAndSecondOptionValue(ItemOptionValue firstItemOptionValue, ItemOptionValue secondItemOptionValue);
    public void deletedByItemId(Long itemId);
}
