package ma4174h.gre.ac.uk.eztrade.Responses;

import ma4174h.gre.ac.uk.eztrade.Models.User;

public class LoginResponse {

    private String message;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
