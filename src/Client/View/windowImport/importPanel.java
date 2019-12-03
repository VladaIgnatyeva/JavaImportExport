package Client.View.windowImport;

import Client.Service;
import Client.View.TableModel.ImportTableModel;
import Models.Import;
import Models.ImportForTable;
import Models.ImportModels.ShowImportResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class importPanel extends JPanel implements Runnable {
    private Service service = Service.getInstance();

    private JButton addButton = new JButton("Добавить");
    private JButton deleteButton = new JButton("Удалить");
    private JButton updateButton = new JButton("Обновить");
    private JButton reportButton = new JButton("Отчёт");

    private ImportTableModel ptm = new ImportTableModel();
    private  JTable import_sTable = new JTable(ptm);

    public importPanel() {
        setLayout(new GridBagLayout());

        (new Thread(this)).start();
    }

    public void init () {

        JScrollPane import_sTableScrollPane = new JScrollPane(import_sTable);
        import_sTableScrollPane.setPreferredSize(new Dimension(900, 300));

        addButton.addActionListener(new AddImportActionListener());
        deleteButton.addActionListener(new deleteImportActionListener());
        updateButton.addActionListener(new updateImportActionListener());
        reportButton.addActionListener(new reportProductsActionListener());



        this.add(import_sTableScrollPane, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(addButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(deleteButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(updateButton, new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(reportButton, new GridBagConstraints(3, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

    }

    @Override
    public void run() {
        while(true){
            try {
                ShowImportResponse showImports = service.request("ShowImport", ShowImportResponse.class);
                ptm.setSource(showImports.getImport());
                repaint();
                Thread.sleep(1000);
            } catch (InterruptedException | JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class deleteImportActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame windowImportDelete = null;
            try {
                windowImportDelete = new windowImportDelete();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            windowImportDelete.setVisible(true);
        }
    }

    public class AddImportActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowImportAdd = null;
            try {
                windowImportAdd = new windowImportAdd();
            } catch (ParseException | java.text.ParseException ex) {
                ex.printStackTrace();
            }
            windowImportAdd.setVisible(true);
        }
    }

    public class updateImportActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowImportUpdate = null;
            try {
                windowImportUpdate = new windowImportUpdate();
            } catch (ParseException | java.text.ParseException ex) {
                ex.printStackTrace();
            }
            //windowProducts.pack();
            windowImportUpdate.setVisible(true);
        }
    }

    public class reportProductsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try
            {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Import.txt"),"UTF8" ));

                ShowImportResponse showImport= service.request("ShowImport", ShowImportResponse.class);
                ArrayList<ImportForTable> import_ = new ArrayList<ImportForTable>();
                import_.addAll(showImport.getImport());
                for (int i = 0; i < import_.size(); i++){
                    bw.write("Код операции импорта: " + import_.get(i).getID_import() + "  Дата: " + import_.get(i).getDate() + "  ID-пользователя: " + import_.get(i).getID_user() + "  Код товара: " + import_.get(i).getID_product() + " Название товара: " + import_.get(i).getName_product() + "  Код поставщика: " + import_.get(i).getID_buyer() + "  Название фирмы: " + import_.get(i).getName_buyer() + "  Номер накладной: " + import_.get(i).getWaybill() + "  Количество товара: " + import_.get(i).getUnit() + "  Цена: " + import_.get(i).getPrice() +  " Примечание: " + import_.get(i).getNote() + "\n");
                }

                bw.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }
}
