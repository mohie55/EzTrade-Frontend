package ma4174h.gre.ac.uk.eztrade.DTO;

public class ItemLocationsDTO {

    private int id;
    private Double latitude;
    private Double longitude;
    private Double price;
    private String title;


    public ItemLocationsDTO(int id, Double latitude, Double longitude, Double price, String title) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
