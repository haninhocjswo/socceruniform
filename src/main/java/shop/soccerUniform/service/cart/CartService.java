package shop.soccerUniform.service.cart;

import shop.soccerUniform.entity.dto.CartForm;

import java.util.List;

public interface CartService {
    public boolean saveCart(CartForm cartForm);
    public boolean editCart(Long cartId, Integer stock);
    public boolean delCart(Long cartId);
    public List<CartForm> findCartsByLoginId(String loginId);
}
