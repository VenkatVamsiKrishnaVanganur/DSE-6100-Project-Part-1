import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from users where email = ? AND password = ?" ;
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM users";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            
             
            user users = new user(email,firstName, lastName, password, role);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();         
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into users(email,firstName, lastName, password, role) values (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getrole());		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM users WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update users set firstName=?, lastName =?,password = ?, role=? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getrole());		
		
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM users WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role"); 
          
            user = new user(email, firstName, lastName, password, role);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM users WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM users WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM users";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] RESET_TABLES = {"use testdb;",
								"delete from users;",
								"delete from Orders;",
								"delete from client;",
								"delete from tree;",
								"delete from Quote;",
								"delete from Bill;"
        		};
        
        String[] TUPLES_USERS= {"INSERT INTO users VALUES " +
        	    "('john@google.com', 'John', 'Doe', 'password1', 'Customer'), " +
        	    "('jane@google.com', 'Jane', 'Smith', 'password2', 'Customer'), " +
        	    "('ross@google.com', 'Ross', 'Taylore', 'password12', 'Customer'), " +
        	    "('alice@google.com', 'Alice', 'Johnson', 'password3', 'Customer'), " +
        	    "('robert4@google.com', 'Robert', 'Williams', 'password4', 'Customer'), " +
        	    "('jake@google.com', 'jake', 'Bo', 'password25', 'Customer'), " +
        	    "('eva@google.com', 'Eva', 'Brown', 'password5', 'Customer'), " +
        	    "('michael@google.com', 'Michael', 'Davis', 'password6', 'Customer'), " +
        	    "('david@google.com', 'David', 'Smith', 'david123', 'Admin'), " +
        	    "('root', 'default', 'default', 'pass1234', 'root')"};
        
        String[] TUPLES_CLIENT = {
        	    "INSERT INTO client VALUES (1, 'John', 'Doe', '123 Main St, Detroit, USA', '1234567890123456', '555-555-5555', 'john@gmail.com')",
        	    "INSERT INTO client VALUES (2, 'Jane', 'Smith', '456 Elm St, Flint, USA', '9876543210987654', '555-123-4567', 'jane@gmail.com')",
        	    "INSERT INTO client VALUES (3, 'Bob', 'Johnson', '789 Oak St, Chicago, USA', '1111222233334444', '555-987-6543', 'bob@gmail.com')",
        	    "INSERT INTO client VALUES (4, 'Alice', 'Brown', '246 Maple St, Novi, USA', '5555666677778888', '555-333-2222', 'alice@gmail.com')",
        	    "INSERT INTO client VALUES (5, 'Charlie', 'Wilson', '789 Pine St, Troy, USA', '4444333322221111', '555-111-4444', 'charlie@gmail.com')",
        	    "INSERT INTO client VALUES (6, 'Emily', 'Jones', '654 Cedar St, Detroit, USA', '9999888877776666', '555-444-7777', 'emily@gmail.com')",
        	    "INSERT INTO client VALUES (7, 'Daniel', 'Taylor', '890 Birch St, Flint, USA', '2222333344445555', '555-888-3333', 'daniel@gmail.com')",
        	    "INSERT INTO client VALUES (8, 'Ella', 'White', '753 Oak St, Chicago, USA', '7777666655554444', '555-222-6666', 'ella@gmail.com')",
        	    "INSERT INTO client VALUES (9, 'Frank', 'Lee', '125 Pine St, Novi, USA', '3333444455556666', '555-999-2222', 'frank@gmail.com')",
        	    "INSERT INTO client VALUES (10, 'Grace', 'Smith', '367 Maple St, Troy, USA', '8888777766665555', '555-444-1111', 'grace@gmail.com')"
        	};
        
        String[] TUPLES_TREE = {
        	    "INSERT INTO Tree VALUES (111, 1, 3.5, 15.0, 'Front Yard', 1, 'Tall oak tree near the house')",
        	    "INSERT INTO Tree VALUES (112, 2, 4.0, 12.0, 'Backyard', 0, 'Maple tree by the garden')",
        	    "INSERT INTO Tree VALUES (113, 3, 2.5, 8.0, 'Front Yard', 1, 'Pine tree next to the driveway')",
        	    "INSERT INTO Tree VALUES (114, 4, 3.0, 10.0, 'Backyard', 0, 'Birch tree near the patio')",
        	    "INSERT INTO Tree VALUES (115, 5, 4.5, 18.0, 'Front Yard', 1, 'Cedar tree by the entrance')",
        	    "INSERT INTO Tree VALUES (116, 6, 3.8, 14.0, 'Backyard', 0, 'Willow tree near the pond')",
        	    "INSERT INTO Tree VALUES (117, 7, 2.0, 6.0, 'Front Yard', 1, 'Fir tree by the mailbox')",
        	    "INSERT INTO Tree VALUES (118, 8, 4.2, 16.0, 'Backyard', 0, 'Hemlock tree near the fence')",
        	    "INSERT INTO Tree VALUES (119, 9, 3.2, 11.0, 'Front Yard', 1, 'Spruce tree near the gazebo')",
        	    "INSERT INTO Tree VALUES (120, 10, 2.8, 9.0, 'Backyard', 0, 'Poplar tree by the pool')"
        	};

        String[] TUPLES_QUOTE = {
        	    "INSERT INTO Quote VALUES (11, 111, 1200.00, '2023-04-15', '2023-04-20', 'Pending', 'Initial quote for tree pruning')",
        	    "INSERT INTO Quote VALUES (12, 112, 900.50, '2023-05-10', '2023-05-15', 'Accepted', 'Accepted quote for tree removal')",
        	    "INSERT INTO Quote VALUES (13, 113, 600.00, '2023-06-05', '2023-06-10', 'Completed', 'Completed work on pine tree')",
        	    "INSERT INTO Quote VALUES (14, 114, 759.00, '2023-07-15', '2023-07-20', 'Pending', 'Initial quote for tree trimming')",
        	    "INSERT INTO Quote VALUES (15, 115, 1100.00, '2023-08-10', '2023-08-15', 'Accepted', 'Accepted quote for tree pruning')",
        	    "INSERT INTO Quote VALUES (16, 116, 850.00, '2023-09-05', '2023-09-10', 'Completed', 'Completed work on maple tree')",
        	    "INSERT INTO Quote VALUES (17, 117, 950.00, '2023-10-15', '2023-10-20', 'Pending', 'Initial quote for tree maintenance')",
        	    "INSERT INTO Quote VALUES (18, 118, 750.00, '2023-11-10', '2023-11-15', 'Accepted', 'Accepted quote for tree trimming')",
        	    "INSERT INTO Quote VALUES (19, 119, 988.00, '2023-12-05', '2023-12-10', 'Completed', 'Completed work on oak tree')",
        	    "INSERT INTO Quote VALUES (20, 120, 950.00, '2024-01-15', '2024-01-20', 'Pending', 'Initial quote for tree pruning')"
        	};
        String[] TUPLES_ORDERS = {
        	    "INSERT INTO Orders VALUES (1001, 111, 1, 11, 'Accepted')",
        	    "INSERT INTO Orders VALUES (1002, 112, 2, 12, 'Accepted')",
        	    "INSERT INTO Orders VALUES (1003, 113, 3, 13, 'Completed')",
        	    "INSERT INTO Orders VALUES (1004, 114, 4, 14, 'Pending')",
        	    "INSERT INTO Orders VALUES (1005, 115, 5, 15, 'Accepted')",
        	    "INSERT INTO Orders VALUES (1006, 116, 6, 16, 'Completed')",
        	    "INSERT INTO Orders VALUES (1007, 117, 7, 17, 'Pending')",
        	    "INSERT INTO Orders VALUES (1008, 118, 8, 18, 'Accepted')",
        	    "INSERT INTO Orders VALUES (1009, 119, 9, 19, 'Completed')",
        	    "INSERT INTO Orders VALUES (1010, 120, 10, 20, 'Pending')"
        	};
        String[] TUPLES_BILL = {
        	    "INSERT INTO Bill VALUES (1101, 1001, 1500.00, 'Paid', 'Paid for tree maintenance work')",
        	    "INSERT INTO Bill VALUES (1102, 1002, 1200.50, 'Paid', 'Paid for tree trimming work')",
        	    "INSERT INTO Bill VALUES (1103, 1003, 1750.00, 'Paid', 'Paid for tree pruning work')",
        	    "INSERT INTO Bill VALUES (1104, 1004, 950.00, 'Paid', 'Paid for tree removal work')",
        	    "INSERT INTO Bill VALUES (1105, 1005, 2000.00, 'Paid', 'Paid for tree maintenance work')",
        	    "INSERT INTO Bill VALUES (1106, 1006, 1800.75, 'Paid', 'Paid for tree trimming work')",
        	    "INSERT INTO Bill VALUES (1107, 1007, 1300.00, 'Paid', 'Paid for tree pruning work')",
        	    "INSERT INTO Bill VALUES (1108, 1008, 900.50, 'Paid', 'Paid for tree removal work')",
        	    "INSERT INTO Bill VALUES (1109, 1009, 1600.00, 'Paid', 'Paid for tree maintenance work')",
        	    "INSERT INTO Bill VALUES (1110, 1010, 1850.25, 'Paid', 'Paid for tree trimming work')"
        	};


     

        
        //for loop to put these in database
        for (int i = 0; i < RESET_TABLES.length; i++) {
        	statement.execute(RESET_TABLES[i]);
        }
        for (int i = 0; i < TUPLES_USERS.length; i++) {	
        	statement.execute(TUPLES_USERS[i]);
        }
        for (int i = 0; i < TUPLES_CLIENT.length; i++) {	
        	statement.execute(TUPLES_CLIENT[i]);
        }
        for (int i = 0; i < TUPLES_TREE.length; i++) {	
        	statement.execute(TUPLES_TREE[i]);
        }
        for (int i = 0; i < TUPLES_QUOTE.length; i++) {	
        	statement.execute(TUPLES_QUOTE[i]);
        }
        for (int i = 0; i < TUPLES_ORDERS.length; i++) {	
        	statement.execute(TUPLES_ORDERS[i]);
        }
        for (int i = 0; i < TUPLES_BILL.length; i++) {	
        	statement.execute(TUPLES_BILL[i]);
        }
        
        
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
