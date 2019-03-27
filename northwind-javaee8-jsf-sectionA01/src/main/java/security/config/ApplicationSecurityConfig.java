package security.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

//@BasicAuthenticationMechanismDefinition(
//		realmName="jaspitest"
//)

//@FormAuthenticationMechanismDefinition(		
//		loginToContinue = @LoginToContinue(
//				loginPage="/security/login.xhtml",
//				errorPage="/security/login.xhtml?error")
//)

@CustomFormAuthenticationMechanismDefinition(
	loginToContinue = @LoginToContinue(
		loginPage="/security/login.xhtml", 
		useForwardToLogin = false,
		errorPage="")
)

@EmbeddedIdentityStoreDefinition({
	@Credentials(
		callerName = "dmit2015",
		password = "Password2015",
		groups = { "Software Developer", "VIEW_USER_PAGES","VIEW_ADMIN_PAGES" }
	),
	@Credentials(
		callerName = "user2015",
		password = "Password2015",
		groups = { "VIEW_USER_PAGES" }
	),
})

//@LdapIdentityStoreDefinition(
//	url = "ldap://metro-ds1.nait.ca:389",
//	callerSearchBase = "dc=nait,dc=ca",
//	callerNameAttribute = "SamAccountName",	// SamAccountName or UserPrincipalName
//	groupSearchBase = "dc=nait,dc=ca",
//	bindDn = "cn=DMIT Student1,ou=DMITStudentRestricted,ou=Student,ou=DMIT,ou=SICET,dc=nait,dc=ca",
//	bindDnPassword = "Password2015",
//	priority = 5
//)

@DatabaseIdentityStoreDefinition(
	dataSourceLookup="java:app/datasources/northwind-javaee8-jsf-sectionA01/NorthwindDS",
	callerQuery="SELECT password FROM LoginUser WHERE username = ?",
	groupsQuery="SELECT g.groupname FROM LoginUser u, LoginUserGroup ug, LoginGroup g WHERE u.username = ? AND u.id = ug.userid AND ug.groupid = g.id",
	priority = 10
)

@FacesConfig @ApplicationScoped
public class ApplicationSecurityConfig {

}
