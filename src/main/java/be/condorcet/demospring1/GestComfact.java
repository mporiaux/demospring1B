package be.condorcet.demospring1;

import be.condorcet.demospring1.modele.Client;
import be.condorcet.demospring1.modele.Comfact;
import be.condorcet.demospring1.services.InterfClientService;
import be.condorcet.demospring1.services.InterfComfactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comfacts")
public class GestComfact {

/*    @Autowired
    private ComfactRepository comfactRepository;

    @Autowired
    private ClientRepository clientRepository;*/

    @Autowired
    private InterfClientService clientServiceImpl;
    @Autowired
    private InterfComfactService comfactServiceImpl;

    @RequestMapping("/rechparcli")
    public String read(@RequestParam int idclient, Map<String, Object> model) {
        System.out.println("recherche du client n° " + idclient);
        try {
          /*  Optional<Client> ocl = clientRepository.findById(idclient);//findById lance une exception si id inconnu
            Client cl = ocl.get();

            List<Comfact> lcf = comfactRepository.findComfactByClient(cl);*/
             Client cl = clientServiceImpl.read(idclient);
            Comfact cf = new Comfact(Date.valueOf(LocalDate.now()),"c",new BigDecimal(1000),cl);
             comfactServiceImpl.create(cf);
             List<Comfact> lcf = comfactServiceImpl.getComfacts(cl);
            model.put("moncli",cl);
            model.put("clicf", lcf);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affclicf";
    }
}

