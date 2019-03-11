package chinook.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;

@DataSourceDefinition(
	name="java:app/datasources/dmit2015-javaee8-jsf-sectionA01/ChinookDS",
	className="com.mysql.cj.jdbc.MysqlDataSource",
	url="jdbc:mysql://localhost:3306/Chinook?serverTimezone=UTC",
	user="dmit2015",
	password="Password2015")
@ApplicationScoped
public class DataSourceDefinitionConfig {

}
