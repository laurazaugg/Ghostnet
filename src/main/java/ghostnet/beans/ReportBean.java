package ghostnet.beans;

import ghostnet.model.Ghostnet;
import ghostnet.service.GhostnetService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("reportBean")
@ViewScoped
public class ReportBean implements Serializable {
    //Net characteristics
    Double latitude;
    Double longitude;
    Integer size;
    String name;
    String number;
    private boolean anonym = false;

    @Inject
    private GhostnetService ghostnetService;

    public String save() {
        Ghostnet net = new Ghostnet();
        net.setLatitude(latitude);
        net.setLongitude(longitude);
        net.setSize(size);

        if(!anonym) {
            net.setName(name);
            net.setNumber(number);
        }

        net.setStatus("Gemeldet");

        try {
            ghostnetService.save(net);
            return "pages/thank.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean isAnonym() {
        return anonym;
    }
    public void setAnonym(boolean anonym) {
        this.anonym = anonym;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
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
