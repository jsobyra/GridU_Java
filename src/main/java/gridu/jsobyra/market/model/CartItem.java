package gridu.jsobyra.market.model;

public class CartItem {
    private long id;
    private int quantity;

    public CartItem() {}

    public CartItem(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
