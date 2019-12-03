package Client.View.windowSearch;

import Client.Service;
import Client.View.TableModel.ExportTableModel;
import Client.View.TableModel.ImportTableModel;
import Client.View.TableModel.TopProductsTableModel;
import Models.*;
import Models.ExportModels.ShowExportByDate.ShowExportByDateRequest;
import Models.ExportModels.ShowExportByDate.ShowExportByDateResponse;
import Models.ExportModels.ShowExportByIDBuyer.ShowExportByIDBuyerRequest;
import Models.ExportModels.ShowExportByIDBuyer.ShowExportByIDBuyerResponse;
import Models.ExportModels.ShowTopExportResponse;
import Models.ImportModels.ShowImportByDate.ShowImportByDateRequest;
import Models.ImportModels.ShowImportByDate.ShowImportByDateResponse;
import Models.ImportModels.ShowTopImportResponse;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;

public class buttonPanel extends JPanel {
    private Service service = Service.getInstance();



    private JTextField textField = new JTextField("");
    private JButton searchButton = new JButton("Поиск");

    private JLabel errorLabel = new JLabel("");

    private ImportTableModel importTableModel = new ImportTableModel();
    private JTable importTable = new JTable(importTableModel);
    private ExportTableModel exportTableModel = new ExportTableModel();
    private JTable exportTable = new JTable(exportTableModel);
    private TopProductsTableModel topProductsTableModel = new TopProductsTableModel();
    private JTable topProductsTable = new JTable(topProductsTableModel);

    JScrollPane import_sTableScrollPane = new JScrollPane(importTable);
    JScrollPane exportTableScrollPane = new JScrollPane(exportTable);
    JScrollPane topProductsTableScrollPane = new JScrollPane(topProductsTable);

    private ButtonGroup radioButtonGroup = new ButtonGroup();
    private JRadioButton importButton = new JRadioButton("Поиск записей импорта по дате");

    private JRadioButton exportButton = new JRadioButton("Поиск записей экспорта по дате");
    private JRadioButton ordersBuyerButton = new JRadioButton("Поиск заказов покупателя (введите код покупателя)");
    private JRadioButton ordersSupplierButton = new JRadioButton("Поиск заказов поставщика (введите код поставщика)");
    private JRadioButton topImportButton = new JRadioButton("Популярные товары импорта");
    private JRadioButton topExportButton = new JRadioButton("Популярные товары экспорта");
    private JRadioButton recordsUserButton = new JRadioButton("Записи пользователя (введите код пользователя)");


    private MaskFormatter dateFormat = new MaskFormatter("##.##.####");
    private JFormattedTextField dateField = new JFormattedTextField(dateFormat);

    public buttonPanel() throws ParseException {
        setLayout(new GridBagLayout());
    }

    public void init(){

        importButton.setActionCommand("importButton");
        exportButton.setActionCommand("exportButton");
        //trafficButton.setActionCommand("trafficButton");
        ordersBuyerButton.setActionCommand("ordersBuyerButton");
        ordersSupplierButton.setActionCommand("ordersSupplierButton");
        topImportButton.setActionCommand("topImportButton");
        topExportButton.setActionCommand("topExportButton");
        recordsUserButton.setActionCommand("recordsUserButton");

        radioButtonGroup.add(importButton);
        radioButtonGroup.add(exportButton);
        //radioButtonGroup.add(trafficButton);
        radioButtonGroup.add(ordersBuyerButton);
        radioButtonGroup.add(ordersSupplierButton);
        radioButtonGroup.add(topImportButton);
        radioButtonGroup.add(topExportButton);
        radioButtonGroup.add(recordsUserButton);

        dateFormat.setPlaceholderCharacter('_');
        errorLabel.setText("");

        importButton.addActionListener(new ButtonActionListener());
        exportButton.addActionListener(new ButtonActionListener());
        //trafficButton.addActionListener(new ButtonActionListener());
        ordersBuyerButton.addActionListener(new ButtonActionListener());
        ordersSupplierButton.addActionListener(new ButtonActionListener());
        topImportButton.addActionListener(new ButtonActionListener());
        topExportButton.addActionListener(new ButtonActionListener());
        recordsUserButton.addActionListener(new ButtonActionListener());

        searchButton.addActionListener(new searchButtonActionListener());

        import_sTableScrollPane.setPreferredSize(new Dimension(900, 300));
        this.add(import_sTableScrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        exportTableScrollPane.setPreferredSize(new Dimension(900, 300));
        this.add(exportTableScrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        topProductsTableScrollPane.setPreferredSize(new Dimension(900, 300));
        this.add(topProductsTableScrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));




        this.add(textField, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 1, 0));

        this.add(dateField, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 1, 0));

