package Client.View.windowProducts;


import Client.Service;
import Client.View.TableModel.ProductsTableModel;
import Models.Product;
import Models.Products.ShowProductsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class productsPanel extends JPanel implements Runnable {

    private Service service = Service.getInstance();

    private JButton addButton = new JButton("Добавить");
    private JButton deleteButton = new JButton("Удалить");
    private JButton updateButton = new JButton("Обновить");
    private JButton reportButton = new JButton("Отчёт");


    private ProductsTableModel ptm = new ProductsTableModel();
    private  JTable productsTable = new JTable(ptm);



    public productsPanel() {
        setLayout(new GridBagLayout());

        (new Thread(this)).start();
    }

    public void init () {

        JScrollPane productsTableScrollPane = new JScrollPane(productsTable);
        productsTableScrollPane.setPreferredSize(new Dimension(400, 200));

        addButton.addActionListener(new AddProductsActionListener());
        deleteButton.addActionListener(new deleteProductsActionListener());
        updateButton.addActionListener(new updateProductsActionListener());
        reportButton.addActionListener(new reportProductsActionListener());

        this.add(productsTableScrollPane, new GridBagConstraints(0, 0, 3, 1, 1, 1,
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
                ShowProductsResponse showProducts = service.request("ShowProducts", ShowProductsResponse.class);
                ptm.setSource(showProducts.getProducts());
                repaint();
                Thread.sleep(1000);
            } catch (InterruptedException | JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class deleteProductsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame windowProductsDelete = null;
            try {
                windowProductsDelete = new windowProductsDelete();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            windowProductsDelete.setVisible(true);
        }
    }

    public class AddProductsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowProductsAdd = null;
            try {
                windowProductsAdd = new windowProductsAdd();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            windowProductsAdd.setVisible(true);
        }
    }

    public class updateProductsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JFrame windowProductsUpdate = null;
            try {
                windowProductsUpdate = new windowProductsUpdate();
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }
            windowProductsUpdate.setVisible(true);
        }
    }

    public class reportProductsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try
            {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Products.txt"),"UTF8" ));

                ShowProductsResponse showProducts = service.request("ShowProducts", ShowProductsResponse.class);
                ArrayList<Product> products = new ArrayList<Product>();
                products.addAll(showProducts.getProducts());
                for (int i = 0; i < products.size(); i++){
                    bw.write("Код товара: " + products.get(i).getID_product() + "  Название товара: " + products.get(i).getName() + "  Единица измерения: " + products.get(i).getUnit() + "  Цена: " + products.get(i).getPrice() + " Примечание: " + products.get(i).getNote() + "\n");
                }

                bw.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }
    }
}
