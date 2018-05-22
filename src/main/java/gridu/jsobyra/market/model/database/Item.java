package gridu.jsobyra.market.model.database;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Long id;

        @Column(nullable = false)
        private String title;

        @Column(nullable = false)
        private Long available;

        @Column(nullable = false)
        private Double price;

        public Item(){}

        public Item(Long id, String title, Long available, Double price) {
            this.id = id;
            this.title = title;
            this.available = available;
            this.price = price;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", available=" + available +
                ", price=" + price +
                '}';
    }
}
