package ma4174h.gre.ac.uk.eztrade.Responses;

public class UserContactDetailsResponse {

    private String firstName;
    private String email;
    private int reviews;
    private String message;

    public UserContactDetailsResponse(String firstName, String email, int reviews, String message) {
        this.firstName = firstName;
        this.email = email;
        this.reviews = reviews;
        this.message = message;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }
}
