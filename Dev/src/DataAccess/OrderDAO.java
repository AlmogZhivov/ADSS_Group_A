package DataAccess;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OrderDAO {

    private final String tableName = "Orders";
    private String path;
    private String connectionString;
    public OrderDAO()
    {
        connect();
    }

    protected Connection connect() {
        path = (new File("").getAbsolutePath()).concat("\\SuperLiDB.db");
        connectionString = "jdbc:sqlite:".concat(path);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionString);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String getTableName() {
        return tableName;
    }

    public boolean insert(OrderDTO orderDTO) {
        String command = "INSERT INTO " + tableName + " (OrderId, SupplierId, Day, ShipmentDate) VALUES (" + orderDTO.getOrderId() + ", '" + orderDTO.getSupplierId() + "', '" + orderDTO.getDay() + "', '" + orderDTO.getShipmentDate() + "');";
        try (Connection conn = connect(); java.sql.Statement s = conn.createStatement()) {
            s.execute(command);
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(int orderId) {
        String command = "DELETE FROM " + tableName + " WHERE OrderId = " + orderId + ";";
        try (Connection conn = connect(); java.sql.Statement s = conn.createStatement()) {
            s.execute(command);
            return true;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }




}
