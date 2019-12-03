package Client.View.windowImport;

import Client.Service;
import Models.Import;
import Models.ImportModels.DeleteImport.DeleteImportRequest;
import Models.ImportModels.DeleteImport.DeleteImportResponse;
import net.minidev.json.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class windowImportDelete extends JFrame {
    JTextField textId ;

    JLabel labelError;

    private Service service = Service.getInstance();

    public windowImportDelete() throws ParseException {
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

        cancelButton.addActionListener(new windowImportDelete.cancelActionListener());
        deleteButton.addActionListener(new windowImportDelete.deleteActionListener());


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
                Import import_ = new Import(id, null, null, 1, 1, 1, null, null, null, null);

                try {
                    DeleteImportResponse result = service.request("DeleteImport", new DeleteImportRequest(import_), DeleteImportResponse.class );
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
