package ghostnet.beans;

import ghostnet.model.Ghostnet;
import ghostnet.service.GhostnetService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

@Named("recoveryBean")
@ViewScoped
public class RecoveryBean implements Serializable {

    private List<Ghostnet> netList;

    private String currentUser;
    public String getCurrentUser() {
        return currentUser;
    }

    @Inject
    private GhostnetService ghostnetService;

    @PersistenceContext(unitName = "ghostnetPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        currentUser = (String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("loggedInUsername");

        netList = ghostnetService.findAllOpenNets();
    }

    public List<Ghostnet> getNetList() {
        return netList;
    }
    public void recoverNet(Ghostnet net) {
        String currentUser = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUsername");
        System.out.println("Aktueller Benutzer (currentUser) aus Session: " + currentUser);
        System.out.println("net.getRecoverer() == null: " + (net.getRecoverer() == null));

        if (net.getRecoverer() == null && currentUser != null) {
            try {
                Ghostnet managedNet = em.find(Ghostnet.class, net.getId());
                if (managedNet != null && managedNet.getRecoverer() == null) {
                    managedNet.setRecoverer(currentUser);
                    managedNet.setStatus("Bergung bevorstehend");
                    ghostnetService.update(managedNet);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Netz wurde zugewiesen.", null));
                    init();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Dieses Netz wird bereits geborgen.", null));
                }
            } catch (Exception e) {
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Fehler beim Zuweisen des Netzes.", null));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Dieses Netz wird bereits geborgen.", null));
        }
    }

    public void markAsRecovered(Ghostnet net) {
        try {
            String currentUser = getCurrentUser();
            Ghostnet managedNet = em.find(Ghostnet.class, net.getId());

            if (managedNet != null && currentUser.equals(managedNet.getRecoverer())) {
                managedNet.setStatus("Geborgen");
                ghostnetService.update(managedNet);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Netz als geborgen markiert.", null));
                init();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Du darfst diesen Status nicht ändern.", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler beim Aktualisieren!", null));
        }
    }
}
