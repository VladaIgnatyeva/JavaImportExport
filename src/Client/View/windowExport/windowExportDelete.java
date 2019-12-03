package Client.View.windowExport;

import Client.Service;
import Models.Export;
import Models.ExportModels.DeleteExport.DeleteExportRequest;
import Models.ExportModels.DeleteExport.DeleteExportResponse;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class windowExportDelete extends JFrame {
    JTextField textId ;

    JLabel labelError;

    private Service service = Service.getInstance();

    public windowExportDelete() throws ParseException {
        super("Удалить");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        textId = new JTextField("");

        JLabel labelId = new JLabel("ID");

        labelError = new JLabel("");

        JButton deleteButton = new JButton("Удалить");
        JButton cancelButton = new JButton("Отмена");

        cancelButton.addActionListener(new windowExportDelete.cancelActionListener());
        deleteButton.addActionListener(new windowExportDelete.deleteActionListener());


        this.add(labelId, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        this.add(textId, new GridBagConstraints(1, 0, 1, 1, 1, 1,
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
            if( textId.getText().equals("") ){
                labelError.setText("Заполните поле.");
            }
            else {
                int id = Integer.parseInt(textId.getText());
                Export export = new Export(id, null, null, 1, 1, 1, null, null, null, null);

                try {
                    DeleteExportResponse result = service.request("DeleteExport", new DeleteExportRequest(export), DeleteExportResponse.class );
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
