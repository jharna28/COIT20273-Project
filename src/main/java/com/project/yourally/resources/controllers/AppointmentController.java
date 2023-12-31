/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.yourally.resources.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.project.yourally.entity.Appointment;
import com.project.yourally.entity.HelperDetail;
import com.project.yourally.entity.User;
import com.project.yourally.repository.AppointmentRepository;
import com.project.yourally.repository.HelperDetailRepository;
import com.project.yourally.utils.APIResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
@Path("book")
public class AppointmentController implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
        bookNow method return json object and send data to repo to save the data in database
    */
    @POST
    @Path("newbook")
    public Response bookNow(Appointment appointment) {
        APIResponse res = new APIResponse();

        AppointmentRepository respository = new AppointmentRepository();
        appointment.CreatedDate = new Date();
        res = respository.createAppointment(appointment);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "{}";
        try {
            json = ow.writeValueAsString(res);
            json = ow.writeValueAsString(res);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.ok(json, MediaType.APPLICATION_JSON).build();

    }

    /*
        getBookingByUser method return json object with user booked appointment
    */ 
    @POST
    @Path("listbyuser")
    public Response getBookingByUser(User user) {
        AppointmentRepository respository = new AppointmentRepository();
        List<Appointment> list = null;
        list = respository.getUserAppointment(user);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "{}";
        try {

            json = ow.writeValueAsString(list);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.ok(json, MediaType.APPLICATION_JSON).build();

    }

    /*
        getBookingByHelper method return json object with helper detail
    */
    @POST
    @Path("listbyhelper")
    public Response getBookingByHelper(User user) {
        AppointmentRepository respository = new AppointmentRepository();
        HelperDetailRepository helprerepo = new HelperDetailRepository();

        List<Appointment> list = null;
        HelperDetail helper = helprerepo.getHelper(user);

        if (helper != null) {
            list = respository.getHelperrAppointment(helper);
        }

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "{}";
        try {

            json = ow.writeValueAsString(list);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.ok(json, MediaType.APPLICATION_JSON).build();

    }

    /*
        closeAppointment method return json object and cancel appointment
    */
    @POST
    @Path("cancel")
    public Response closeAppointment(Appointment app) {
        AppointmentRepository respository = new AppointmentRepository();
        boolean result = respository.closeAppointment(app);

        APIResponse res= new APIResponse();
        res.setCode(0);
        res.setMessage("Changed");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "{}";
        try {

            json = ow.writeValueAsString(res);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
