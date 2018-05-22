package gridu.jsobyra.market.model.database;

import gridu.jsobyra.market.model.NotEnoughAmountOfItemInShop;
import gridu.jsobyra.market.model.Cart;
import gridu.jsobyra.market.model.CartItem;
import gridu.jsobyra.market.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Cart addItemToCartIfPossible(Cart cart, CartItem item) throws NotEnoughAmountOfItemInShop {
        Item shopItem = itemRepository.getOne(item.getId());
        if(ifAmountOfItemIsEnough(item, shopItem)) {
            //updateAmountOfItemInShop(item, shopItem);
            return updateCart(cart, item);
        } else {
            throw new NotEnoughAmountOfItemInShop("Not enough in shop!!!");
        }
    }

    public boolean ifAmountOfItemIsEnough(CartItem cartItem, Item item) {
        if(item.getAvailable() >= cartItem.getQuantity()) return true;
        else return false;
    }

    public void updateAmountOfItemInShop(CartItem cartItem, Item item) {
        long newAvailability = item.getAvailable() - cartItem.getQuantity();
        item.setAvailable(newAvailability);
        itemRepository.save(item);
    }

    public Cart updateCart(Cart cart, CartItem item) {
        cart.addItemToCart(item.getId(), item.getQuantity());
        return cart;
    }

    public Cart removeItemFromCart(Cart cart, Long itemId) {
        cart.removeItemFromCart(itemId);
        return cart;
    }

    public Order checkoutOrder(Cart cart) {
        Order order = new Order();
        for(Long itemId : cart.getItemsInCart().keySet()) {
            Item shopItem = itemRepository.getOne(itemId);
            if(checkAvailabilityOfItemInCart(shopItem, cart)) {
                order.addToOrder(shopItem.getTitle(), cart.getItemsInCart().get(itemId), shopItem.getPrice());
                shopItem.setAvailable(setNewAvailability(shopItem, cart));
                itemRepository.save(shopItem);
            } else return null;
        }
        cart.emptyCart();
        return order;
    }

    public boolean checkAvailabilityOfItemInCart(Item item, Cart cart) {
        return item.getAvailable()>=cart.getItemsInCart().getOrDefault(item.getId(), 0);
    }

    public Long setNewAvailability(Item item, Cart cart) {
        return item.getAvailable() - cart.getItemsInCart().get(item.getId());
    }
}
