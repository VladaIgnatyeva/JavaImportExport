package Client.View.windowSuppliers;

import Client.Service;
import Client.View.TableModel.SuppliersTableModel;
import Client.View.windowProducts.productsPanel;
import Models.Supplier;
import Models.Suppliers.ShowSuppliersResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class suppliersPanel extends JPanel implements Runnable {
    private Service service = Service.getInstance();

    private JButton addButton = new JButton("Добавить");
    private JButton deleteButton = new JButton("Удалить");
    private JButton updateButton = new JButton("Обновить");
    private JButton reportButton = new JButton("Отчёт");


    private SuppliersTableModel ptm = new SuppliersTableModel();
    private  JTable suppliersTable = new JTable(ptm);

    public suppliersPanel() {
        setLayout(new GridBagLayout());

        (new Thread(this)).start();
    }

    public void init () {

        JScrollPane suppliersTableScrollPane = new JScrollPane(suppliersTable);
        suppliersTableScrollPane.setPreferredSize(new Dimension(400, 200));

        addButton.addActionListener(new AddSupplierActionListener());
        deleteButton.addActionListener(new deleteSupplierActionListener());
        updateButton.addActionListener(new updateSupplierActionListener());
        reportButton.addActionListener(new reportProductsActionListener());


        this.add(suppliersTableScrollPane, new GridBagConstraints(0, 0, 3, 1, 1, 1,
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
                ShowSuppliersResponse showSuppliers = service.request("ShowSuppliers", ShowSuppliersResponse.class);
                ptm.setSource(showSuppliers.getSupplier());
                repaint();
                Thread.sleep(1000);
            } catch (InterruptedException | JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class deleteSupplierActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame windowSuppliersDelete = null;
            try {
                windowSuppliersDelete = new windowSuppliersDelete();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            //windowProducts.pack();
            windowSuppliersDelete.setVisible(true);
        }
    }

    public class AddSupplierActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowSuppliersAdd = null;
            try {
                windowSuppliersAdd = new windowSuppliersAdd();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            windowSuppliersAdd.setVisible(true);
        }
    }

    public class updateSupplierActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowSuppliersUpdate = null;
            try {
                windowSuppliersUpdate = new windowSuppiersUpdate();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            //windowProducts.pack();
            windowSuppliersUpdate.setVisible(true);
        }
    }

    public class reportProductsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try
            {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Suppliers.txt"),"UTF8" ));

                ShowSuppliersResponse showSuppliers= service.request("ShowSuppliers", ShowSuppliersResponse.class);
                ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
                suppliers.addAll(showSuppliers.getSupplier());
                for (int i = 0; i < suppliers.size(); i++){
                    bw.write("Код поставщика: " + suppliers.get(i).getID_supplier() + "  Название фирмы: " + suppliers.get(i).getName_firm() + "  Адресс: " + suppliers.get(i).getAddress() + "  Телефон: " + suppliers.get(i).getPhone() + " Примечание: " + suppliers.get(i).getNote() + "\n");
                }

                bw.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }
}
