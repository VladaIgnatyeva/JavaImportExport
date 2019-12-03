package Client.View.windowExport;

import Client.Service;
import Models.Export;
import Models.ExportModels.AddExport.AddExportRequest;
import Models.ExportModels.AddExport.AddExportResponse;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class windowExportAdd extends JFrame {

    private JTextField textId_User;
    private JTextField textId_Product;
    private JTextField textId_Buyer;
    private JTextField textWaybill ;
    private JTextField textUnit ;
    private JTextField textPrice;
    private JTextField textNote;
    private MaskFormatter dateFormat = new MaskFormatter("##.##.####");
    private JFormattedTextField dateField = new JFormattedTextField(dateFormat);

    JLabel labelError;

    private Service service = Service.getInstance();


    public windowExportAdd() throws ParseException, java.text.ParseException {
        super("Добавить");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        textId_User = new JTextField("");
        textId_Product = new JTextField("");
        textId_Buyer = new JTextField("");
        textWaybill = new JTextField("");
        textUnit = new JTextField("");
        textPrice = new JTextField("");
        textNote = new JTextField("");


        dateFormat.setPlaceholderCharacter('_');


        JLabel labelData = new JLabel("Дата*");
        JLabel labelId_User = new JLabel("ID-пользователя*");
        JLabel labelId_Product = new JLabel("Код товара*");
        JLabel labelId_Buyer = new JLabel("Код покупателя*");
        JLabel labelWaybill = new JLabel("Номер накладной*");
        JLabel labelUnit = new JLabel("Количество товара*");
        JLabel labelPrice = new JLabel("Цена*");
        JLabel labelNote = new JLabel("Примечание");

        labelError = new JLabel("");

        JButton addButton = new JButton("Добавить");
        JButton cancelButton = new JButton("Отмена");

        cancelButton.addActionListener(new cancelActionListener());
        addButton.addActionListener(new addActionListener());


        this.add(labelData, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(dateField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelId_User, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textId_User, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelId_Product, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textId_Product, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelId_Buyer, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textId_Buyer, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelWaybill, new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textWaybill, new GridBagConstraints(1, 4, 1, 1, 1, 1,
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

        this.add(addButton, new GridBagConstraints(0, 9, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(cancelButton, new GridBagConstraints(1, 9, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));


    }

    public void clearField () {
        textNote.setText("");
        textPrice.setText("");
        textUnit.setText("");
        dateField.setText("");
        textId_User.setText("");;
        textId_Product.setText("");;
        textId_Buyer.setText("");;
        textWaybill.setText("");

        labelError.setText("");
    }

    public class cancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clearField();
            setVisible(false);
        }
    }

    public class addActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(dateField.getText().equals("")  || textPrice.getText().equals("") || textUnit.getText().equals("") || textId_Buyer.getText().equals("") ||
                    textId_Product.getText().equals("") || textId_User.getText().equals("") || textWaybill.getText().equals("")){
                labelError.setText("Заполните все поля.");
            }
            else {
                String date = dateField.getText();
                int id_product  = Integer.parseInt(textId_Product.getText());
                int id_buyer  = Integer.parseInt(textId_Buyer.getText());
                int id_user  = Integer.parseInt(textId_User.getText());
                String price = textPrice.getText();
                String unit = textUnit.getText();
                String waybill = textWaybill.getText();
                String note = textNote.getText();
                Export export = new Export(1, "export", date, id_user, id_product, id_buyer, waybill, unit,price, note);

                try {
                    AddExportResponse result = service.request("AddExport", new AddExportRequest(export), AddExportResponse.class );

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
