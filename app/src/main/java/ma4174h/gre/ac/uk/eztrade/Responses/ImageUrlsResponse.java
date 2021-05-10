package ma4174h.gre.ac.uk.eztrade.Responses;

import java.util.List;

public class ImageUrlsResponse {

    private List<String> listOfImageUrls;
    private String message;

    public ImageUrlsResponse(List<String> listOfImageUrls, String message) {
        this.listOfImageUrls = listOfImageUrls;
        this.message = message;
    }

    public List<String> getListOfImageUrls() {
        return listOfImageUrls;
    }

    public void setListOfImageUrls(List<String> listOfImageUrls) {
        this.listOfImageUrls = listOfImageUrls;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
