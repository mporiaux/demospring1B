package be.condorcet.demospring1.services;


import be.condorcet.demospring1.modele.Client;
import be.condorcet.demospring1.modele.Comfact;
import be.condorcet.demospring1.repositories.ClientRepository;
import be.condorcet.demospring1.repositories.ComfactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional(rollbackOn = Exception.class)
public class ComfactServiceImpl  implements InterfComfactService{
    @Autowired
    private ComfactRepository comfactRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Comfact create(Comfact comfact) throws Exception {
        comfactRepository.save(comfact);
        return comfact;
    }

    @Override
    public Comfact read(Integer id) throws Exception {
        return comfactRepository.findById(id).get();
    }

    @Override
    public Comfact update(Comfact comfact) throws Exception {
        read(comfact.getIdcommande());
        comfactRepository.save(comfact);
        return comfact;
    }

    @Override
    public void delete(Comfact comfact) throws Exception {
        comfactRepository.deleteById(comfact.getIdcommande());
    }

    @Override
    public List<Comfact> all() throws Exception {
        return comfactRepository.findAll();
    }

    @Override
    public List<Comfact> getComfacts(Client cl) {
        List<Comfact> lcf = comfactRepository.findComfactByClient(cl);
        return lcf;
    }
}
