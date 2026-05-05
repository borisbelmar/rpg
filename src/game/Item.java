package game;

public class Item {
    private String name;
    private int price;
    private int weight;

    public Item(String name, int price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + " (precio: " + price + ", peso: " + weight + ")";
    }
}
