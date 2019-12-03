package Client.View;

import Client.View.windowBuyers.windowBuyers;
import Client.View.windowExport.windowExport;
import Client.View.windowImport.windowImport;
import Client.View.windowProducts.windowProducts;
import Client.View.windowSearch.windowSearch;
import Client.View.windowSuppliers.windowSuppliers;
import Client.View.windowUsers.windowUsers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;

public class window extends JFrame{

    private boolean admin;

    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("notes.txt"));


    JButton productsButton = new JButton("Все товары");
    JButton buyersButton = new JButton("Покупатели");
    JButton suppliersButton = new JButton("Поставщики");
    JButton importButton = new JButton("Импорт");
    JButton exportButton = new JButton("Экспорт");
    JButton usersButton = new JButton("Пользователи");
    //JButton reportButton = new JButton("Отчёт");
    //JButton trafficButton = new JButton("Все операции");
    JButton searchButton = new JButton("Поиск");

    JButton exitButton = new JButton("Выход");



    public window(boolean admin) throws IOException
    {
        super("Меню");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(1000, 800));
        this.setLocationRelativeTo(null);

        this.admin = admin;
        setLayout(new GridBagLayout());

        this.add(productsButton, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 100, 1, 100), 100, 10));
        this.add(buyersButton, new GridBagConstraints(0, 1, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 100, 1, 100), 100, 10));
        this.add(suppliersButton, new GridBagConstraints(0, 2, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 100, 1, 100), 100, 10));
        this.add(importButton, new GridBagConstraints(0, 3, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 100, 1, 100), 100, 10));
        this.add(exportButton, new GridBagConstraints(0, 4, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 100, 1, 100), 100, 10));
        this.add(usersButton, new GridBagConstraints(0, 5, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 100, 1, 100), 100, 10));
//        this.add(reportButton, new GridBagConstraints(0, 6, 3, 1, 1, 1,
//                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
//                new Insets(20, 100, 1, 100), 100, 10));
//        this.add(trafficButton, new GridBagConstraints(0, 7, 3, 1, 1, 1,
//                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
//                new Insets(20, 100, 1, 100), 100, 10));
        this.add(searchButton, new GridBagConstraints(0, 8, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 100, 1, 100), 100, 10));
        this.add(exitButton, new GridBagConstraints(0, 9, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(20, 100, 20, 100), 100, 10));


        productsButton.addActionListener(new productsActionListener());
        buyersButton.addActionListener(new buyersActionListener());
        suppliersButton.addActionListener(new suppliersActionListener());
        importButton.addActionListener(new importActionListener());
        exportButton.addActionListener(new exportActionListener());
        usersButton.addActionListener(new usersActionListener());
        searchButton.addActionListener(new searchActionListener());

//        reportButton.addActionListener(new OurActionListener());
//        //trafficButton.addActionListener(new OurActionListener());


//        //exitButton.addActionListener(new OurActionListener());
        pack();

    }

    public class productsActionListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           try {
               JFrame windowProducts = new windowProducts();
               windowProducts.setVisible(true);
           } catch (net.minidev.json.parser.ParseException ex) {
               ex.printStackTrace();
           }
        }
    }

    public class buyersActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                JFrame windowBuyers = new windowBuyers();
                windowBuyers.setVisible(true);
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }

        }
    }

    public class suppliersActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                JFrame windowSuppliers = new windowSuppliers();
                windowSuppliers.setVisible(true);
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }

        }
    }

    public class importActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                JFrame windowImport = new windowImport();
                windowImport.setVisible(true);
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }

        }
    }

    public class exportActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                JFrame windowExport = new windowExport();
                windowExport.setVisible(true);
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }

        }
    }

    public class usersActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                JFrame windowUsers = new windowUsers(admin);
                windowUsers.setVisible(true);
            } catch (net.minidev.json.parser.ParseException ex) {
                ex.printStackTrace();
            }

        }
    }

    public class searchActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame windowSearch = null;
            try {
                windowSearch = new windowSearch();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            windowSearch.setVisible(true);
        }
    }
}
