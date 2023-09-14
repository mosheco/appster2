package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





@SpringBootApplication
@RestController
public class DemoApplication {
    private record ResponseObj(int account_id, int dollar_balance) { }

    private static Connection connection;
    
    public static void main(String[] args) {
	SpringApplication.run(DemoApplication.class, args);
    }

    private static Connection get_connection() {
        // Database connection parameters
	String mysqlHost = System.getProperty("mysqlhost", "mysqldb"); // the default is for the docker case
        String jdbcUrl = "jdbc:mysql://" + mysqlHost + ":3306/the_bank";
        String username = "demouser";
        String password = "demopw";

        try {
            // Establish a connection to the database
	    return DriverManager.getConnection(jdbcUrl, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
	    return null;
        }
    }
    
    @GetMapping("/get_balance")
    public List<ResponseObj> get_balance( String account_id) {
	List<ResponseObj> objList = new ArrayList<>();

	if (connection == null) {
	    // Connect once
	    connection = get_connection();
	}
	
        try {
	    String sqlQuery =  String.format("SELECT account_id, dollars FROM balance WHERE account_id = %s", account_id);

            // 1. Create a statement for executing SQL queries
            Statement statement = connection.createStatement();

            // 2. Execute the SQL query and retrieve the results
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // 3. Process the query results
            while (resultSet.next()) {
                int id = resultSet.getInt("account_id");
                int dollar_balance = resultSet.getInt("dollars");
		ResponseObj obj = new ResponseObj(id, dollar_balance);
		objList.add(obj);
            }

            // 4. Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return objList;
    }

    public List<ResponseObj> get_balance_never_called( String account_id) {
	List<ResponseObj> objList = new ArrayList<>();

	if (connection == null) {
	    // Connect once
	    connection = get_connection();
	}
	
        try {
	    String sqlQuery =  String.format("SELECT account_id, dollars FROM balance WHERE account_id = %s", account_id);

            // 1. Create a statement for executing SQL queries
            Statement statement = connection.createStatement();

            // 2. Execute the SQL query and retrieve the results
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // 3. Process the query results
            while (resultSet.next()) {
                int id = resultSet.getInt("account_id");
                int dollar_balance = resultSet.getInt("dollars");
		ResponseObj obj = new ResponseObj(id, dollar_balance);
		objList.add(obj);
            }

            // 4. Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return objList;
    }

    @GetMapping("/get_balance_early_return")
    public List<ResponseObj> get_balance_early_return( String account_id) {
	List<ResponseObj> objList = new ArrayList<>();

	if (connection == null) {
	    // Connect once
	    connection = get_connection();
	}
	
        try {
	    String sqlQuery =  String.format("SELECT account_id, dollars FROM balance WHERE account_id = %s", account_id);

            // 1. Create a statement for executing SQL queries
            Statement statement = connection.createStatement();
	    boolean flag = true;
	    if (flag) {
		return objList;
	    }

            // 2. Execute the SQL query and retrieve the results
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // 3. Process the query results
            while (resultSet.next()) {
                int id = resultSet.getInt("account_id");
                int dollar_balance = resultSet.getInt("dollars");
		ResponseObj obj = new ResponseObj(id, dollar_balance);
		objList.add(obj);
            }

            // 4. Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return objList;
    }

    @GetMapping("/get_balance_early_return_explicit")
    public List<ResponseObj> get_balance_early_return_explicit( String account_id) {
	List<ResponseObj> objList = new ArrayList<>();

	if (connection == null) {
	    // Connect once
	    connection = get_connection();
	}
	
        try {
	    String sqlQuery =  String.format("SELECT account_id, dollars FROM balance WHERE account_id = %s", account_id);

            // 1. Create a statement for executing SQL queries
            Statement statement = connection.createStatement();
	    if (true) {
		return objList;
	    }

            // 2. Execute the SQL query and retrieve the results
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // 3. Process the query results
            while (resultSet.next()) {
                int id = resultSet.getInt("account_id");
                int dollar_balance = resultSet.getInt("dollars");
		ResponseObj obj = new ResponseObj(id, dollar_balance);
		objList.add(obj);
            }

            // 4. Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return objList;
    }

    @GetMapping("/get_balance_if_false")
    public List<ResponseObj> get_balance_if_false( String account_id) {
	List<ResponseObj> objList = new ArrayList<>();

	if (connection == null) {
	    // Connect once
	    connection = get_connection();
	}
	
        try {
	    String sqlQuery =  String.format("SELECT account_id, dollars FROM balance WHERE account_id = %s", account_id);

            // 1. Create a statement for executing SQL queries
            Statement statement = connection.createStatement();

	    Boolean flag = false;

	    if (flag) {
		// 2. Execute the SQL query and retrieve the results
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		
		// 3. Process the query results
		while (resultSet.next()) {
		    int id = resultSet.getInt("account_id");
		    int dollar_balance = resultSet.getInt("dollars");
		    ResponseObj obj = new ResponseObj(id, dollar_balance);
		    objList.add(obj);
		}
		
		// 4. Close the resources
		resultSet.close();
	    }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return objList;
    }
    
    public List<ResponseObj> get_balance_extra_caller( String account_id) {
	return called_by_get_balance_extra_caller(account_id);
    }
	
    public List<ResponseObj> called_by_get_balance_extra_caller( String account_id) {
	List<ResponseObj> objList = new ArrayList<>();

	if (connection == null) {
	    // Connect once
	    connection = get_connection();
	}
	
        try {
	    String sqlQuery =  String.format("SELECT account_id, dollars FROM balance WHERE account_id = %s", account_id);

            // 1. Create a statement for executing SQL queries
            Statement statement = connection.createStatement();

            // 2. Execute the SQL query and retrieve the results
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // 3. Process the query results
            while (resultSet.next()) {
                int id = resultSet.getInt("account_id");
                int dollar_balance = resultSet.getInt("dollars");
		ResponseObj obj = new ResponseObj(id, dollar_balance);
		objList.add(obj);
            }

            // 4. Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return objList;
    }


    @GetMapping("/get_balance_parameterized")
    public List<ResponseObj> get_balance_parameterized( String account_id) {
	List<ResponseObj> objList = new ArrayList<>();

	if (connection == null) {
	    // Connect once
	    connection = get_connection();
	}

	// Cast account_id to string, to avoid automatic conversion of the string on the right hand
	// side of the '=' operator to integer. That would use the prefix integer ("5 OR 1" becomes 5) and
	// cause surprising results. We want strict equality.
	String sqlQuery =  "SELECT account_id, dollars FROM balance WHERE CAST(account_id AS CHAR) = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
	    // 1. prepare statement with parameter
	    preparedStatement.setString(1, account_id); // Set the parameter value

	
	    // 2. Execute the SQL query and retrieve the results
	    ResultSet resultSet = preparedStatement.executeQuery();
	    
	    // 3. Process the query results
	    while (resultSet.next()) {
		int id = resultSet.getInt("account_id");
		int dollar_balance = resultSet.getInt("dollars");
		ResponseObj obj = new ResponseObj(id, dollar_balance);
		objList.add(obj);
	    }
	    
	    // 4. Close the resources
	    resultSet.close();
	} catch (SQLException e) {
	    e.printStackTrace();
        }
	return objList;
    }

}
