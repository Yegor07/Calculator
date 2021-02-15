package calculator.DataBase;

import calculator.model.DBModel;
import calculator.view_controller.HistoryEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class DBProvider implements DBModel {

    public final static String CHECK_HISTORY = "CREATE TABLE IF NOT EXISTS history (\n" +
            "    id_history INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    current_expression_value VARCHAR(30),\n" +
            "    last_operation VARCHAR(30),\n" +
            "    current_number VARCHAR(30),\n" +
            "    value VARCHAR(60),\n" +
            "    time INTEGER,\n" +
            "    date DATE\n" +
            ");\n";
    public final static String INSERT_HISTORY = "INSERT INTO history(current_expression_value,last_operation,current_number,value,time,date) VALUES(?,?,?,?,?,?)";

    public final static String SELECT_HISTORY = "SELECT * FROM history";

    public final static String DELETE_HISTORY = "DELETE FROM history WHERE id_history = ?";


    private ObservableList<HistoryEntity> recordList;

    private HashMap<HistoryEntity, Integer> recordMap;

    private Connection connection;

    public DBProvider() {

        try {
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", "calculator.db"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void FetchData(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_HISTORY);
            ArrayList<HistoryEntity> dbList = new ArrayList<>();
            recordMap = new HashMap<>();
            while (rs.next()) {
                HistoryEntity r = new HistoryEntity(this);
                r.setCurrentExpressionValue(rs.getString("current_expression_value"));
                r.setLastOperation(rs.getString("last_operation"));
                r.setCurrentNumber(rs.getString("current_number"));
                r.setValue(rs.getString("value"));
                r.setTime(rs.getLong("time"));
                r.setDate( new java.util.Date(rs.getDate("date").getTime()));
                recordMap.put(r, rs.getInt("id_history"));
                dbList.add(r);
            }
            recordList = FXCollections.observableArrayList(dbList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void checkTables() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(CHECK_HISTORY);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getTables() {
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_HISTORY)) {

            while (rs.next()) {

                System.out.println(rs.getInt("id_history") + "\t" +
                        rs.getString("current_expression_value") + " " +
                        rs.getString("last_operation") + " " +
                        rs.getString("current_number") + " " +
                        rs.getString("value") + " " +
                        rs.getLong("time")+" "+
                        rs.getDate("date"));
            }
            System.out.println("");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertHistory(HistoryEntity record) {

        try {
            PreparedStatement pstmt = connection.prepareStatement(INSERT_HISTORY);
            pstmt.setString(1, record.getCurrentExpressionValue());
            pstmt.setString(2, record.getLastOperation());
            pstmt.setString(3, record.getCurrentNumber());
            pstmt.setString(4, record.getValue());
            pstmt.setLong(5, record.getTime());
            pstmt.setDate(6,new Date(record.getDate().getTime()));
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);

            recordMap.put(record, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void deleteRecordDB(int index) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(DELETE_HISTORY);
            pstmt.setInt(1, index);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addRecord(HistoryEntity record) {
        insertHistory(record);
        this.recordList.add(record);
    }

    public ObservableList<HistoryEntity> getRecordList() {
        return this.recordList;
    }

    @Override
    public void deleteRecord(HistoryEntity record) {
        int id = recordMap.get(record);
        this.recordList.remove(record);
        deleteRecordDB(id);
        getTables();
    }
}
