package shop.soccerUniform.repository.itemOptionValue;

import shop.soccerUniform.entity.ItemOptionValue;

import java.util.List;

public interface ItemOptionValueQueryRepository {
    public List<ItemOptionValue> findByItemOptionId(Long itemOptionId);
}
