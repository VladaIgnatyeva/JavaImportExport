package Models.Users.SearchUser;

import Models.User;

public class SearchUserRequest {
    private User user;

    public SearchUserRequest(){  }

    public SearchUserRequest(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
