package ma4174h.gre.ac.uk.eztrade.Models;

public class ItemImage {

    private int itemId;
    private String downloadUrl;

    public ItemImage(int itemId, String downloadUrl) {
        this.itemId = itemId;
        this.downloadUrl = downloadUrl;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
