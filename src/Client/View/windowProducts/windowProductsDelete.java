package Client.View.windowProducts;

import Client.Service;
import Models.Products.DeleteProduct.DeleteProductRequest;
import Models.Products.DeleteProduct.DeleteProductResponse;
import Models.Product;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class windowProductsDelete extends JFrame {
    JTextField textName ;
    JTextField textId ;

    JLabel labelError;

    private Service service = Service.getInstance();

    public windowProductsDelete() throws ParseException {
        super("Удалить");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        textName = new JTextField("");
        textId = new JTextField("");


        JLabel labelName = new JLabel("Название товара");
        JLabel labelId = new JLabel("ID");


        labelError = new JLabel("");

        JButton deleteButton = new JButton("Удалить");
        JButton cancelButton = new JButton("Отмена");

        cancelButton.addActionListener(new windowProductsDelete.cancelActionListener());
        deleteButton.addActionListener(new windowProductsDelete.deleteActionListener());


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
                Product product = new Product(id, name, null, null, null);

                try {
                    DeleteProductResponse result = service.request("DeleteProduct", new DeleteProductRequest(product), DeleteProductResponse.class );
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
