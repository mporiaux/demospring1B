package be.condorcet.demospring1.services;


import be.condorcet.demospring1.modele.Client;
import be.condorcet.demospring1.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ClientServiceImpl implements InterfClientService{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> read(String nom) {
        return clientRepository.findByNomLike(nom+"%");
    }

    @Override
    public Client read(String nom, String prenom, String tel) {
        return clientRepository.findByNomAndPrenomAndTel(nom,prenom,tel).get(0);
    }

    @Override
    public Client create(Client client) throws Exception {
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client read(Integer id) throws Exception {
        Optional<Client> ocl= clientRepository.findById(id);
        return ocl.get();
    }

    @Override
    public Client update(Client client) throws Exception {
        read(client.getIdclient());
        clientRepository.save(client);
        return client;
    }

    @Override
    public void delete(Client client) throws Exception {
        clientRepository.deleteById(client.getIdclient());
    }

    @Override
    public List<Client> all() throws Exception {
        return clientRepository.findAll();
    }

}
