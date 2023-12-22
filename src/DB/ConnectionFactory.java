package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String URL = 
            "jdbc:mysql://localhost:3306/rpg?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
    public static Connection createConnectionToMySQL() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        
        return conn;
    }
    
    public static void main(String[] args) throws Exception{
        Connection c = createConnectionToMySQL();
        
        if(c != null){
            System.out.println("Conexão obtida com sucesso!\n"+c);
        }else {
            System.out.println("Problema na conexão!");
        }
    }
}
