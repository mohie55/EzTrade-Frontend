package ma4174h.gre.ac.uk.eztrade.Responses;

import ma4174h.gre.ac.uk.eztrade.Models.SellerDetails;

public class SellerDetailsResponse {

    private SellerDetails sellerDetails;
    private String message;

    public SellerDetailsResponse(SellerDetails sellerDetails, String message) {
        this.sellerDetails = sellerDetails;
        this.message = message;
    }

    public SellerDetails getSellerDetails() {
        return sellerDetails;
    }

    public void setSellerDetails(SellerDetails sellerDetails) {
        this.sellerDetails = sellerDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
