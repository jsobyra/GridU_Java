package gridu.jsobyra.market.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderedItem> orderedItems;
    private double total;

    public Order() {
        this.orderedItems = new ArrayList<>();
    }

    public void addToOrder(String title, int amount, double price) {
        orderedItems.add(new OrderedItem(title, amount, price));
        total += amount*price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderedItems=" + orderedItems +
                ", total=" + total +
                '}';
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private class OrderedItem {
        private String title;
        private int amount;
        private double price;

        public OrderedItem(String title, int amount, double price) {
            this.title = title;
            this.amount = amount;
            this.price = price;
        }


        @Override
        public String toString() {
            return "OrderedItem{" +
                    "title='" + title + '\'' +
                    ", amount=" + amount +
                    ", price=" + price +
                    ", totalPrice=" + amount*price +
                    '}';
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}