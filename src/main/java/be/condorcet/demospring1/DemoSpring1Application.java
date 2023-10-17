package be.condorcet.demospring1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class DemoSpring1Application implements CommandLineRunner {
    @Value("${spring.datasource.url}")
    String url ;
    @Value("${spring.datasource.username}")
    String userid;
   @Value("${spring.datasource.password}")
    String password;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpring1Application.class, args);
    }

    @Override
    public void run(String... args){


        Connection dbConnect=null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbConnect = DriverManager.getConnection(url, userid, password);
            // connexion avec le login et le password
            // et récupération d'un objet connection
        }
        catch (Exception e){
            System.out.println("erreur : "+e);
            System.exit(0);
        }

        try(
                Statement stmt = dbConnect.createStatement();
                //représente une requête SQL
                ResultSet rs = stmt.executeQuery("select * from apiclient ");
                //récupération des données à partir de la table client
                //ensemble des lignes répondant à la requête
        )
        {
            while (rs.next()) {
                String nom = rs.getString("NOM");
                //ou rs.getString(2);
                String prenom = rs.getString("PRENOM");
                //ou rs.getString(3);
                int n = rs.getInt("IDCLIENT");
                //ou   int n=  rs.getInt(1) ;
                System.out.println(nom + " " + prenom + " " + n);
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL " + e);
        } catch (Exception e) {
            System.out.println("erreur " + e);
        }
        try{
            dbConnect.close();
        } catch (SQLException e) {
            System.out.println("erreur de fermeture de connexion "+e);
        }
    }

}


