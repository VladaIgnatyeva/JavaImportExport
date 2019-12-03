package Client.View.windowUsers;

import Client.Service;
import Models.User;
import Models.Users.AddUser.AddUserRequest;
import Models.Users.AddUser.AddUserResponse;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class windowUsersAdd extends JFrame {
    JTextField textName ;
    JTextField textLogin ;
    JTextField textPassword ;
    //JTextField textNote ;

    JLabel labelError;

    private Service service = Service.getInstance();


    public windowUsersAdd() throws ParseException {
        super("Добавить");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        textName = new JTextField("");
        textLogin = new JTextField("");
        textPassword = new JTextField("");

        JLabel labelName = new JLabel("ФИО");
        JLabel labelLogin = new JLabel("Логин");
        JLabel labelPassword = new JLabel("Пароль");
        labelError = new JLabel("");

        JButton addButton = new JButton("Добавить");
        JButton cancelButton = new JButton("Отмена");

        cancelButton.addActionListener(new cancelActionListener());
        addButton.addActionListener(new addActionListener());
        
        this.add(labelLogin, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textLogin, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelPassword, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textPassword, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelName, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textName, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelError, new GridBagConstraints(0, 4, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(addButton, new GridBagConstraints(0, 5, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(cancelButton, new GridBagConstraints(1, 5, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));


    }

    public void clearField () {
        textName.setText("");
        textLogin.setText("");
        textPassword.setText("");
    }

    public class cancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clearField();
            setVisible(false);
        }
    }

    public class addActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(textName.getText().equals("")  || textPassword.getText().equals("") || textLogin.getText().equals("")){
                labelError.setText("Заполните все поля.");
            }
            else {
                String name = textName.getText();
                String login = textLogin.getText();
                String password = textPassword.getText();
                User user = new User(1, login, password, name);

                try {
                    AddUserResponse result = service.request("AddUser", new AddUserRequest(user), AddUserResponse.class );

                    if(result.getResult() == true){
                        clearField();
                        setVisible(false);
                    }
                    else{
                        labelError.setText("Запись не добавлена.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


}
