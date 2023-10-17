package be.condorcet.demospring1;


import be.condorcet.demospring1.modele.Client;
import be.condorcet.demospring1.modele.ClientDAO;
import be.condorcet.demospring1.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
    @RequestMapping("/clients")
    public class GestClient {
        @Autowired
        ClientRepository clientRepository;

    @Autowired
    CacheManager cacheManager;

    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @RequestMapping("/tous")
        public String affTous(Map<String, Object> model){
            evictAllCaches();
            System.out.println("recherche clients");
           Collection<Client> liste;
            try {
                liste= clientRepository.findAll();
                model.put("mesClients", liste);
            } catch (Exception e) {
                System.out.println("----------erreur lors de la recherche-------- " + e);
                model.put("error",e);
                return "error";
            }
            return "affichagetousClients";
        }

        @RequestMapping("/selection")
        String selection(@RequestParam("numcli") int numcli, Map<String, Object> model) {
            Client cl = null;

            try {
                Optional<Client> ocl =clientRepository.findById(numcli);
                if(ocl.isPresent()){
                     cl=ocl.get();
                }
                else {
                    throw new Exception("client introuvable");
                }
                model.put("monClient", cl);

            } catch (Exception e) {
                System.out.println("erreur lors de la lecture " + e);
                model.put("error",e.getMessage());
                return "error";
            }
            return "affClient";  // page html à développer
        }

    @RequestMapping("/selectionNom")
    String selection(@RequestParam("nom") String nom, Map<String, Object> model) {
        List<Client> lc;

        try {
              lc = clientRepository.findByNomLike(nom+"%");

                model.put("mesClients", lc);

        } catch (Exception e) {
            System.out.println("erreur lors de la lecture " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affClientsNom";  // page html à développer
    }

    }