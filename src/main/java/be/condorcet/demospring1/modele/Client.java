package be.condorcet.demospring1.modele;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor
@ToString
@Entity
@Table(name = "APICLIENT", schema = "ORA30", catalog = "orcl.condorcet.be")
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name="client_generator", sequenceName = "APICLIENT_SEQ", allocationSize=1)
    private Integer idclient;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    private Integer cp;
    private String localite;
    private String rue;
    private String num;
    @NonNull
    private String tel;

}




