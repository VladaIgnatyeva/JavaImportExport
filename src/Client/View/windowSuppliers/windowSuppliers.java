package Client.View.windowSuppliers;

import Client.Service;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;

public class windowSuppliers extends JFrame {
    private Service service = Service.getInstance();

    public windowSuppliers() throws ParseException {
        super("Покупатели");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600, 500));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        suppliersPanel bPanel = new suppliersPanel();
        bPanel.init();

        add(bPanel, BorderLayout.CENTER);
        pack();
    }
}
