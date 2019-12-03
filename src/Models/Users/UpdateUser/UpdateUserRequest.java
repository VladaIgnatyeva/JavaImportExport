package Models.Users.UpdateUser;

import Models.User;

public class UpdateUserRequest {
    private User user;

    public UpdateUserRequest(){  }

    public UpdateUserRequest(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
