package ma4174h.gre.ac.uk.eztrade.Responses;

import ma4174h.gre.ac.uk.eztrade.Models.Item;
import ma4174h.gre.ac.uk.eztrade.Models.User;

public class ItemResponse {

    private String message;
    private Item item;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
