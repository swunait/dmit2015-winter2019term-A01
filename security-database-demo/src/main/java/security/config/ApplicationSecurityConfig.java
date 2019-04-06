package security.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

@CustomFormAuthenticationMechanismDefinition(
	loginToContinue = @LoginToContinue(
		loginPage = "/login.xhtml",
		errorPage = "",
		useForwardToLogin = false
	)
)

@EmbeddedIdentityStoreDefinition({
	@Credentials(
		callerName = "customer01",
		password = "Password2015",
		groups = { "VIEW_CUSTOMER_PAGES" }
	),
	@Credentials(
		callerName = "employee01",
		password = "Password2015",
		groups = { "VIEW_EMPLOYEE_PAGES" }
	),
	@Credentials(
		callerName = "webadmin01",
		password = "Password2015",
		groups = { "VIEW_ADMIN_PAGES" }
	),
})

@DatabaseIdentityStoreDefinition(
	dataSourceLookup="java:app/datasources/ChinookDS",
	callerQuery="SELECT password FROM LoginUser WHERE username = ?",
	groupsQuery="SELECT g.groupname FROM LoginUser u, LoginUserGroup ug, LoginGroup g WHERE u.username = ? AND u.id = ug.userid AND ug.groupid = g.id",
	priority = 10
)

@FacesConfig @ApplicationScoped
public class ApplicationSecurityConfig {

}
