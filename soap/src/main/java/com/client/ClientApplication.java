package com.client;

import com.client.service.CityClientService;
import com.soap.gen.City;
import com.soap.gen.GetCityResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Viktor Makarov
 */
@Log4j2
@SpringBootApplication
@PropertySource("classpath:client.properties")
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(CityClientService quoteClient) {
        return args -> {
            String cityName = "Samara";

            if (args.length > 0) {
                cityName = args[0];
            }
            GetCityResponse response = quoteClient.getCity(cityName);
            City city = response.getCity();
            log.debug(city.getName() + ", latitude: " + city.getLatitude() + ", longitude: "
                    + city.getLongitude());
        };
    }
}
