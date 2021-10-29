package com.client.service;

import com.soap.gen.GetCityRequest;
import com.soap.gen.GetCityResponse;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Log4j2
public class CityClientService extends WebServiceGatewaySupport {

    public GetCityResponse getCity(String country) {

        GetCityRequest request = new GetCityRequest();
        request.setName(country);

        log.info("Requesting location for " + country);

        GetCityResponse response = (GetCityResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/cities", request,
                        new SoapActionCallback(
                                "http://gen.soap.com/GetCitiesRequest"));

        return response;
    }

}