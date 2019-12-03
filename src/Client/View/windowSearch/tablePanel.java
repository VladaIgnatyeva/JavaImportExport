package Client.View.windowSearch;

import Client.Service;
import Client.View.TableModel.ImportTableModel;
import Models.ImportModels.ShowImportByDate.ShowImportByDateResponse;

import javax.swing.*;
import java.awt.*;

public class tablePanel extends JPanel{
    private Service service = Service.getInstance();

    private ImportTableModel importTableModel = new ImportTableModel();
    private JTable importTable = new JTable(importTableModel);

    public tablePanel(){
        setLayout(new GridBagLayout());
    }

    public void init () {
        JScrollPane import_sTableScrollPane = new JScrollPane(importTable);
        import_sTableScrollPane.setPreferredSize(new Dimension(900, 300));

        this.add(import_sTableScrollPane, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        //ShowImportByDateResponse showImports = service.request("ShowImport", ShowImportByDateResponse.class);
        //ptm.setSource(showImports.getImport());
    }

}
