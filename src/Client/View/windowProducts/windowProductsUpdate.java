package Client.View.windowProducts;

import Client.Service;
import Models.Product;
import Models.Products.SearchProduct.SearchProductRequest;
import Models.Products.SearchProduct.SearchProductResponse;
import Models.Products.UpdateProduct.UpdateProductRequest;
import Models.Products.UpdateProduct.UpdateProductResponse;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class windowProductsUpdate extends JFrame {
    private Service service = Service.getInstance();

    private JTextField textId ;
    private JTextField textName ;
    private JTextField textUnit ;
    private JTextField textPrice ;
    private JTextField textNote ;

    private JLabel labelError;
    private JButton updateButton;

    public windowProductsUpdate() throws ParseException {

        super("Обновить");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600, 600));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        textId = new JTextField("");
        textName = new JTextField("");
        textUnit = new JTextField("");
        textPrice = new JTextField("");
        textNote = new JTextField("");

        JLabel labelId = new JLabel("Код товара");
        JLabel labelName = new JLabel("Название товара");
        JLabel labelUnit = new JLabel("Ед. измерения");
        JLabel labelPrice = new JLabel("Цена");
        JLabel labelNote = new JLabel("Примечание");

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

        this.add(labelName, new GridBagConstraints(0, 4, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textName, new GridBagConstraints(1, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelUnit, new GridBagConstraints(0, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textUnit, new GridBagConstraints(1, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelPrice, new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textPrice, new GridBagConstraints(1, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelNote, new GridBagConstraints(0, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textNote, new GridBagConstraints(1, 7, 1, 1, 1, 1,
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
        textNote.setEnabled(false);
        textPrice.setEnabled(false);
        textUnit.setEnabled(false);

        updateButton.setEnabled(false);

        pack();
    }

    public void clearField () {
        textName.setText("");
        textNote.setText("");
        textPrice.setText("");
        textUnit.setText("");

        textName.setEnabled(false);
        textNote.setEnabled(false);
        textPrice.setEnabled(false);
        textUnit.setEnabled(false);

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
                labelError.setText("Введите код товара.");
            }
            else{
                int id = Integer.parseInt(textId.getText());
                String name = textName.getText();
                String unit = textUnit.getText();
                String price = textPrice.getText();
                String note = textNote.getText();
                Product product = new Product(id, name, unit, price, note);

                try {
                    UpdateProductResponse result = service.request("UpdateProduct", new UpdateProductRequest(product), UpdateProductResponse.class );
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
                labelError.setText("Введите код товара.");
            }
            else {
                int id = Integer.parseInt(textId.getText());
                Product product = new Product(id, null, null, null, null);

                try {
                    SearchProductResponse result = service.request("SearchProduct", new SearchProductRequest(product), SearchProductResponse.class );

                    textName.setText(result.getResult().getName());
                    textNote.setText(result.getResult().getNote());
                    textPrice.setText(result.getResult().getPrice());
                    textUnit.setText(result.getResult().getUnit());

                    textName.setEnabled(true);
                    textNote.setEnabled(true);
                    textPrice.setEnabled(true);
                    textUnit.setEnabled(true);

                    updateButton.setEnabled(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
