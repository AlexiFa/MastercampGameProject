import java.util.ArrayList;
import java.util.List;
public class Inventory {
    private int rows;
    private int cols;
    private int capacity;
    private List<Items> items;



    public Inventory(int rows, int cols, int capacity) {
        this.rows = rows;
        this.cols = cols;
        this.capacity = capacity;
        this.items = new ArrayList<>();

    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getCapacity() {
        return capacity;
    }

    public Items getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public boolean addItem(Items item) {
        if (items.size() < capacity) {
            items.add(item);
            return true;
        }
        return false;
    }




    public void removeItem(Items item) {
        items.remove(item);
    }
}


