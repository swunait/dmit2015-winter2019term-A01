package security.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;
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
		loginPage="/security/customLogin.xhtml", 
		useForwardToLogin = false,
		errorPage="/security/customLogin.xhtml?error")
)

@EmbeddedIdentityStoreDefinition({
	@Credentials(
		callerName = "dmit2015",
		password = "Password2015",
		groups = { "Software Developer" }
	)
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
	dataSourceLookup="java:app/datasources/securityapp/SecurityDS",
	callerQuery="SELECT password FROM LoginUser WHERE username = ?",
	groupsQuery="SELECT g.groupname FROM LoginUser u, LoginUserGroup ug, LoginGroup g WHERE u.username = ? AND u.id = ug.userid AND ug.groupid = g.id",
	hashAlgorithm = Pbkdf2PasswordHash.class,
	hashAlgorithmParameters = { 
		"Pbkdf2PasswordHash.Iterations=3072", 
		"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", 
		"Pbkdf2PasswordHash.SaltSizeBytes=64" },
	priority = 10
)

@DataSourceDefinition(
	name="java:app/datasources/securityapp/SecurityDS",
	className="org.h2.jdbcx.JdbcDataSource",
	url="jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
	user="sa",
	password="sa"
)

@ApplicationScoped
public class ApplicationSecurityConfig {

}
