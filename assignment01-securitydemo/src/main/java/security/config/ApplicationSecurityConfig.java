package security.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;

import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

//@BasicAuthenticationMechanismDefinition(
//	realmName="jaspitest"
//)

@FormAuthenticationMechanismDefinition(
	loginToContinue = @LoginToContinue(
		loginPage="/login.html",
		errorPage="/login.html?error=true"
	)
)

//@EmbeddedIdentityStoreDefinition({
//	@Credentials(callerName="user2015", password="Password2015"),
//	@Credentials(callerName="admin2015", password="Password2015")
//})

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
	dataSourceLookup="java:app/datasources/ChinookDS",
	callerQuery="SELECT password FROM LoginUser WHERE username = ?",
	groupsQuery="SELECT g.groupname FROM LoginUser u, LoginUserGroup ug, LoginGroup g WHERE u.username = ? AND u.id = ug.userid AND ug.groupid = g.id",
	priority = 10
)

@DataSourceDefinition(
	name="java:app/datasources/ChinookDS",
	className="com.mysql.cj.jdbc.MysqlDataSource",
	url="jdbc:mysql://localhost:3306/Chinook?serverTimezone=UTC",
	user="dmit2015",
	password="Password2015")

@ApplicationScoped
public class ApplicationSecurityConfig {

}
