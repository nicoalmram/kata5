package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5 {
    private Connection connection;

    private static void selectDATA_PEOPLE(Connection connection) {
        String SQL = "SELECT * FROM PEOPLE";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(SQL);
            System.out.println("ID \t NAME \t APELLIDOS \t DEPARTAMENTOS");
            while(resultset.next()) {
                System.out.println(resultset.getInt("ID") + " \t " +
                        resultset.getString("NAME") + " \t " +
                        resultset.getString("APELLIDOS") + " \t " +
                        resultset.getInt("DEPARTAMENTOS") + " \t ");
            }
        }
        catch (SQLException exception) {
            System.out.println("ERROR Kata5::connect (SQLException)  " + exception.getMessage());
        }
    }

    public static void main(String[] args) {
        
        String URL_BD_SQLite = "jdbc:sqlite:C:\\Users\\Usuario\\Documents\\NetBeansProjects\\SQLite\\PEOPLE.db";
        connect(URL_BD_SQLite);
    }

    private static void connect(String URL_BD_SQLite) {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL_BD_SQLite);
            System.out.println("Base de Datos conectada...");
            selectDATA_PEOPLE(connection);
        }
        catch (SQLException exception) {
            System.out.println("ERROR Kata5::connect (SQLException)  " + exception.getMessage());
        }
        finally{
            try{
                if (connection != null)
                    connection.close();
            }
            catch (SQLException exception) {
                System.out.println("ERROR Kata5::connect-finally (SQLException)  " + exception.getMessage());
            }
        }
    }
    
}
