package shop.soccerUniform.repository.itemOption;

import shop.soccerUniform.entity.ItemOption;

import java.util.List;

public interface ItemOptionQueryRepository {
    public List<ItemOption> findByItemId(Long itemId);
}
