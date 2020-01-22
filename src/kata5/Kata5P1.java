package kata5.pkg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Kata5P1 {
    
    private Connection connect() {
        
        String url = "jdbc:sqlite:KATA5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void selectAll(){
        String sql = "SELECT * FROM PEOPLE";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + "\t" +
                                rs.getString("Name") + "\t" +
                                rs.getString("Apellido") + "\t" +
                                rs.getString("Departamento") + "\t");    
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createTable() {
        
        String query = "CREATE TABLE IF NOT EXISTS EMAIL (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIL TEXT NOT NULL);";
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void fillTable(){
        String path = "C:\\Users\\nico\\Documents\\NetBeansProjects\\Kata5-1\\email.txt";
        List<String> emails = MailListReader.read(path);
        try {
            String insert = "INSERT INTO EMAIL(Mail) VALUES(?)";
            Connection conn = this.connect();
            PreparedStatement pstmt= conn.prepareStatement(insert);
            for (String email : emails) {
                pstmt.setString(1, email);
                pstmt.executeUpdate();
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        
        Kata5P1 app = new Kata5P1();
        app.fillTable();
    }
}
