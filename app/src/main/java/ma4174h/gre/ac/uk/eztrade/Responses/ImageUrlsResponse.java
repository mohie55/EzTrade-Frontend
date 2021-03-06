package ma4174h.gre.ac.uk.eztrade.Responses;

import java.util.List;

public class ImageUrlsResponse {

    private List<String> imageUrls;
    private String message;

    public ImageUrlsResponse(List<String> listOfImageUrls, String message) {
        this.imageUrls = listOfImageUrls;
        this.message = message;
    }

    public List<String> getListOfImageUrls() {
        return imageUrls;
    }

    public void setListOfImageUrls(List<String> listOfImageUrls) {
        this.imageUrls = listOfImageUrls;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
