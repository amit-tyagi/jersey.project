package com.apc.its.jersey.ws;

import com.apc.its.dao.TodoDao;
import com.apc.its.jaxb.model.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

/**
 * User: kav192
 * Date: 9/12/12
 * Time: 11:55 AM
 */
public class TodoResource {

    @Context
    private UriInfo uriInfo;

    @Context
    private Request request;

    private String id;

    public TodoResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Todo getTodo() {
        Todo todo = TodoDao.instance.getModel().get(id);
        if(todo == null) {
            throw new RuntimeException("GET: Todo with " + id + " not found");
        }

        return todo;
    }

    //For the browser
    @GET
    @Produces({MediaType.TEXT_XML})
    public Todo getTodoHTML() {
        Todo todo = TodoDao.instance.getModel().get(id);
        if(todo == null) {
            throw new RuntimeException("GET: Todo with " + id + " not found");
        }

        return todo;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putTodo(JAXBElement<Todo> todo) {
        Todo c = todo.getValue();
        return putAndGetResponse(c);
    }

    @DELETE
    public void deleteTodo() {
        Todo c = TodoDao.instance.getModel().remove(id);
        if(c == null) {
            throw new RuntimeException("DELETE: Todo with " + id + " not found");
        }
    }

    private Response putAndGetResponse(Todo todo) {
        Response res;
        if(TodoDao.instance.getModel().containsKey(todo.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }

        TodoDao.instance.getModel().put(todo.getId(), todo);

        return res;
    }

    // this method is called if XML is requested
    @GET
    @Path("xml")
    @Produces({MediaType.APPLICATION_XML})
    public Todo getXML() {
        System.out.println("getXML() called..");
        Todo todo = new Todo();
        todo.setSummary("This is my first todo");
        todo.setDescription("This is my first todo description");

        return todo;
    }

    // this method is called if XML is requested
    @GET
    @Path("json")
    @Produces({MediaType.APPLICATION_JSON})
    public Todo getJSON() {
        System.out.println("getJSON() called..");
        Todo todo = new Todo();
        todo.setSummary("This is my first todo");
        todo.setDescription("This is my first todo description");

        return todo;
    }

    // this can be used to test the integration with the browser
    @GET
    @Path("/html")
    @Produces({MediaType.TEXT_HTML})
    public String getHTML() {
        System.out.println("getHTML() called..");

        return "<html><body>Hello!! This is a response from <b>Jersey</b> REST service</body></html>";
    }
}
