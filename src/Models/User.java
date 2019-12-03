package Models;

import java.io.Serializable;

public class User implements Serializable {
    private  int idUser;
    private String login;
    private String password;
    private String name;

    public User (){}

    public User(int id, String login, String password, String name){
        this.idUser = id;
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
