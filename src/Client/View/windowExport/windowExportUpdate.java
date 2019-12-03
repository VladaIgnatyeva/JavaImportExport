package Client.View.windowExport;

import Client.Service;
import Models.Export;
import Models.ExportModels.SearchExport.SearchExportRequest;
import Models.ExportModels.SearchExport.SearchExportResponse;
import Models.ExportModels.UpdateExport.UpdateExportRequest;
import Models.ExportModels.UpdateExport.UpdateExportResponse;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class windowExportUpdate extends JFrame {
    private Service service = Service.getInstance();

    private JTextField textId;
    private JTextField textId_User;
    private JTextField textId_Product;
    private JTextField textId_Buyer;
    private JTextField textWaybill ;
    private JTextField textUnit ;
    private JTextField textPrice;
    private JTextField textNote;
    private MaskFormatter dateFormat = new MaskFormatter("####-##-##");
    private JFormattedTextField dataField = new JFormattedTextField(dateFormat);

    JLabel labelError;
    private JButton updateButton;

    public windowExportUpdate() throws ParseException, java.text.ParseException {

        super("Обновить");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600, 600));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        textId_User = new JTextField("");
        textId_Product = new JTextField("");
        textId_Buyer = new JTextField("");
        textWaybill = new JTextField("");
        textUnit = new JTextField("");
        textPrice = new JTextField("");
        textNote = new JTextField("");
        textId = new JTextField("");

        dateFormat.setPlaceholderCharacter('_');


        JLabel labelId = new JLabel("Код операции импорта*");
        JLabel labelData = new JLabel("Дата");
        JLabel labelId_User = new JLabel("ID-пользователя");
        JLabel labelId_Product = new JLabel("Код товара");
        JLabel labelId_Buyer = new JLabel("Код поставщика");
        JLabel labelWaybill = new JLabel("Номер накладной");
        JLabel labelUnit = new JLabel("Количество товара");
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

        this.add(labelData, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(dataField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelId_User, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textId_User, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelId_Product, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textId_Product, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelId_Buyer, new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textId_Buyer, new GridBagConstraints(1, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelWaybill, new GridBagConstraints(0, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textWaybill, new GridBagConstraints(1, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelUnit, new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textUnit, new GridBagConstraints(1, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelPrice, new GridBagConstraints(0, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textPrice, new GridBagConstraints(1, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelNote, new GridBagConstraints(0, 8, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textNote, new GridBagConstraints(1, 8, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(labelError, new GridBagConstraints(0, 9, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(updateButton, new GridBagConstraints(0, 10, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(cancelButton, new GridBagConstraints(1, 10, 1, 1, 0.5, 0.5,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        textId_Buyer.setEnabled(false);
        textNote.setEnabled(false);
        textPrice.setEnabled(false);
        textUnit.setEnabled(false);
        textId_Product.setEnabled(false);
        textId_User.setEnabled(false);
        textWaybill.setEnabled(false);

        updateButton.setEnabled(false);

        pack();
    }

    public void clearField () {
        textNote.setText("");
        textPrice.setText("");
        textUnit.setText("");
        dataField.setText("");
        textId_User.setText("");;
        textId_Product.setText("");;
        textId_Buyer.setText("");;
        textWaybill.setText("");

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
                String date = dataField.getText();
                int id_product  = Integer.parseInt(textId_Product.getText());
                int id_buyer  = Integer.parseInt(textId_Buyer.getText());
                int id_user  = Integer.parseInt(textId_User.getText());
                String price = textPrice.getText();
                String unit = textUnit.getText();
                String waybill = textWaybill.getText();
                String note = textNote.getText();
                Export export = new Export(id, "export", date, id_user, id_product, id_buyer, waybill, unit,price, note);

                try {
                    UpdateExportResponse result = service.request("UpdateExport", new UpdateExportRequest(export), UpdateExportResponse.class );
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
                labelError.setText("Введите код операции.");
            }
            else {
                int id = Integer.parseInt(textId.getText());
                Export export = new Export(id, null, null, 1, 1, 1, null, null, null, null);

                try {
                    SearchExportResponse result = service.request("SearchExport", new SearchExportRequest(export), SearchExportResponse.class );

                    textNote.setText(result.getResult().getNote());
                    textPrice.setText(result.getResult().getPrice());
                    textUnit.setText(result.getResult().getUnit());
                    textId_Buyer.setText(Integer.toString(result.getResult().getID_buyer()));
                    textId_Product.setText(Integer.toString(result.getResult().getID_product()));
                    textId_User.setText(Integer.toString(result.getResult().getId_user()));
                    dataField.setText(result.getResult().getDate());
                    textWaybill.setText(result.getResult().getWaybill());

                    textId_Buyer.setEnabled(true);
                    textNote.setEnabled(true);
                    textPrice.setEnabled(true);
                    textUnit.setEnabled(true);
                    textId_Product.setEnabled(true);
                    textId_User.setEnabled(true);
                    dataField.setEnabled(true);
                    textWaybill.setEnabled(true);

                    updateButton.setEnabled(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
