package ghostnet.beans;

import ghostnet.model.Ghostnet;
import ghostnet.service.GhostnetService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class reportBean implements Serializable {
    //Net characteristics
    String coordinates;
    String size;
    String name;
    String number;
    private boolean anonym = false;

    @Inject
    private GhostnetService ghostnetService;

    public String save() {
        Ghostnet net = new Ghostnet();
        net.setCoordinates(coordinates);
        net.setSize(size);

        if(!anonym) {
            net.setName(name);
            net.setNumber(number);
        }

        net.setStatus("Gemeldet");
        ghostnetService.save(net);

        return "pages/thank.xhtml?faces-redirect=true";
    }
    public boolean isAnonym() {
        return anonym;
    }
    public void setAnonym(boolean anonym) {
        this.anonym = anonym;
    }
    public String getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
