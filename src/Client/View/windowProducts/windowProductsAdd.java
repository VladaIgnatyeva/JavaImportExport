package Client.View.windowProducts;

import Models.Products.AddProduct.AddProductRequest;
import Models.Products.AddProduct.AddProductResponse;
import Models.Product;
import Client.Service;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class windowProductsAdd extends JFrame {

    JTextField textName ;
    JTextField textUnit ;
    JTextField textPrice ;
    JTextField textNote ;

    JLabel labelError;

    private Service service = Service.getInstance();


    public windowProductsAdd() throws ParseException {
        super("Добавить");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

         textName = new JTextField("");
         textUnit = new JTextField("");
         textPrice = new JTextField("");
         textNote = new JTextField("");

        JLabel labelName = new JLabel("Название товара");
        JLabel labelUnit = new JLabel("Ед. измерения");
        JLabel labelPrice = new JLabel("Цена");
        JLabel labelNote = new JLabel("Примечание");

        labelError = new JLabel("");

        JButton addButton = new JButton("Добавить");
        JButton cancelButton = new JButton("Отмена");

        cancelButton.addActionListener(new cancelActionListener());
        addButton.addActionListener(new addActionListener());


        this.add(labelName, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textName, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelUnit, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textUnit, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelPrice, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textPrice, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelNote, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textNote, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelError, new GridBagConstraints(0, 4, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(addButton, new GridBagConstraints(0, 5, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(cancelButton, new GridBagConstraints(1, 5, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));


    }

    public void clearField () {
        textName.setText("");
        textNote.setText("");
        textPrice.setText("");
        textUnit.setText("");
    }

    public class cancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clearField();
            setVisible(false);
        }
    }

    public class addActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(textName.getText().equals("")  || textPrice.getText().equals("") || textUnit.getText().equals("")){
                labelError.setText("Заполните все поля.");
            }
            else {
                String name = textName.getText();
                String unit = textUnit.getText();
                String price = textPrice.getText();
                String note = textNote.getText();
                Product product = new Product(1, name, unit, price, note);

                try {
                    AddProductResponse result = service.request("AddProduct", new AddProductRequest(product), AddProductResponse.class );

                    if(result.getResult() == true){
                        clearField();
                        setVisible(false);
                    }
                    else{
                        labelError.setText("Запись не добавлена.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        }
    }
}