        this.add(searchButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(errorLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(importButton, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(exportButton, new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

//        this.add(trafficButton, new GridBagConstraints(0, 5, 1, 1, 1, 1,
//                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
//                new Insets(1, 1, 1, 1), 0, 0));

        this.add(ordersBuyerButton, new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(ordersSupplierButton, new GridBagConstraints(0, 7, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(topImportButton, new GridBagConstraints(0, 8, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(topExportButton, new GridBagConstraints(0, 9, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(recordsUserButton, new GridBagConstraints(0, 10, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
    }


    public class searchButtonActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            {
               String button = radioButtonGroup.getSelection().getActionCommand();

                switch (button){
                    case "importButton":
                        if(dateField.getText().equals(""))  errorLabel.setText("Введите данные. ");
                        else{
                            String date = dateField.getText();
                            Import import_ = new Import(1, "import", date, 1, 1, 1, null, null,null, null);

                            try {
                                ShowImportByDateResponse result = service.request("ShowImportByDate", ShowImportByDateResponse.class );
                                exportTableScrollPane.setVisible(false);
                                import_sTableScrollPane.setVisible(true);
                                topProductsTableScrollPane.setVisible(false);

                                importTableModel.setSource(result.getImport());

                                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ImportByDate.txt"),"UTF8" ));
                                ArrayList<ImportForTable> result_ = new ArrayList<ImportForTable>();
                                result_.addAll(result.getImport());
                                for (int i = 0; i < result_.size(); i++){
                                    bw.write("Код операции импорта: " + result_.get(i).getID_import() + "  Дата: " + result_.get(i).getDate() + "  ID-пользователя: " + result_.get(i).getID_user() + "  Код товара: " + result_.get(i).getID_product() + " Название товара: " + result_.get(i).getName_product() + "  Код поставщика: " + result_.get(i).getID_buyer() + "  Название фирмы: " + result_.get(i).getName_buyer() + "  Номер накладной: " + result_.get(i).getWaybill() + "  Количество товара: " + result_.get(i).getUnit() + "  Цена: " + result_.get(i).getPrice() +  " Примечание: " + result_.get(i).getNote() + "\n");
                                }
                                bw.flush();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        break;
                    case "exportButton":
                        if(dateField.getText().equals(""))  errorLabel.setText("Введите данные. ");
                        else{
                            String date = dateField.getText();
                            Export export = new Export(1, "export", date, 1, 1, 1, null, null,null, null);

                            try {
                                ShowExportByDateResponse result = service.request("ShowExportByDate", new ShowExportByDateRequest(export), ShowExportByDateResponse.class );
                                exportTableScrollPane.setVisible(true);
                                import_sTableScrollPane.setVisible(false);
                                topProductsTableScrollPane.setVisible(false);

                                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ExportByDate.txt"),"UTF8" ));
                                ArrayList<ExportForTable> result_ = new ArrayList<ExportForTable>();
                                result_.addAll(result.getExport());
                                for (int i = 0; i < result_.size(); i++){
                                    bw.write("Код операции экспорта: " + result_.get(i).getID_export() + "  Дата: " + result_.get(i).getDate() + "  ID-пользователя: " + result_.get(i).getID_user() + "  Код товара: " + result_.get(i).getID_product() + " Название товара: " + result_.get(i).getName_product() + "  Код поставщика: " + result_.get(i).getID_buyer() + "  Название фирмы: " + result_.get(i).getName_buyer() + "  Номер накладной: " + result_.get(i).getWaybill() + "  Количество товара: " + result_.get(i).getUnit() + "  Цена: " + result_.get(i).getPrice() +  " Примечание: " + result_.get(i).getNote() + "\n");
                                }
                                bw.flush();
                                exportTableModel.setSource(result.getExport());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        break;
                    case "ordersBuyerButton":
                        if(textField.getText().equals(""))  errorLabel.setText("Введите данные. ");
                        else{
                            int id = Integer.parseInt(textField.getText());

                            Export export = new Export(1, "export", null, 1, 1, id, null, null,null, null);

                            try {
                                ShowExportByIDBuyerResponse result = service.request("ShowExportByIDBuyer", new ShowExportByIDBuyerRequest(export), ShowExportByIDBuyerResponse.class );
                                exportTableScrollPane.setVisible(true);
                                import_sTableScrollPane.setVisible(false);
                                topProductsTableScrollPane.setVisible(false);

                                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("OrdersBuyer.txt"),"UTF8" ));
                                ArrayList<ExportForTable> result_ = new ArrayList<ExportForTable>();
                                result_.addAll(result.getExport());
                                for (int i = 0; i < result_.size(); i++){
                                    bw.write("Код операции экспорта: " + result_.get(i).getID_export() + "  Дата: " + result_.get(i).getDate() + "  ID-пользователя: " + result_.get(i).getID_user() + "  Код товара: " + result_.get(i).getID_product() + " Название товара: " + result_.get(i).getName_product() + "  Код поставщика: " + result_.get(i).getID_buyer() + "  Название фирмы: " + result_.get(i).getName_buyer() + "  Номер накладной: " + result_.get(i).getWaybill() + "  Количество товара: " + result_.get(i).getUnit() + "  Цена: " + result_.get(i).getPrice() +  " Примечание: " + result_.get(i).getNote() + "\n");
                                }
                                bw.flush();
                                exportTableModel.setSource(result.getExport());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        break;
                    case "ordersSupplierButton":
                        if(textField.getText().equals(""))  errorLabel.setText("Введите данные. ");
                        else{
                            int id = Integer.parseInt(textField.getText());

                            Import import_ = new Import(1, "import", null, 1, 1, id, null, null,null, null);

                            try {
                                ShowImportByDateResponse result = service.request("ShowImportByIDSupplier", new ShowImportByDateRequest(import_), ShowImportByDateResponse.class );
                                exportTableScrollPane.setVisible(false);
                                import_sTableScrollPane.setVisible(true);
                                topProductsTableScrollPane.setVisible(false);

                                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("OrdersSupplier.txt"),"UTF8" ));
                                ArrayList<ImportForTable> result_ = new ArrayList<ImportForTable>();
                                result_.addAll(result.getImport());
                                for (int i = 0; i < result_.size(); i++){
                                    bw.write("Код операции импорта: " + result_.get(i).getID_import() + "  Дата: " + result_.get(i).getDate() + "  ID-пользователя: " + result_.get(i).getID_user() + "  Код товара: " + result_.get(i).getID_product() + " Название товара: " + result_.get(i).getName_product() + "  Код поставщика: " + result_.get(i).getID_buyer() + "  Название фирмы: " + result_.get(i).getName_buyer() + "  Номер накладной: " + result_.get(i).getWaybill() + "  Количество товара: " + result_.get(i).getUnit() + "  Цена: " + result_.get(i).getPrice() +  " Примечание: " + result_.get(i).getNote() + "\n");
                                }
                                bw.flush();
                                importTableModel.setSource(result.getImport());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        break;
                    case "topImportButton":
                        try {
                            ShowTopImportResponse result = service.request("GetPopularImport", ShowTopImportResponse.class );
                            exportTableScrollPane.setVisible(false);
                            import_sTableScrollPane.setVisible(false);
                            topProductsTableScrollPane.setVisible(true);

                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("TopImport.txt"),"UTF8" ));
                            ArrayList<TopProduct> result_ = new ArrayList<TopProduct>();
                            result_.addAll(result.getImport());
                            for (int i = 0; i < result_.size(); i++){
                                bw.write("Количество записей: " + result_.get(i).getAmount()  + "  Код товара: " + result_.get(i).getID_product() + "  Название товара: " + result_.get(i).getName() + "  Единица измерения: " + result_.get(i).getUnit() + "  Цена: " + result_.get(i).getPrice() + " Примечание: " + result_.get(i).getNote() + "\n");
                            }
                            bw.flush();

                            topProductsTableModel.setSource(result.getImport());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "topExportButton":
                        try {
                            ShowTopExportResponse result = service.request("GetPopularExport", ShowTopExportResponse.class );
                            exportTableScrollPane.setVisible(false);
                            import_sTableScrollPane.setVisible(false);
                            topProductsTableScrollPane.setVisible(true);

                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("TopExport.txt"),"UTF8" ));
                            ArrayList<TopProduct> result_ = new ArrayList<TopProduct>();
                            result_.addAll(result.getExport());
                            for (int i = 0; i < result_.size(); i++){
                                bw.write("Количество записей: " + result_.get(i).getAmount() + "  Код товара: " + result_.get(i).getID_product() + "  Название товара: " + result_.get(i).getName() + "  Единица измерения: " + result_.get(i).getUnit() + "  Цена: " + result_.get(i).getPrice() + " Примечание: " + result_.get(i).getNote() + "\n");
                            }
                            bw.flush();

                            topProductsTableModel.setSource(result.getExport());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "recordsUserButton":
                        if(textField.getText().equals(""))  errorLabel.setText("Введите данные. ");
                        else{
                            int id = Integer.parseInt(textField.getText());

                            Export export = new Export(1, "export", null, id, 1, 1, null, null,null, null);

                            try {
                                ShowExportByIDBuyerResponse result = service.request("ShowExportByIDUser", new ShowExportByIDBuyerRequest(export), ShowExportByIDBuyerResponse.class );

                                exportTableScrollPane.setVisible(true);
                                import_sTableScrollPane.setVisible(false);
                                topProductsTableScrollPane.setVisible(false);

                                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("RecordsUser.txt"),"UTF8" ));
                                ArrayList<ExportForTable> result_ = new ArrayList<ExportForTable>();
                                result_.addAll(result.getExport());
                                for (int i = 0; i < result_.size(); i++){
                                    bw.write("Код операции экспорта: " + result_.get(i).getID_export() + "  Дата: " + result_.get(i).getDate() + "  ID-пользователя: " + result_.get(i).getID_user() + "  Код товара: " + result_.get(i).getID_product() + " Название товара: " + result_.get(i).getName_product() + "  Код поставщика: " + result_.get(i).getID_buyer() + "  Название фирмы: " + result_.get(i).getName_buyer() + "  Номер накладной: " + result_.get(i).getWaybill() + "  Количество товара: " + result_.get(i).getUnit() + "  Цена: " + result_.get(i).getPrice() +  " Примечание: " + result_.get(i).getNote() + "\n");
                                }
                                bw.flush();


                                exportTableModel.setSource(result.getExport());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        break;
                }
            }
        }
    }

    public class ButtonActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            {
                if(e.getSource() == importButton || e.getSource() == exportButton){
                    dateField.setVisible(true);
                    textField.setVisible(false);
                }
                else {
                    dateField.setVisible(false);
                    textField.setVisible(true);
                }
            }
        }
    }
}
