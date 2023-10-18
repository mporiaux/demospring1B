package be.condorcet.demospring1.services;

import be.condorcet.demospring1.modele.Client;

import java.util.List;

public interface InterfClientService extends InterfService<Client>{
    public List<Client> read(String nom);

    Client read(String nom, String prenom, String tel);
}
