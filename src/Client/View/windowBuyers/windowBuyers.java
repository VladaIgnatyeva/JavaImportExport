package Client.View.windowBuyers;

import Client.Service;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;

public class windowBuyers extends JFrame {
    private Service service = Service.getInstance();

    public windowBuyers() throws ParseException {
        super("Покупатели");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600, 500));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        buyersPanel bPanel = new buyersPanel();
        bPanel.init();

        add(bPanel, BorderLayout.CENTER);
        pack();
    }
}
