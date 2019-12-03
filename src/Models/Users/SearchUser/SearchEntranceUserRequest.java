package Models.Users.SearchUser;

import Models.User;

public class SearchEntranceUserRequest {
    private User user;

    public SearchEntranceUserRequest(){  }

    public SearchEntranceUserRequest(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
