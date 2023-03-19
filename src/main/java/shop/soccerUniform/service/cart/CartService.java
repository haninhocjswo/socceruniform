package shop.soccerUniform.service.cart;

import shop.soccerUniform.entity.dto.CartForm;

import java.util.List;

public interface CartService {
    public void saveCart(CartForm cartForm);
    public List<CartForm> findCartsByLoginId(String loginId);
}
