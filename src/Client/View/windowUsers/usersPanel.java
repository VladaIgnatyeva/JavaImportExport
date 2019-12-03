package Client.View.windowUsers;

import Client.Service;
import Client.View.TableModel.UsersTableModel;
import Models.Users.ShowUsersResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class usersPanel extends JPanel implements Runnable {
    private Service service = Service.getInstance();

    private UsersTableModel ptm = new UsersTableModel();
    private  JTable usersTable = new JTable(ptm);

    public usersPanel() {
        setLayout(new GridBagLayout());

        (new Thread(this)).start();
    }

    public void init () {

        JScrollPane usersTableScrollPane = new JScrollPane(usersTable);
        usersTableScrollPane.setPreferredSize(new Dimension(400, 200));


        this.add(usersTableScrollPane, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));



    }

    @Override
    public void run() {
        while(true){
            try {
                ShowUsersResponse showUsers = service.request("ShowUsers", ShowUsersResponse.class);
                ptm.setSource(showUsers.getUser());
                repaint();
                Thread.sleep(1000);
            } catch (InterruptedException | JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
