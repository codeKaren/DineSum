package com.a0xffffffff.dinesum;

import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private String requesterID1 = "requester1";
    private Restaurant restaurant1 = new Restaurant("restaurantID1", "restaurantName1",
            "555-5555", "1200 Pennsylvania Ave NW", "Los Angeles");
    private RequestData requestData1 = new RequestData("11:00", "12:00", "party1",
            2, restaurant1, 2.50);
    private String requestID1 = "request1";
    Request request1 = new Request(requesterID1, requestData1, requestID1);

    private String requesterID2 = "requester2";
    private Restaurant restaurant2 = new Restaurant("restaurantID2", "restaurantName2",
            "777-7777", "330 De Neve Dr", "Los Angeles");
    private RequestData requestData2 = new RequestData("2:00", "3:00", "party2",
            4, restaurant2, 3.00);
    private String requestID2 = "request2";
    Request request2 = new Request(requesterID2, requestData2, requestID2);

    User testUser;

    @Before
    public void setup(){
        testUser = new User();
    }


    @Test
    public void testUserCreatedSuccessfully(){
        System.out.println("Checking to make sure Lists are not null and empty");
        assertTrue(testUser.getRequests() != null && testUser.getRequests().isEmpty());
        assertTrue(testUser.getReservations() != null && testUser.getReservations().isEmpty());
    }

    @Test
    public void testRequestAddedRemovedSuccessfully() {
        System.out.println("Adding Request 1 to mRequests");
        assertTrue(testUser.addRequest(request1));
        assertTrue(testUser.getRequests().size() == 1);
        assertTrue(testUser.getRequests().get(0) == request1);

        System.out.println("Adding Request 2 to mRequests");
        assertTrue(testUser.addRequest(request2));
        assertTrue(testUser.getRequests().size() == 2);
        assertTrue(testUser.getRequests().get(0) == request1);
        assertTrue(testUser.getRequests().get(1) == request2);

        System.out.println("Removing Request 1 from mRequests");
        assertTrue(testUser.removeRequest(request1));
        assertTrue(testUser.getRequests().size() == 1);
        assertTrue(testUser.getRequests().get(0) == request2);

        System.out.println("Removing Request 2 from mRequests");
        assertTrue(testUser.removeRequest(request2));
        assertTrue(testUser.getRequests().size() == 0);
    }

    @Test
    public void testReservationAddedRemovedSuccessfully() {
        System.out.println("Adding Request 1 to mReservations");
        assertTrue(testUser.addReservation(request1));
        assertTrue(testUser.getReservations().size() == 1);
        assertTrue(testUser.getReservations().get(0) == request1);

        System.out.println("Adding Request 2 to mReservations");
        assertTrue(testUser.addReservation(request2));
        assertTrue(testUser.getReservations().size() == 2);
        assertTrue(testUser.getReservations().get(0) == request1);
        assertTrue(testUser.getReservations().get(1) == request2);

        System.out.println("Removing Request 1 from mReservations");
        assertTrue(testUser.removeReservation(request1));
        assertTrue(testUser.getReservations().size() == 1);
        assertTrue(testUser.getReservations().get(0) == request2);

        System.out.println("Removing Request 2 from mReservations");
        assertTrue(testUser.removeReservation(request2));
        assertTrue(testUser.getReservations().size() == 0);
    }

}