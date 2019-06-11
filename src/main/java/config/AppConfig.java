package config;

import config.profiles.DevProfile;
import config.profiles.ProdProfile;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {

    @Bean
    @DevProfile
    //@Profile("default")
    public DatabaseDatasource getH2Datasource() {
        return (() -> Arrays.asList("KasiaTest", "BartekTest", "AniaTest", "KrzysztofTest"));
    }

    @Bean
    @ProdProfile
    public DatabaseDatasource getMysqlDatasource() {
        return new DatabaseDatasource() {
            @Override
            public List<String> getDatabase() {
                try {
                    Path filePath = new File(getClass().getResource("/data.txt").toURI()).toPath();
                    List<String> allLines = Files.readAllLines(filePath);
                    return allLines;
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
                return new ArrayList<>();
            }
        };
    }

    @Bean
    @Profile("default")
    public DatabaseDatasource getDefaultDatasource() {
        return (() -> Arrays.asList("Kasia Default", "Bartek Default"));
    }
}