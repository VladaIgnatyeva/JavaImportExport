package Client.View.TableModel;

import Models.User;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class UsersTableModel extends AbstractTableModel {
    private int  columnCount = 3;
    private ArrayList<User> usersArrayList;

    public UsersTableModel() {
        usersArrayList = new ArrayList<User>();
    }


    public void setSource(ArrayList<User> users){
        usersArrayList.clear();
        usersArrayList.addAll(users);
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex) {
            case 0:
                return "Код пользователя";
            case 1:
                return "Логин";
            case 2:
                return "ФИО";
            default:
                return "Другое";
        }
    }

    @Override
    public int getRowCount() {
        return usersArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return usersArrayList.get(rowIndex).getIdUser();
            case 1:
                return usersArrayList.get(rowIndex).getLogin();
            case 2:
                return usersArrayList.get(rowIndex).getName();
            default:
                return "";
        }
    }
}
