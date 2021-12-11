package com.cts.conctes.dao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.cts.conctes.exception.ConstructionEstimationException;


public class DBConnectionManager 
{

    private static Connection con = null;
    private static DBConnectionManager instance;

    private DBConnectionManager() throws ConstructionEstimationException 
    {
        //WRITE YOUR CODE HERE
        //return con;
    }

    public static DBConnectionManager getInstance() throws ConstructionEstimationException 
    {
        if (instance == null) 
        {
            instance = new DBConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() 
    {
      //--------------------------------------1st try catch-------------------------------------------------
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
        Properties properties = new Properties();
        FileReader f = null;
      //--------------------------------------2nd try catch-------------------------------------------------  
        try 
        {
            f = new FileReader("database.properties");
        } 
        catch (FileNotFoundException e2) 
        {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
      //--------------------------------------3rd try catch-------------------------------------------------
        try 
        {
            properties.load(f);
        } 
        catch (IOException e1) 
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String url = properties.getProperty("url");
        String user = properties.getProperty("username");
        String password = properties.getProperty("password");
      //--------------------------------------4th try catch-------------------------------------------------
        try 
        {
            con = DriverManager.getConnection(url, user, password);
        } 
        catch (SQLException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      //--------------------------------------4th try catch END-------------------------------------------------
        return con;


    }//End of Connection getConnection() 


}//End of class DBConnectionManager
