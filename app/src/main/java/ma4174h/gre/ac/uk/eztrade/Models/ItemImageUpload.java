package ma4174h.gre.ac.uk.eztrade.Models;

public class ItemImageUpload {

    private String imageUrl;
    private String imageName;

    public ItemImageUpload() {
    }

    public ItemImageUpload(String imageName,String imageUrl) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
