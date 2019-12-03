package Models.Users.DeleteUser;

import Models.User;

public class DeleteUserRequest {
    private User user;

    public DeleteUserRequest(){  }

    public DeleteUserRequest(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
