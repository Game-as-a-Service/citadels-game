package tw.waterballsa.gaas.citadels.spring;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class CitadelsApplicationTests {
    @Configuration
    static class Config {
        @Container
        final static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.22-focal"))
                .withExposedPorts(27017)
                .waitingFor(Wait.forLogMessage(".*waiting for connections on port.*\\n", 3));

        @Bean
        public MongoClient mongoClient() {
            mongoDBContainer.start();
            String host = "localhost";
            int port = mongoDBContainer.getMappedPort(27017);
            System.out.println("mongoDBContainer MappedPort::" + port);
            String connectionString = String.format("mongodb://%s:%d", host, port);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .build();
            return MongoClients.create(settings);
        }
    }

    @Autowired
    MongoClient mongoClient;

    @Test
    void checkConnectToMongodb() {
        var dbs = mongoClient.listDatabaseNames();
        dbs.forEach(name -> System.out.println("show mongo dbs: " + name));
        Assertions.assertNotNull(dbs.first());
    }

}
