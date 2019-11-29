package com.yash.ecom.server.controller;

import com.yash.ecom.server.ws.GetShipment;
import com.yash.ecom.server.ws.GetShipmentResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ShipmentEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getShipment")
    @ResponsePayload
    public GetShipmentResponse getShipment(@RequestPayload GetShipment request) {
        GetShipmentResponse response = new GetShipmentResponse();
        int shipment = Integer.parseInt(request.productDepth) * Integer.parseInt(request.productHeight) *
                Integer.parseInt(request.productWeight) * Integer.parseInt(request.productWidth) * 10;
        response.setCharges(shipment);
        return response;
    }
}
