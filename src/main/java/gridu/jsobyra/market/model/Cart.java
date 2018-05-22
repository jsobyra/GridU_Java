package gridu.jsobyra.market.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Component
@SessionScope
public class Cart {
    private Map<Long, Integer> itemsInCart = new HashMap<>();

    public Map<Long, Integer> getItemsInCart() {
        return itemsInCart;
    }

    public Map<Long, Integer> removeItemFromCart(long id) {
        if(itemsInCart.containsKey(id))
            itemsInCart.remove(id);

        return itemsInCart;
    }

    public Map<Long, Integer> addItemToCart(long id, int quantity) {
        itemsInCart.put(id, quantity);
        return itemsInCart;
    }

    public void emptyCart() {
        itemsInCart = new HashMap<>();
    }

    public void setItemsInCart(Map<Long, Integer> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "itemsInCart=" + itemsInCart +
                '}';
    }
}
