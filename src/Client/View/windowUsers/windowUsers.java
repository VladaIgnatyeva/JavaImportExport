package Client.View.windowUsers;

import Client.Service;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;

public class windowUsers extends JFrame {
    private Service service = Service.getInstance();
   // private boolean admin;

    public windowUsers(boolean admin) throws ParseException {
        super("Пользователи");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600, 500));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        usersPanel bPanel = new usersPanel();
        usersAdminPanel uPanel = new usersAdminPanel();
        uPanel.init();
        bPanel.init();
        add(bPanel, BorderLayout.CENTER);
        if(admin){
            add(uPanel, BorderLayout.SOUTH);
        }
        pack();
    }
}
