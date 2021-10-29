package com.soap.service;

import com.soap.gen.City;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Viktor Makarov
 */
@Service
public class CityService {
    private final Map<String, City> repo = new HashMap<>();

    @PostConstruct
    private void init() {
        String cityName = "Magnitogorsk";
        City city = new City();
        city.setName(cityName);
        city.setLatitude(53.412943);
        city.setLongitude(59.001623);
        repo.put(cityName, city);

        city = new City();
        cityName = "Samara";
        city.setName(cityName);
        city.setLatitude(53.241505);
        city.setLongitude(50.221245);
        repo.put(cityName, city);

        cityName = "Montreal";
        city = new City();
        city.setName(cityName);
        city.setLatitude(45.508888);
        city.setLongitude(-73.561668);
        repo.put(cityName, city);
    }

    public City findCity(String name) {
        return repo.get(name);
    }
}
