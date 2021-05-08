package ma4174h.gre.ac.uk.eztrade.Models;

public class Item {


    private Integer id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private Double latitude;
    private Double longitude;
    private Integer userId;


    public Item(String title, String description, String category, Double price, Double latitude, Double longitude, Integer userId) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.longitude = longitude;
        this.latitude = latitude;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
