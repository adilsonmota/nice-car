package service;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="GrowlViewBean")
@RequestScoped
public class GrowlViewBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String errorMessage = "Ops! Algo deu errado!";
    
    private String successMessage = "Tudo certo por aqui!";
     
    public void saveMessage(Boolean success) {
        FacesContext context = FacesContext.getCurrentInstance();
         
        if (success) {
        	context.addMessage(null, new FacesMessage("Sucesso!", successMessage) );
		} else {
			context.addMessage(null, new FacesMessage("Aviso!", errorMessage));
		}
    }

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
    
}