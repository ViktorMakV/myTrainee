package com.soap.endpoint;

import com.soap.gen.GetCityRequest;
import com.soap.gen.GetCityResponse;
import com.soap.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Viktor Makarov
 */
@Endpoint
@RequiredArgsConstructor
public class CityEndpoint {
    private static final String NAMESPACE_URI = "gen.soap.com";
    private final CityService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCityRequest")
    @ResponsePayload
    public GetCityResponse getCity(@RequestPayload GetCityRequest request) {
        GetCityResponse response = new GetCityResponse();
        response.setCity(service.findCity(request.getName()));

        return response;
    }
}
