package Client.View.windowExport;

import Client.Service;
import Client.View.TableModel.ExportTableModel;
import Models.ExportForTable;
import Models.ExportModels.ShowExportResponse;
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

public class exportPanel extends JPanel implements Runnable {
    private Service service = Service.getInstance();

    private JButton addButton = new JButton("Добавить");
    private JButton deleteButton = new JButton("Удалить");
    private JButton updateButton = new JButton("Обновить");
    private JButton reportButton = new JButton("Отчёт");

    private ExportTableModel ptm = new ExportTableModel();
    private  JTable exportsTable = new JTable(ptm);

    public exportPanel() {
        setLayout(new GridBagLayout());

        (new Thread(this)).start();
    }

    public void init () {

        JScrollPane exportsTableScrollPane = new JScrollPane(exportsTable);
        exportsTableScrollPane.setPreferredSize(new Dimension(900, 300));

        addButton.addActionListener(new AddExportActionListener());
        deleteButton.addActionListener(new deleteExportActionListener());
        updateButton.addActionListener(new updateExportActionListener());
        reportButton.addActionListener(new reportProductsActionListener());



        this.add(exportsTableScrollPane, new GridBagConstraints(0, 0, 3, 1, 1, 1,
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
                ShowExportResponse showExports = service.request("ShowExport", ShowExportResponse.class);
                ptm.setSource(showExports.getExport());
                repaint();
                Thread.sleep(1000);
            } catch (InterruptedException | JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class deleteExportActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame windowExportDelete = null;
            try {
                windowExportDelete = new windowExportDelete();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            windowExportDelete.setVisible(true);
        }
    }

    public class AddExportActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowExportAdd = null;
            try {
                windowExportAdd = new windowExportAdd();
            } catch (ParseException | java.text.ParseException ex) {
                ex.printStackTrace();
            }
            windowExportAdd.setVisible(true);
        }
    }

    public class updateExportActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowExportUpdate = null;
            try {
                windowExportUpdate = new windowExportUpdate();
            } catch (ParseException | java.text.ParseException ex) {
                ex.printStackTrace();
            }
            //windowProducts.pack();
            windowExportUpdate.setVisible(true);
        }
    }

    public class reportProductsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try
            {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Export.txt"),"UTF8" ));

                ShowExportResponse showExport= service.request("ShowExport", ShowExportResponse.class);
                ArrayList<ExportForTable> export = new ArrayList<ExportForTable>();
                export.addAll(showExport.getExport());
                for (int i = 0; i < export.size(); i++){
                    bw.write("Код операции экспорта: " + export.get(i).getID_export() + "  Дата: " + export.get(i).getDate() + "  ID-пользователя: " + export.get(i).getID_user() + "  Код товара: " + export.get(i).getID_product() + " Название товара: " + export.get(i).getName_product() + "  Код покупателя: " + export.get(i).getID_buyer() + "  Название фирмы: " + export.get(i).getName_buyer() + "  Номер накладной: " + export.get(i).getWaybill() + "  Количество товара: " + export.get(i).getUnit() + "  Цена: " + export.get(i).getPrice() +  " Примечание: " + export.get(i).getNote() + "\n");
                }

                bw.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }
}
