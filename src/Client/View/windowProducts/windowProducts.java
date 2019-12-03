package Client.View.windowProducts;

import Client.Service;
import net.minidev.json.parser.ParseException;

import java.awt.*;

import javax.swing.*;

public class windowProducts extends JFrame {

    private Service service = Service.getInstance();

    public windowProducts() throws ParseException {
        super("Товары");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600, 500));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        productsPanel prodPanel = new productsPanel();
        prodPanel.init();

        add(prodPanel, BorderLayout.CENTER);
        pack();
    }
}
