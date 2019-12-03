package Models.Users;

import Models.User;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowUsersResponse implements Serializable {
    private ArrayList<User> user;

    public ShowUsersResponse(){}

    public ShowUsersResponse(ArrayList<User> user){
        this.user = user;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }
}
