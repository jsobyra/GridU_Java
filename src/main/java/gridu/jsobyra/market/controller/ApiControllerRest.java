package gridu.jsobyra.market.controller;

import gridu.jsobyra.market.model.*;
import gridu.jsobyra.market.model.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ApiControllerRest {
    @Resource
    private Cart cart;
    private UserService userService;
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    public ApiControllerRest(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping(path = "/register")
    public HttpStatus register(@RequestBody UserRequest userRequest) {
        if(userService.checkIfUserExists(userRequest.getUser()))
            return HttpStatus.CONFLICT;
        else {
            userService.saveUser(userRequest.getUser(), userRequest.getPassword());
            return HttpStatus.OK;
        }
    }

    @GetMapping(path = "/login/session")
    public UserSession getSession() {
        return userService.getCurrentSession();
    }

    @GetMapping("/cart")
    public String getCart() {
        return cart.toString();
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/cart")
    public String addToCart(@RequestBody CartItem cartItem) throws NotEnoughAmountOfItemInShop {
        //cart.addItemToCart(cartItem.getId(), cartItem.getQuantity());
        return itemService.addItemToCartIfPossible(cart, cartItem).toString();
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long itemId) throws NotEnoughAmountOfItemInShop{
        //cart.addItemToCart(cartItem.getId(), cartItem.getQuantity());
        return itemService.removeItemFromCart(cart, itemId).toString();
    }

    @GetMapping("/checkout")
    public Order checkoutOrder() {
        return itemService.checkoutOrder(cart);
    }
}
