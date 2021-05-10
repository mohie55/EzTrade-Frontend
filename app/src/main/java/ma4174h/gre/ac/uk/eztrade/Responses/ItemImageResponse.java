package ma4174h.gre.ac.uk.eztrade.Responses;

import ma4174h.gre.ac.uk.eztrade.Models.ItemImage;

public class ItemImageResponse {

    private ItemImage itemImage;
    private String message;

    public ItemImageResponse(ItemImage itemImage, String message) {
        this.itemImage = itemImage;
        this.message = message;
    }

    public ItemImage getItemImage() {
        return itemImage;
    }

    public void setItemImage(ItemImage itemImage) {
        this.itemImage = itemImage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
