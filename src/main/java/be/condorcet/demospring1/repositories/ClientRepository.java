package be.condorcet.demospring1.repositories;

import be.condorcet.demospring1.modele.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    public List<Client> findByNom(String nom);

    public List<Client> findByNomLike(String nom);

    public List<Client> findByNomAndPrenomAndTel(String nom,String prenom,String tel);
}
