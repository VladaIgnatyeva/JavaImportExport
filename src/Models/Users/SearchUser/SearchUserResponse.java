package Models.Users.SearchUser;

import Models.User;

public class SearchUserResponse {
    private User user;

    public SearchUserResponse(){  }

    public SearchUserResponse(User user){
        this.user = user;
    }

    public User getResult() {
        return user;
    }

    public void setResult(User user) {
        this.user = user;
    }
}
