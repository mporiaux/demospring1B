package be.condorcet.demospring1.modele;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.*;

@Component
public class ClientDAO  {

    String url ;
    String userid;

    String password;

    Connection dbConnect=null;

    public ClientDAO(  @Value("${spring.datasource.url}")String url, @Value("${spring.datasource.username}") String userid,  @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.userid = userid;
        this.password = password;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbConnect = DriverManager.getConnection(url, userid, password);
           }
        catch (Exception e){
            System.out.println("erreur : "+e);
            System.exit(0);
        }
    }

    public List<Client> readall() throws Exception{
          List<Client> lc = new ArrayList<>();


        try(PreparedStatement pstm = dbConnect.prepareStatement("select * from APICLIENT")) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idclient= rs.getInt("IDCLIENT");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                int cp = rs.getInt("CP");
                String localite = rs.getString("LOCALITE");
                String rue = rs.getString("RUE");
                String num = rs.getString("NUM");
                String tel = rs.getString("TEL");
                lc.add(new Client(idclient, nom, prenom, cp, localite, rue, num, tel));
            }
            if(lc.isEmpty())throw new Exception("aucun client");
            return lc;

        } catch (Exception e) {
            throw new Exception("Erreur de lecture " + e.getMessage());
        }
    }

    public Client read(int numcli)  throws Exception{
        String req = "select * from APICLIENT where idclient = ?";
        try ( PreparedStatement pstm = dbConnect.prepareStatement(req);) {

            pstm.setInt(1, numcli);
            try ( ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("NOM");
                    String prenom = rs.getString("PRENOM");
                    int cp = rs.getInt("CP");
                    String localite = rs.getString("LOCALITE");
                    String rue = rs.getString("RUE");
                    String num = rs.getString("NUM");
                    String tel = rs.getString("TEL");
                    Client cl = new Client(numcli, nom, prenom, cp, localite, rue, num, tel);
                    return cl;

                } else {
                    throw new Exception("aucun client trouvé pour cet identifiant");
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
