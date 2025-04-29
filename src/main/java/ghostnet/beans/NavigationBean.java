package ghostnet.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("navigation")
@RequestScoped
public class NavigationBean implements Serializable {
    public String reportNet() {
        return "/pages/report.xhtml?faces-redirect=true";
    }

    public String showNet() {
        return "/pages/net.xhtml?faces-redirect=true";
    }

    public String home() {
        return "/pages/home.xhtml?faces-redirect=true";
    }

    public String thanks() {
        return "/pages/thank.xhtml?faces-redirect=true";
    }
}
