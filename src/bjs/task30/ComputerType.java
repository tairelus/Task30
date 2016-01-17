package bjs.task30;

/**
 * Created by U-1 on 17.01.2016.
 */
public class ComputerType {
    private int id;

    private String title;
    private String typeName;
    private int amount;

    ComputerType (int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String result = "\tType Id: " + getId() + "\n";
        result += "\tTitle: " + getTitle() + "\n";
        result += "\tType: " + getType() + "\n";
        result += "\tAmount: " + getAmount() + "\n\n";

        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return typeName;
    }

    public void setType(String type) {
        this.typeName = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
