package Cooking.School.Project.cookingSchool.config;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.hsqldb.server.ServerAcl;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HSQLDBStarter {

    public static void main(String[] args) throws ServerAcl.AclFormatException, IOException {
        final HsqlProperties properties = new HsqlProperties();
        properties.setProperty("server.port", "9092");
        properties.setProperty("server.dbname.0", "cooking");
        properties.setProperty("server.database.0", "file:db/cooking/cookingdb;user=sa;password=");

        Server server1 = new org.hsqldb.Server();
        server1.setProperties(properties);
        server1.start();
    }
}
