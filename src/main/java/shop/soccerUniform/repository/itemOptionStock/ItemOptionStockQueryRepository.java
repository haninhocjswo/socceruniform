package shop.soccerUniform.repository.itemOptionStock;

import shop.soccerUniform.entity.ItemOptionStock;

import java.util.List;

public interface ItemOptionStockQueryRepository {
    public List<ItemOptionStock> findByItemId(Long itemId);
    public void deletedByItemId(Long itemId);
}
