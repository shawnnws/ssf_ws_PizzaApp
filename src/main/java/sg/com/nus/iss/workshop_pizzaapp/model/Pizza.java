package sg.com.nus.iss.workshop_pizzaapp.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;



// Define our pizza class
public class Pizza implements Serializable {

    @NotNull(message="Must select a pizza")
    private String pizza;

    @NotNull(message="Must select a size")
    private String size;

    @Min(value=1, message="You must order at least 1 pizza")
    @Max(value=10, message="You can only order up to 10 pizzas")
    private int quantity;

    public String getPizza() {
        return pizza;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static Pizza create(JsonObject o) {
        Pizza p = new Pizza();
        p.setPizza(o.getString("pizza"));
        p.setPizza(o.getString("size"));
        p.setPizza(o.getString("quantity"));

        return p;
    }
    
}
