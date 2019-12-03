package Client.View.windowSearch;

import Client.Service;
import Client.View.windowUsers.usersPanel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class windowSearch extends JFrame {
    private Service service = Service.getInstance();

    public windowSearch() throws ParseException {
        super("Поиск");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600, 500));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        buttonPanel bPanel = new buttonPanel();
        bPanel.init();
        add(bPanel, BorderLayout.CENTER);

//        usersPanel uPanel = new usersPanel();
//        uPanel.init();
//        add(uPanel, BorderLayout.CENTER);

        pack();


    }
}
