package northwind.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;

@DataSourceDefinition(
	name="java:app/datasources/northwind-javaee8-jsf-sectionA01/NorthwindDS",
	className="com.mysql.cj.jdbc.MysqlDataSource",
	url="jdbc:mysql://localhost:3306/northwind?serverTimezone=UTC",
	user="dmit2015",
	password="Password2015")
@ApplicationScoped
public class DataSourceDefinitionConfig {

}
