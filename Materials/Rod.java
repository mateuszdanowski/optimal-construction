public class Rod {
    private int length, price, id;

    Rod(int length, int price, int id) {
        this.length = length;
        this.price = price;
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}