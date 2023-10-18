package be.condorcet.demospring1.services;

import be.condorcet.demospring1.modele.Client;
import be.condorcet.demospring1.modele.Comfact;

import java.util.List;

public interface InterfComfactService extends InterfService<Comfact> {
    public List<Comfact> getComfacts(Client cl);
}
