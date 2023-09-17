/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.yourally.resources.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.project.yourally.entity.Appointment;
import com.project.yourally.entity.Board;
import com.project.yourally.entity.Message;
import com.project.yourally.repository.DiscussionRepository;
import com.project.yourally.utils.APIResponse;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
 
@Path("discussion")
public class DiscussionController implements Serializable {

    private static final long serialVersionUID = 1L;

    @POST
    @Path("load")
    public Response getOrCreateBoard(Appointment app) {
        DiscussionRepository rep = new DiscussionRepository();
        Board board = rep.getOrCreateDiscussionForum(app);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "{}";
        try {

            json = ow.writeValueAsString(board);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("send")
    public Response sendMessage(Message message) {

        DiscussionRepository rep = new DiscussionRepository();
        
        APIResponse res= new APIResponse();
        
        res = rep.saveMessage(message);
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
