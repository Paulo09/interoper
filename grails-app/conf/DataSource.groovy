import java.sql.*;
import groovy.sql.Sql

dataSource { 
	pooled = true 
	driverClassName = "org.postgresql.Driver" 
	username = "postgres" 
	password = "root" 
}
hibernate { 
    cache.use_second_level_cache=true 
    cache.use_query_cache=true 
    cache.provider_class='com.opensymphony.oscache.hibernate.OSCacheProvider' 
} 
environments { 
	development { 
		dataSource { 
			try {	
				def sql1 = Sql.newInstance("jdbc:postgresql://localhost:5432/robo","postgres", "root", "org.postgresql.Driver")
				if(sql1){
				   println "-------------- Conectou  Data SOurce 1 --------------"
				   
				    dbCreate = "update" 
					url = "jdbc:postgresql://localhost:5432/robo"
					username = "postgres" 
					password = "root"
				
				}
			
			} 
			catch (Exception e) {
					
					dbCreate = "update" 
					url = "jdbc:postgresql://localhost:5432/robo"
					username = "postgres" 
					password = "root"
					
		    } 
			
		} 
	} 
	test { 
		dataSource { 
			dbCreate = "update" 
			url = "jdbc:hsqldb:mem:piramide" 
		} 
	} 
	production { 
		dataSource { 
			dbCreate = "update" 
			url = "jdbc:hsqldb:file:prodDb;shutdown=true" 
		} 
	} 
} 
