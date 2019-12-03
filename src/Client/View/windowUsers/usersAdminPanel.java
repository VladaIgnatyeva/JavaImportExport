package Client.View.windowUsers;

import Client.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class usersAdminPanel extends JPanel {
    private Service service = Service.getInstance();

    private JButton addButton = new JButton("Добавить");
    private JButton deleteButton = new JButton("Удалить");
    private JButton updateButton = new JButton("Обновить");

    public void init () {

        addButton.addActionListener(new AddUserActionListener());
        deleteButton.addActionListener(new deleteUserActionListener());
        updateButton.addActionListener(new updateUserActionListener());


        this.add(addButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(deleteButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(updateButton, new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));


    }

    public class deleteUserActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame windowUsersDelete = null;
            try {
                windowUsersDelete = new windowUsersDelete();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            //windowProducts.pack();
            windowUsersDelete.setVisible(true);
        }
    }

    public class AddUserActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowUsersAdd = null;
            try {
                windowUsersAdd = new windowUsersAdd();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            windowUsersAdd.setVisible(true);
        }
    }

    public class updateUserActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowUsersUpdate = null;
            try {
                windowUsersUpdate = new windowUsersUpdate();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            windowUsersUpdate.setVisible(true);
        }
    }

}
