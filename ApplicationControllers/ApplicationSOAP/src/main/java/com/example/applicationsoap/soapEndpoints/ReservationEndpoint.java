package com.example.applicationsoap.soapEndpoints;

import com.example.applicationsoap.soapAdapters.ReservationServiceSoapAdapter;
import com.example.applicationsoap.soapmodel.reservationmodel.*;
import exceptions.CannotCreateItem;
import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDateTime;
import java.util.UUID;

@Endpoint
public class ReservationEndpoint {
    private static final String URI = "http://example.com/applicationsoap/soapmodel/reservationmodel";

    @Autowired
    ReservationServiceSoapAdapter reservationServiceSoapAdapter;

    @PayloadRoot(namespace = URI, localPart = "ReadAllReservationRequest")
    @ResponsePayload
    public ReadAllReservationResponse readAllReservationResponse(@RequestPayload ReadAllReservationRequest request) {
        ReadAllReservationResponse response = new ReadAllReservationResponse();
        response.getReservationSoap().addAll(reservationServiceSoapAdapter.readAllReservation());
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "ReadOneReservationRequest")
    @ResponsePayload
    public ReadOneReservationResponse readOneReservationResponse(@RequestPayload ReadOneReservationRequest request) throws ItemNotFound {
        ReadOneReservationResponse response = new ReadOneReservationResponse();
        response.setReservationSoap(reservationServiceSoapAdapter.readOneReservation(UUID.fromString(request.getUuid())));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "CreateReservationRequest")
    @ResponsePayload
    public CreateReservationResponse createReservationResponse(@RequestPayload CreateReservationRequest request) throws ItemNotFound, CannotCreateItem {
        CreateReservationResponse response = new CreateReservationResponse();
        response.setReservationSoap(reservationServiceSoapAdapter.addReservation(UUID.fromString(request.getUserUuid()),
                UUID.fromString(request.getLaneUuid()), LocalDateTime.parse(request.getStartReservation()),
                LocalDateTime.parse(request.getStartReservation())));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "EndReservationRequest")
    @ResponsePayload
    public EndReservationResponse EndReservationResponse(@RequestPayload EndReservationRequest request) {
        EndReservationResponse response = new EndReservationResponse();
        response.setReservationSoap(reservationServiceSoapAdapter.endReservation(UUID.fromString(request.getUuid()),
                LocalDateTime.parse(request.getEndReservation())));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "DeleteReservationRequest")
    @ResponsePayload
    public DeleteReservationResponse deleteReservationResponse(@RequestPayload DeleteReservationRequest request) throws ItemNotFound, CannotDeleteItem {
        DeleteReservationResponse response = new DeleteReservationResponse();
        response.setReservationSoap(reservationServiceSoapAdapter.delete(UUID.fromString(request.getUuid())));
        return response;
    }

}
