package Client.View.windowUsers;

import Client.Service;
import Models.User;
import Models.Users.DeleteUser.DeleteUserRequest;
import Models.Users.DeleteUser.DeleteUserResponse;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class windowUsersDelete extends JFrame {
    JTextField textName ;
    JTextField textId ;

    JLabel labelError;

    private Service service = Service.getInstance();

    public windowUsersDelete() throws ParseException {
        super("Удалить");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        textName = new JTextField("");
        textId = new JTextField("");


        JLabel labelName = new JLabel("ФИО");
        JLabel labelId = new JLabel("ID");


        labelError = new JLabel("");

        JButton deleteButton = new JButton("Удалить");
        JButton cancelButton = new JButton("Отмена");

        cancelButton.addActionListener(new windowUsersDelete.cancelActionListener());
        deleteButton.addActionListener(new windowUsersDelete.deleteActionListener());


        this.add(labelId, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textId, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelName, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textName, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelError, new GridBagConstraints(0, 2, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(deleteButton, new GridBagConstraints(0, 3, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(cancelButton, new GridBagConstraints(1, 3, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

    }

    public void clearField () {
        textName.setText("");
        textId.setText("");
    }

    public class cancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clearField();
            setVisible(false);
        }
    }

    public class deleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(textName.getText().equals("")  || textId.getText().equals("") ){
                labelError.setText("Заполните все поля.");
            }
            else {
                String name = textName.getText();
                int id = Integer.parseInt(textId.getText());
                User user = new User(id, null, null, name);

                try {
                    DeleteUserResponse result = service.request("DeleteUser", new DeleteUserRequest(user), DeleteUserResponse.class );
                    if(result.getResult() == true){
                        clearField();
                        setVisible(false);
                    }
                    else{
                        labelError.setText("Запись не удалена.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
