package Client.View.windowImport;

import Client.Service;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;

public class windowImport extends JFrame {
    private Service service = Service.getInstance();

    public windowImport() throws ParseException {
        super("Импорт");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(900, 600));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        importPanel bPanel = new importPanel();
        bPanel.init();

        add(bPanel, BorderLayout.CENTER);
        pack();
    }
}
