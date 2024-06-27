package DataAccess;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreator {
    private String path;
    private String connectionString;

    public DataBaseCreator() {
        path = (new File("").getAbsolutePath()).concat("\\SuperLiDB.db");
        connectionString = "jdbc:sqlite:".concat(path);
    }

    public void CreateAllTables() throws SQLException, IOException {
        File dbFile = new File(path);
        dbFile.createNewFile(); // if file already exists will do nothing
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
        try(Statement s = conn.createStatement()) {
            //Supplier Tables
            s.addBatch(createSuppliersTableCommand());
            s.addBatch(createContactsTableCommand());
            s.addBatch(createSupplierAgreementTableCommand());
            s.addBatch(createSupplierItemsDiscountsTableCommand());

            // Order Tables
            s.addBatch(createOrderTableCommand());
            s.addBatch(createOrderItemsTableCommand());

            s.executeBatch();
            conn.close();
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    // Supplier Tables
    private String createSuppliersTableCommand() {
        String command = "CREATE TABLE IF NOT EXISTS Suppliers (" +
                "SupplierId INTEGER NOT NULL UNIQUE," +
                "Name	TEXT NOT NULL,"+
                "BankNumber TEXT NOT NULL," +
                "CompNumber TEXT NOT NULL," +
                "PaymentMethod	TEXT NOT NULL," +
                "Address TEXT NOT NULL," +
                "PRIMARY KEY (SupplierId));";

        return command;
    }

    private String createContactsTableCommand() {
        String command = "CREATE TABLE IF NOT EXISTS Contacts (" +
                "ContactID INTEGER,"+
                "SupplierId INTEGER,"+
                "Name TEXT,"+
                "PhoneNumber	TEXT NOT NULL UNIQUE," +
                "PRIMARY KEY(ContactID,SupplierId)," +
                "FOREIGN KEY(SupplierId) REFERENCES Suppliers(SupplierId));";

        return command;
    }

    private String createSupplierAgreementTableCommand() {
        String command =  "CREATE TABLE IF NOT EXISTS SupplierAgreement ("+
                "SupplierId 	INTEGER,"+
                "CatalogNumber 	INTEGER,"+
                "Price 	REAL NOT NULL,"+
                "Name 	TEXT NOT NULL,"+
                "PRIMARY KEY(SupplierId, CatalogNumber)," +
                "FOREIGN KEY(SupplierId) REFERENCES Suppliers(SupplierId));";

        return command;
    }

    private String createSupplierItemsDiscountsTableCommand() {
        String command =  "CREATE TABLE IF NOT EXISTS SupplierItemsDiscounts  ("+
                "SupplierId 	INTEGER,"+
                "CatalogNumber 	INTEGER,"+
                "Amount 	INTEGER NOT NULL,"+
                "Discounts 	REAL,"+
                "PRIMARY KEY(SupplierId ,CatalogNumber, Amount)," +
                "FOREIGN KEY(SupplierId) REFERENCES SupplierAgreement(SupplierId)," +
                "FOREIGN KEY(CatalogNumber) REFERENCES SupplierAgreement(CatalogNumber));";

        return command;
    }

    // Order tables
    private String createOrderTableCommand() {
        String command =  "CREATE TABLE IF NOT EXISTS Orders  ("+
                "OrderId 	INTEGER NOT NULL UNIQUE,"+
                "SupplierId 	INTEGER NOT NULL,"+
                "Day 	INTEGER NOT NULL,"+
                "ShipmentDate 	Date NOT NULL,"+
                "PRIMARY KEY(OrderId)," +
                "FOREIGN KEY(SupplierId) REFERENCES Suppliers(SupplierId);";

        return command;
    }

    private String createOrderItemsTableCommand() {
        String command =  "CREATE TABLE IF NOT EXISTS OrderItems  ("+
                "OrderId 	INTEGER NOT NULL,"+
                "CatalogNumber 	INTEGER NOT NULL,"+
                "Amount 	INTEGER NOT NULL,"+
                "PRIMARY KEY(CatalogNumber, OrderId)," +
                "FOREIGN KEY(CatalogNumber) REFERENCES SupplierAgreement(CatalogNumber))" +
                "FOREIGN KEY(OrderId) REFERENCES Orders(OrderId))";

        return command;
    }

    public void deleteAllTables() {
        try(Connection conn = DriverManager.getConnection(connectionString);
            Statement s = conn.createStatement()){
            //Supplier Deletes
            s.addBatch( "DROP TABLE IF EXISTS 'Suppliers'");
            s.addBatch("DROP TABLE IF EXISTS 'Contacts'");
            s.addBatch("DROP TABLE IF EXISTS 'SupplierAgreement'");
            s.addBatch("DROP TABLE IF EXISTS 'SupplierItemsDiscounts'");

            //Order Deletes
            s.addBatch("DROP TABLE IF EXISTS 'Orders'");
            s.addBatch("DROP TABLE IF EXISTS 'OrderItems'");

            s.executeBatch();
            conn.commit();
        }
        catch (SQLException e){

        }
    }
}
