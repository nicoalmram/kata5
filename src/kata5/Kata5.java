package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Kata5 {
    private Connection connection;

    private static void insertDATA_PEOPLE(Connection connection) {
        String SQL = "INSERT INTO PEOPLE(ID, NAME, APELLIDOS, DEPARTAMENTOS) VALUES(?, ?, ?, ?)";
        try{
            PreparedStatement preparedstatement = connection.PreparedStatement(SQL);
            preparedstatement.setInt(1, 23);
            preparedstatement.setString(2, "Juan");
            preparedstatement.setString(3, "Quesada");
            preparedstatement.setString(4, "Compras");
           
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
            insertDATA_PEOPLE(connection);
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
