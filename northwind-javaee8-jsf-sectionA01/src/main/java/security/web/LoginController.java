package security.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import org.omnifaces.cdi.Param;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@RequestScoped
public class LoginController {

	@Inject
	private SecurityContext securityContext;
	
	@Inject
	@Param(name="new")
	private boolean isNew;
	
	@NotBlank(message="Username value is required.")
	private String username;
	@NotBlank(message="Password value is required.")
	private String password;
	
	
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


	public String login() {
				
		switch (continueAuthentication()) {
			case SEND_CONTINUE:
				Faces.responseComplete();
				break;
			case SEND_FAILURE:
				Messages.addGlobalError("Login failed");
				break;
			case SUCCESS:
//				flash.setKeepMessages(true);
				Messages.addFlashGlobalInfo("Login succeed");
//				return "/index.xhtml?faces-redirect=true";
				Faces.redirect("/index.xhtml");
				break;
			case NOT_DONE:
				// JSF does not need to take any special action here
				break;
		}
				
		return null;
	}
	
	private AuthenticationStatus continueAuthentication() {
		Credential credential = new UsernamePasswordCredential(username, new Password(password) );		
		HttpServletRequest request = Faces.getRequest();
		HttpServletResponse response = Faces.getResponse();
//		return securityContext.authenticate(request, response, AuthenticationParameters.withParams().credential(credential));
		return securityContext.authenticate(request, response, AuthenticationParameters.withParams().newAuthentication(isNew).credential(credential));
	}
}
