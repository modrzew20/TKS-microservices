package com.example.applicationsoap;

import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApplicationSoapApplication.class)
@Disabled("Disabled until UserService is fixed")
//TODO Remove after userService is updated
public interface SpringSOAPTest {
    String readAllLanesRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/lanemodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:ReadAllLaneRequest>
                    </gs:ReadAllLaneRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;
    String addLaneRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/lanemodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:CreateLaneRequest>
                        <gs:lane_type>%s</gs:lane_type>
                    </gs:CreateLaneRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String updateLaneRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/lanemodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:UpdateLaneRequest>
                        <gs:uuid>%s</gs:uuid>
                        <gs:lane_type>%s</gs:lane_type>
                    </gs:UpdateLaneRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String readOneLaneRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/lanemodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:ReadOneLaneRequest>
                        <gs:uuid>%s</gs:uuid>
                    </gs:ReadOneLaneRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String DeleteLaneRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/lanemodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:DeleteLaneRequest>
                        <gs:uuid>%s</gs:uuid>
                    </gs:DeleteLaneRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String ReadOneUserRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/usermodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:ReadOneUserRequest>
                        <gs:uuid>%s</gs:uuid>
                    </gs:ReadOneUserRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String CreateUserRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/usermodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:CreateUserRequest>
                        <gs:accesslevel>%s</gs:accesslevel>
                        <gs:login>%s</gs:login>
                        <gs:password>%s</gs:password>
                    </gs:CreateUserRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String UpdateUserRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/usermodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:UpdateUserRequest>
                        <gs:uuid>%s</gs:uuid>
                        <gs:login>%s</gs:login>
                        <gs:password>%s</gs:password>
                    </gs:UpdateUserRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String DeactivateUserRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/usermodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:deactivateUserRequest>
                        <gs:uuid>%s</gs:uuid>
                    </gs:deactivateUserRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String ActivateUserRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/usermodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:activateUserRequest>
                        <gs:uuid>%s</gs:uuid>
                    </gs:activateUserRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String CreateReservationRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/reservationmodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:CreateReservationRequest>
                        <gs:userUuid>%s</gs:userUuid>
                        <gs:laneUuid>%s</gs:laneUuid>
                        <gs:startReservation>%s</gs:startReservation>
                        <gs:endReservation>%s</gs:endReservation>
                    </gs:CreateReservationRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String ReadAllReservationRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/reservationmodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:ReadAllReservationRequest>
                    </gs:ReadAllReservationRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String ReadOneReservationRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/reservationmodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:ReadOneReservationRequest>
                        <gs:uuid>%s</gs:uuid>
                    </gs:ReadOneReservationRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

    String DeleteReservationRequest = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                    xmlns:gs="http://example.com/applicationsoap/soapmodel/reservationmodel">
                <soapenv:Header/>
                <soapenv:Body>
                    <gs:DeleteReservationRequest>
                        <gs:uuid>%s</gs:uuid>
                    </gs:DeleteReservationRequest>
                </soapenv:Body>
            </soapenv:Envelope>
            """;

}
