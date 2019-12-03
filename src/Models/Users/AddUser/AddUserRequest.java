package Models.Users.AddUser;


import Models.User;

public class AddUserRequest {
    private User user;

    public AddUserRequest(){  }

    public AddUserRequest(User User){
        this.user = User;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
