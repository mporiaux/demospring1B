package be.condorcet.demospring1.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.condorcet.demospring1.modele.*;
import java.sql.Date;
import java.util.List;

@Repository
public interface ComfactRepository extends JpaRepository<Comfact,Integer> {
    public List<Comfact> findComfactByClient(Client cl);
    public List<Comfact> findComfactByDatecommande(Date dc);
}
