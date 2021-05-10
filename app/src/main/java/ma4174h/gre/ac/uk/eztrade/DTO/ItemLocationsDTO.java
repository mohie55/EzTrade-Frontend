package ma4174h.gre.ac.uk.eztrade.DTO;

public class ItemLocationsDTO {

    private int itemId;
    private Double latitude;
    private Double longitude;
    private Double price;
    private String title;


    public ItemLocationsDTO(int itemId, Double latitude, Double longitude, Double price, String title) {
        this.itemId = itemId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
