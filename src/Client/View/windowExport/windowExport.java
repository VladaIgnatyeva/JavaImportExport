package Client.View.windowExport;

import Client.Service;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;

public class windowExport extends JFrame {
    private Service service = Service.getInstance();

    public windowExport() throws ParseException {
        super("Экспорт");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(900, 600));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        exportPanel bPanel = new exportPanel();
        bPanel.init();

        add(bPanel, BorderLayout.CENTER);
        pack();
    }
}
