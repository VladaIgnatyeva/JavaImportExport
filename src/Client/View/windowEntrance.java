package Client.View;

import Client.Service;
import Models.User;
import Models.Users.SearchUser.SearchEntranceUserRequest;
import Models.Users.SearchUser.SearchEntranceUserResponse;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

public class windowEntrance extends JFrame  {

    JButton button;
    JTextField loginField;
    JPasswordField passwordField;

    private Service service = Service.getInstance();

    public windowEntrance(){

        super("Вход в систему");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Настраиваем первую горизонтальную панель (для ввода логина)
        Box box1 = Box.createHorizontalBox();
        JLabel loginLabel = new JLabel("Логин:");
        loginField = new JTextField(15);
        loginField.setText("Admin1");
        box1.add(loginLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(loginField);

        // Настраиваем вторую горизонтальную панель (для ввода пароля)
        Box box2 = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("Пароль:");
        passwordField = new JPasswordField(15);
        passwordField.setText("Admin");
        box2.add(passwordLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(passwordField);

        Box box3 = Box.createHorizontalBox();
        JLabel errorLabel = new JLabel("");
        box3.add(errorLabel);
        box3.add(Box.createHorizontalStrut(12));

        // Настраиваем третью горизонтальную панель (с кнопками)
        Box box4 = Box.createHorizontalBox();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                if(passwordField.getPassword().equals("") || loginField.getText().equals("")){
                    errorLabel.setText("Заполните все поля.");
                }
                else {
                    String login = loginField.getText();
                    String password =new String( passwordField.getPassword());

                    if(login.equals("Admin1") && password.equals("Admin")) {
                        setVisible(false);
                        JFrame window = null;
                        try {
                            window = new window(true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        window.setVisible(true);
                    }

                    User user = new User(1, login, password, null);

                    try {
                        SearchEntranceUserResponse result = service.request("EntranceUser", new SearchEntranceUserRequest(user), SearchEntranceUserResponse.class );

                        if(result.getResult() == true){
                            setVisible(false);
                            JFrame window = null;
                            try {
                                window = new window(false);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            window.setVisible(true);
                        }
                        else{
                            errorLabel.setText("Проверьте введённые данные");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        button = new JButton("Выход");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (JOptionPane.showConfirmDialog(button, "Вы уверены, что хотите выйти?") == JOptionPane.YES_OPTION) setVisible(false);
            }
        });

        box4.add(Box.createHorizontalGlue());
        box4.add(ok);
        box4.add(Box.createHorizontalStrut(18));
       // box3.add(cancel);
       // box3.add(Box.createHorizontalStrut(12));
        box4.add(button);

        // Уточняем размеры компонентов
        loginLabel.setPreferredSize(passwordLabel.getPreferredSize());

        // Размещаем три горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(20));
        mainBox.add(box4);
        setContentPane(mainBox);

        pack();

    }
}
