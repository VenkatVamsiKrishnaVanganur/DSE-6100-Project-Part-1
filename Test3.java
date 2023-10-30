import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class test3  {
  private static Connection connect = null;
  private static Statement statement = null;
  private static PreparedStatement preparedStatement = null;
  private static ResultSet resultSet = null;



 public static void main(String[] args) {



    String sql1 = "DROP TABLE IF EXISTS client";
    String sql2 = "DROP TABLE IF EXISTS quote";
    String sql3 = "DROP TABLE IF EXISTS order";
    String sql4 = "DROP TABLE IF EXISTS bill";
    String sql5 = "DROP TABLE IF EXISTS tree";

    String sql6 = "CREATE TABLE IF NOT EXISTS client " +
                  "(ClientID INT PRIMARY KEY, " +
                  "FirstName VARCHAR(50), " +
                  "LastName VARCHAR(50), " +
                  "Address VARCHAR(100), " +
                  "CreditCardInfo VARCHAR(16), " +
                  "Phone VARCHAR(15), " +
                  "Email VARCHAR(100) UNIQUE)";

    String sql7 = "CREATE TABLE IF NOT EXISTS quote " +
                   "QuoteID INT PRIMARY KEY",+
                    "TreeID INT",+
                    "InitialPrice DECIMAL (10,2)",+
                    "WorkStartDate DATE",+
                    "WorkEndDate DATE",+
                    "Status VARCHAR (20)",+
                    "Note TEXT",+
                    "FOREIGN KEY (TreeID) REFERENCES Tree (TreeID))";

    String sql8 = "CREATE TABLE IF NOT EXISTS order " +
                       "OrderID INT PRIMARY KEY",+
                        "TreeID INT",+
                        "ClientID INT",+
                        "QuoteID INT",+
                        "Status VARCHAR (20)",+
                       "FOREIGN KEY (TreeID) REFERENCES Tree (TreeID)",+
                       "FOREIGN KEY (ClientID) REFERENCES Client (ClientID)",+
                       "FOREIGN KEY (QuoteID) REFERENCES Quote (QuoteID))";

    String sql9 = "CREATE TABLE IF NOT EXISTS bill " +
                       "BillID INT PRIMARY KEY",+
                       "OrderID INT",+
                       "TotalAmount DECIMAL(10,2)",+
                       "Status VARCHAR (20)",+
                       "Note TEXT",+
                       "FOREIGN KEY (OrderID) REFERENCES Order (OrderID))";

    
    String sql10 = "CREATE TABLE IF NOT EXISTS tree " +
                          "TreeID INT PRIMARY KEY",+
                          "ClientID INT",+
                          "Size DECIMAL (5,2)",+
                          "Height DECIMAL (5,2)"+,
                          "Location VARCHAR (100)",+
                          "IsNearHouse BOOLEAN",+
                          "Note TEXT",+
                          "FOREIGN KEY (ClientID) REFERENCES Client (ClientID))";
         
    String sql11 = "INSERT INTO client VALUES (1, 'John', 'Doe', '123 Main St, Detroit, USA', '1234567890123456', '555-555-5555', 'john@gmail.com')";
    String sql12 = "INSERT INTO client VALUES (2, 'Jane', 'Smith', '456 Elm St, Flint, USA', '9876543210987654', '555-123-4567', 'jane@gmail.com')";
    String sql13 = "INSERT INTO client VALUES (3, 'Bob', 'Johnson', '789 Oak St, Chicago, USA', '1111222233334444', '555-987-6543', 'bob@gmail.com')";
    String sql14 = "INSERT INTO client VALUES (4, 'Alice', 'Brown', '246 Maple St, Novi, USA', '5555666677778888', '555-333-2222', 'alice@gmail.com')";
    String sql15 = "INSERT INTO client VALUES (5, 'Charlie', 'Wilson', '789 Pine St, Troy, USA', '4444333322221111', '555-111-4444', 'charlie@gmail.com')";
    String sql16 = "INSERT INTO client VALUES (6, 'Emily', 'Jones', '654 Cedar St, Detroit, USA', '9999888877776666', '555-444-7777', 'emily@gmail.com')";
    String sql17 = "INSERT INTO client VALUES (7, 'Daniel', 'Taylor', '890 Birch St, Flint, USA', '2222333344445555', '555-888-3333', 'daniel@gmail.com')";
    String sql18 = "INSERT INTO client VALUES (8, 'Ella', 'White', '753 Oak St, Chicago, USA', '7777666655554444', '555-222-6666', 'ella@gmail.com')";
    String sql19 = "INSERT INTO client VALUES (9, 'Frank', 'Lee', '125 Pine St, Novi, USA', '3333444455556666', '555-999-2222', 'frank@gmail.com')";
    String sql20 = "INSERT INTO client VALUES (10, 'Grace', 'Smith', '367 Maple St, Troy, USA', '8888777766665555', '555-444-1111', 'grace@gmail.com')";
 
    String sql21 = "INSERT INTO quote VALUES (11, 111, 1200.00, '2023-04-15', '2023-04-20', 'Pending', 'Initial quote for tree pruning')";
    String sql22 = "INSERT INTO quote VALUES (12, 112, 900.50, '2023-05-10', '2023-05-15', 'Accepted', 'Accepted quote for tree removal')";
    String sql23 = "INSERT INTO quote VALUES (13, 113, 600.00, '2023-06-05', '2023-06-10', 'Completed', 'Completed work on pine tree')";
    String sql24 = "INSERT INTO quote VALUES (14, 114, 759.00, '2023-07-15', '2023-07-20', 'Pending', 'Initial quote for tree trimming')";
    String sql25 = "INSERT INTO quote VALUES (15, 115, 1100.00, '2023-08-10', '2023-08-15', 'Accepted', 'Accepted quote for tree pruning')";
    String sql26 = "INSERT INTO quote VALUES (16, 116, 850.00, '2023-09-05', '2023-09-10', 'Completed', 'Completed work on maple tree')";
    String sql27 = "INSERT INTO quote VALUES (17, 117, 950.00, '2023-10-15', '2023-10-20', 'Pending', 'Initial quote for tree maintenance')";
    String sql28 = "INSERT INTO quote VALUES (18, 118, 750.00, '2023-11-10', '2023-11-15', 'Accepted', 'Accepted quote for tree trimming')";
    String sql29 = "INSERT INTO quote VALUES (19, 119, 988.00, '2023-12-05', '2023-12-10', 'Completed', 'Completed work on oak tree')";
    String sql30 = "INSERT INTO quote VALUES (20, 120, 950.00, '2024-01-15', '2024-01-20', 'Pending', 'Initial quote for tree pruning')";

    String sql31 = "INSERT INTO order VALUES (1001, 111, 1, 11, 'Pending')";
    String sql32 = "INSERT INTO order VALUES (1002, 112, 2, 12, 'Accepted')";
    String sql33 = "INSERT INTO order VALUES (1003, 113, 3, 13, 'Completed')";
    String sql34 = "INSERT INTO order VALUES (1004, 114, 4, 14, 'Pending')";
    String sql35 = "INSERT INTO order VALUES (1005, 115, 5, 15, 'Accepted')";
    String sql36 = "INSERT INTO order VALUES (1006, 116, 6, 16, 'Completed')";
    String sql37 = "INSERT INTO order VALUES (1007, 117, 7, 17, 'Pending')";
    String sql38 = "INSERT INTO order VALUES (1008, 118, 8, 18, 'Accepted')";
    String sql39 = "INSERT INTO order VALUES (1009, 119, 9, 19, 'Completed')";
    String sql40 = "INSERT INTO order VALUES (1010, 120, 10, 20, 'Pending')";

    String sql41 = "INSERT INTO bill VALUES (1101, 1001, 1500.00, 'Paid', 'Paid for tree maintenance work')";
    String sql42 = "INSERT INTO bill VALUES (1102, 1002, 1200.50, 'Paid', 'Paid for tree trimming work')";
    String sql43 = "INSERT INTO bill VALUES (1103, 1003, 1750.00, 'Paid', 'Paid for tree pruning work')";
    String sql44 = "INSERT INTO bill VALUES (1104, 1004, 950.00, 'Paid', 'Paid for tree removal work')";
    String sql45 = "INSERT INTO bill VALUES (1105, 1005, 2000.00, 'Paid', 'Paid for tree maintenance work')";
    String sql46 = "INSERT INTO bill VALUES (1106, 1006, 1800.75, 'Paid', 'Paid for tree trimming work')";
    String sql47 = "INSERT INTO bill VALUES (1107, 1007, 1300.00, 'Paid', 'Paid for tree pruning work')";
    String sql48 = "INSERT INTO bill VALUES (1108, 1008, 900.50, 'Paid', 'Paid for tree removal work')";
    String sql49 = "INSERT INTO bill VALUES (1109, 1009, 1600.00, 'Paid', 'Paid for tree maintenance work')";
    String sql50 = "INSERT INTO bill VALUES (1110, 1010, 1850.25, 'Paid', 'Paid for tree trimming work')";

    String sql51 = "INSERT INTO tree VALUES (111, 1, 3.5, 15.0, 'Front Yard', 1, 'Tall oak tree near the house')";
    String sql52 = "INSERT INTO tree VALUES (112, 2, 4.0, 12.0, 'Backyard', 0, 'Maple tree by the garden')";
    String sql53 = "INSERT INTO tree VALUES (113, 3, 2.5, 8.0, 'Front Yard', 1, 'Pine tree next to the driveway')";
    String sql54 = "INSERT INTO tree VALUES (114, 4, 3.0, 10.0, 'Backyard', 0, 'Birch tree near the patio')";
    String sql55 = "INSERT INTO tree VALUES (115, 5, 4.5, 18.0, 'Front Yard', 1, 'Cedar tree by the entrance')";
    String sql56 = "INSERT INTO tree VALUES (116, 6, 3.8, 14.0, 'Backyard', 0, 'Willow tree near the pond')";
    String sql57 = "INSERT INTO tree VALUES (117, 7, 2.0, 6.0, 'Front Yard', 1, 'Fir tree by the mailbox')";
    String sql58 = "INSERT INTO tree VALUES (118, 8, 4.2, 16.0, 'Backyard', 0, 'Hemlock tree near the fence')";
    String sql59 = "INSERT INTO tree VALUES (119, 9, 3.2, 11.0, 'Front Yard', 1, 'Spruce tree near the gazebo')";
    String sql60 = "INSERT INTO tree VALUES (120, 10, 2.8, 9.0, 'Backyard', 0, 'Poplar tree by the pool')";
 


    try {
      System.out.println("Select a table and then print out its content.");
      // This will load the MySQL driver, each DB has its own driver
      // Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/testdb?"
              + "useSSL=false&user=john&password=john1234");

        

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();

      statement.execute(sql1);
      statement.execute(sql2);
      statement.execute(sql3);
      statement.execute(sql4);
      statement.execute(sql5);

      statement.execute(sql6);
      statement.execute(sql7);
      statement.execute(sql8);
      statement.execute(sql9);
      statement.execute(sql10);

      statement.execute(sql11);
      statement.execute(sql12);
      statement.execute(sql13);
      statement.execute(sql14);
      statement.execute(sql15);
      statement.execute(sql16);
      statement.execute(sql17);
      statement.execute(sql18);
      statement.execute(sql19);
      statement.execute(sql20);

      statement.execute(sql21);
      statement.execute(sql22);
      statement.execute(sql23);
      statement.execute(sql24);
      statement.execute(sql25);
      statement.execute(sql26);
      statement.execute(sql27);
      statement.execute(sql28);
      statement.execute(sql29);
      statement.execute(sql30);

      statement.execute(sql31);
      statement.execute(sql32);
      statement.execute(sql33);
      statement.execute(sql34);
      statement.execute(sql35);
      statement.execute(sql36);
      statement.execute(sql37);
      statement.execute(sql38);
      statement.execute(sql39);
      statement.execute(sql40);

      statement.execute(sql41);
      statement.execute(sql42);
      statement.execute(sql43);
      statement.execute(sql44);
      statement execute(sql45);
      statement execute(sql46);
      statement.execute(sql47);
      statement.execute(sql48);
      statement.execute(sql49);
      statement.execute(sql50);

      statement.execute(sql51);
      statement.execute(sql52);
      statement.execute(sql53);
      statement.execute(sql54);
      statement.execute(sql55);
      statement.execute(sql56);
      statement.execute(sql57);
      statement.execute(sql58);
      statement.execute(sql59);
      statement.execute(sql60);




    // After inserting data into tables, add assertions within SQL statements

    // To check uniqueness of email
    String sql61="CREATE TRIGGER UniqueEmailTrigger BEFORE INSERT ON Client " +
        "FOR EACH ROW " +
        "BEGIN " +
        "  IF (SELECT COUNT(*) FROM Client WHERE Email = NEW.Email) > 0 THEN " +
        "    SIGNAL SQLSTATE '45000' " +
        "    SET MESSAGE_TEXT = 'Email must be unique'; " +
        "  END IF; " +
        "END";
    ResultSet rs1 = statement.executeUpdate(sql161)
    System.out.println("Email has been added succesfully!");

    //	Assertion to check Initial Price Range between 100 and 10000  
     String sql62 = "CREATE TRIGGER InitialPriceRangeTrigger BEFORE INSERT ON Quote " +
            "FOR EACH ROW " +
            "BEGIN " +
            "  IF NOT (NEW.InitialPrice BETWEEN 100 AND 10000) THEN " +
            "    SIGNAL SQLSTATE '45000' " +
            "    SET MESSAGE_TEXT = 'Initial Price must be between 100 and 10000'; " +
            "  END IF; " +
            "END";
    ResultSet rs2 =statement.executeUpdate(sql62);
    System.out.println("Initial Price has been added successfully!");  

  // 	Assertion to check if Order Status is valid:
    String sql63 = "CREATE TRIGGER ValidOrderStatusTrigger BEFORE INSERT ON `order` " +
            "FOR EACH ROW " +
            "BEGIN " +
            "  IF NOT (NEW.Status IN ('Pending', 'In Progress', 'Completed')) THEN " +
            "    SIGNAL SQLSTATE '45000' " +
            "    SET MESSAGE_TEXT = 'Invalid Order Status'; " +
            "  END IF; " +
            "END";
    ResultSet rs3 =statement.executeUpdate(sql63);
    System.out.println("Valid Order Status has been added!");

    //Assertion to check Bill Total amount is non negative:
    String sql64 = "CREATE TRIGGER NonNegativeTotalAmountTrigger BEFORE INSERT ON Bill " +
            "FOR EACH ROW " +
            "BEGIN " +
            "  IF NOT (NEW.TotalAmount >= 0) THEN " +
            "    SIGNAL SQLSTATE '45000' " +
            "    SET MESSAGE_TEXT = 'Total Amount must be non-negative'; " +
            "  END IF; " +
            "END";
    ResultSet rs4 =statement.executeUpdate(sql64);
    System.out.println("Non-Negative Total Amount has been added!");

   //Assertion to check if Tree Location is NotNullLocation:
    String sql65 = "CREATE TRIGGER NonNullLocationTrigger BEFORE INSERT ON Tree " +
            "FOR EACH ROW " +
            "BEGIN " +
            "  IF NEW.Location IS NULL THEN " +
            "    SIGNAL SQLSTATE '45000' " +
            "    SET MESSAGE_TEXT = 'Location must not be NULL'; " +
            "  END IF; " +
            "END";
    ResultSet rs5 =statement.executeUpdate(sql65);
    System.out.println("Non-Null Location has been added!");
 

      
    } catch (Exception e) {
         System.out.println(e);
    } finally {
      close();
    }

  }

  
  // You need to close the resultSet
  private static void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }
} 
