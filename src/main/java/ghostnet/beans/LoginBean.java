package ghostnet.beans;

import ghostnet.model.User;
import ghostnet.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("loginBean")
@RequestScoped
public class LoginBean {
    private String username;
    private String password;

    @Inject
    private UserService userService;

    public String login() {
        User user = userService.findByUsernameAndPassword(username, password);
        if (user != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUsername", user.getUsername());
            return "/pages/recovery.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fehlgeschlagen", "Ungültige Zugangsdaten."));
            return null;
        }
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
