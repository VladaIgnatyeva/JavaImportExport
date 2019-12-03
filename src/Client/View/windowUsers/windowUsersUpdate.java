package Client.View.windowUsers;

import Client.Service;
import Models.User;
import Models.Users.SearchUser.SearchUserRequest;
import Models.Users.SearchUser.SearchUserResponse;
import Models.Users.UpdateUser.UpdateUserRequest;
import Models.Users.UpdateUser.UpdateUserResponse;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class windowUsersUpdate extends JFrame {
    private Service service = Service.getInstance();

    private JTextField textId ;
    private JTextField textName ;
    private JTextField textLogin ;
    private JTextField textPassword ;

    private JLabel labelError;
    private JButton updateButton;

    public windowUsersUpdate() throws ParseException {

        super("Обновить");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600, 600));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        textId = new JTextField("");
        textName = new JTextField("");
        textLogin= new JTextField("");
        textPassword = new JTextField("");

        JLabel labelId = new JLabel("Код пользователя");
        JLabel labelLogin = new JLabel("Логин");
        JLabel labelPasswrd = new JLabel("Пароль");
        JLabel labelName = new JLabel("ФИО");

        labelError = new JLabel("");

        JButton searchButton = new JButton("Найти");
        JButton cancelButton = new JButton("Отмена");
        updateButton = new JButton("Обновить");

        cancelButton.addActionListener(new cancelActionListener());
        updateButton.addActionListener(new updateActionListener());
        searchButton.addActionListener(new searchActionListener());

        this.add(labelId, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textId, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(searchButton, new GridBagConstraints(3, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelLogin, new GridBagConstraints(0, 4, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textLogin, new GridBagConstraints(1, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelPasswrd, new GridBagConstraints(0, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textPassword, new GridBagConstraints(1, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelName, new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textName, new GridBagConstraints(1, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelError, new GridBagConstraints(0, 8, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(updateButton, new GridBagConstraints(0, 9, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(cancelButton, new GridBagConstraints(1, 9, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        textName.setEnabled(false);
        textLogin.setEnabled(false);
        textPassword.setEnabled(false);
        updateButton.setEnabled(false);

        pack();
    }

    public void clearField () {
        textName.setText("");
        textLogin.setText("");
        textPassword.setText("");

        textName.setEnabled(false);
        textLogin.setEnabled(false);
        textPassword.setEnabled(false);
        updateButton.setEnabled(false);
    }

    public class cancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clearField();
            setVisible(false);
        }
    }

    public class updateActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(textId.getText().equals("")  ){
                labelError.setText("Введите код пользователя.");
            }
            else{
                int id = Integer.parseInt(textId.getText());
                String name = textName.getText();
                String login = textLogin.getText();
                String password = textPassword.getText();
                User user = new User(id, login, password, name);

                try {
                    UpdateUserResponse result = service.request("UpdateUser", new UpdateUserRequest(user), UpdateUserResponse.class );
                    if(result.getResult() == true){
                        clearField();
                        setVisible(false);
                    }
                    else{
                        labelError.setText("Запись не обновлена.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public class searchActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(textId.getText().equals("")  ){
                labelError.setText("Введите код пользователя.");
            }
            else {
                int id = Integer.parseInt(textId.getText());
                User user = new User(id, null, null,  null);

                try {
                    SearchUserResponse result = service.request("SearchUser", new SearchUserRequest(user), SearchUserResponse.class );

                    textName.setText(result.getResult().getName());
                    textLogin.setText(result.getResult().getLogin());
                    textPassword.setText(result.getResult().getPassword());

                    textName.setEnabled(true);
                    textLogin.setEnabled(true);
                    textPassword.setEnabled(true);
                    updateButton.setEnabled(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
