package Client.View.windowBuyers;

import Client.Service;
import Client.View.TableModel.BuyersTableModel;
import Models.Buyer;
import Models.Buyers.ShowBuyersResponse;
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

public class buyersPanel extends JPanel implements Runnable {
    private Service service = Service.getInstance();

    private JButton addButton = new JButton("Добавить");
    private JButton deleteButton = new JButton("Удалить");
    private JButton updateButton = new JButton("Обновить");
    private JButton reportButton = new JButton("Отчёт");

    private BuyersTableModel ptm = new BuyersTableModel();
    private  JTable buyersTable = new JTable(ptm);

    public buyersPanel() {
        setLayout(new GridBagLayout());

        (new Thread(this)).start();
    }

    public void init () {

        JScrollPane buyersTableScrollPane = new JScrollPane(buyersTable);
        buyersTableScrollPane.setPreferredSize(new Dimension(400, 200));

        addButton.addActionListener(new AddBuyerActionListener());
        deleteButton.addActionListener(new deleteBuyerActionListener());
        updateButton.addActionListener(new updateBuyerActionListener());
        reportButton.addActionListener(new reportProductsActionListener());

        this.add(buyersTableScrollPane, new GridBagConstraints(0, 0, 3, 1, 1, 1,
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
                ShowBuyersResponse showBuyers = service.request("ShowBuyers", ShowBuyersResponse.class);
                ptm.setSource(showBuyers.getBuyers());
                repaint();
                Thread.sleep(1000);
            } catch (InterruptedException | JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class deleteBuyerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame windowBuyersDelete = null;
            try {
                windowBuyersDelete = new windowBuyersDelete();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            //windowProducts.pack();
            windowBuyersDelete.setVisible(true);
        }
    }

    public class AddBuyerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowBuyersAdd = null;
            try {
                windowBuyersAdd = new windowBuyersAdd();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            windowBuyersAdd.setVisible(true);
        }
    }

    public class updateBuyerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowBuyersUpdate = null;
            try {
                windowBuyersUpdate = new windowBuyersUpdate();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            //windowProducts.pack();
            windowBuyersUpdate.setVisible(true);
        }
    }

    public class reportProductsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try
            {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Suppliers.txt"),"UTF8" ));

                ShowBuyersResponse showBuyers= service.request("ShowBuyers", ShowBuyersResponse.class);
                ArrayList<Buyer> buyers = new ArrayList<Buyer>();
                buyers.addAll(showBuyers.getBuyers());
                for (int i = 0; i < buyers.size(); i++){
                    bw.write("Код покупателя: " + buyers.get(i).getID_buyer() + "  Название фирмы: " + buyers.get(i).getName_firm() + "  Адресс: " + buyers.get(i).getAddress() + "  Телефон: " + buyers.get(i).getPhone() + " Примечание: " + buyers.get(i).getNote() + "\n");
                }

                bw.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }
}
