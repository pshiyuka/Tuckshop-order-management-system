import javax.swing.*;
import java.util.ArrayList;
public class Order{

    private int orderID;
    private static int nextID = 1;
    private String customerEmail;

    private ArrayList<String> items;
    private ArrayList<Double> prices;

    public Order(){
        this.items = new ArrayList<>();
        this.prices = new ArrayList<>();
        this.orderID = nextID++;
    }

    public void setCustomerEmail(String email) {
        this.customerEmail = email;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }


    public int getOrderID() {
        return orderID;
    }

    public ArrayList<String> getItems() {
        return items;
    }


    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void addItem(String item, double price) {
        items.add(item);
        prices.add(price);
    }

    public void removeItem(String itemName){
        boolean found = false;

        for (int i = 0; i < items.size(); i++) {
            String currentItem = items.get(i);

            if (currentItem.equalsIgnoreCase(itemName)) {
                JOptionPane.showMessageDialog(null, "Removed"  + currentItem);
                items.remove(i);
                prices.remove(i);
                found = true;
                break;
            }
            else {
                JOptionPane.showMessageDialog(null,"Item not found");
            }
        }

    }

    public double calculateTotal(){
        double total = 0.0;
        for (int i = 0; i < prices.size(); i++) {
            total += prices.get(i);
        }

        return total;
 }
    public int countItem(String itemName) {
        int count = 0;
        for (String name : items) {
            if (name.equals(itemName)) {
                count++;
            }
        }
        return count;
    }


}
