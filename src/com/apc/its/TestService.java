package com.apc.its;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.apc.its.jaxb.model.Todo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

import java.io.IOException;
import java.net.URI;

/**
 * User: kav192
 * Date: 9/12/12
 * Time: 9:54 AM
 */
public class TestService {
    public static void main(String[] args) throws IOException {
        testTodosService();
//        testTodoService();
//        testHelloService();
    }

    private static void testTodosService() {
        ClientConfig config = new DefaultClientConfig();
        Client client  = Client.create(config);
        WebResource service = client.resource(getBaseURI());

        // Create one Todo
//        Todo todo = new Todo("3", "This is added via call");
//        ClientResponse response = service.path("rest").path("todos")
//                .path(todo.getId()).accept(MediaType.APPLICATION_XML)
//                .put(ClientResponse.class, todo);
//        // Return code should be 201 == created resource
//        System.out.println(response.getStatus());

        // Delete
//        service.path("rest").path("todos/3").delete();

        Form form = new Form();
        form.add("id", 4);
        form.add("summary", "Demonstration of client lib for forms");
        ClientResponse response = service.path("rest").path("todos")
                .type(MediaType.APPLICATION_FORM_URLENCODED)
                .post(ClientResponse.class, form);
        System.out.println("Form response: " + response.getEntity(String.class));
    }

    private static void testTodoService() {
        ClientConfig config = new DefaultClientConfig();
        Client client  = Client.create(config);
        WebResource service = client.resource(getBaseURI());

        //Fluent interfaces
        System.out.println(service.path("rest").path("todo").path("json")
                .accept(MediaType.APPLICATION_JSON).get(ClientResponse.class).toString());
        //Get XML for application
        System.out.println(service.path("rest").path("todo").path("xml")
                .accept(MediaType.APPLICATION_XML).get(String.class));
        //Get JSON for application
        System.out.println(service.path("rest").path("todo").path("json")
                .accept(MediaType.APPLICATION_JSON).get(String.class));
    }

    private static void testHelloService() {
        ClientConfig config = new DefaultClientConfig();
        Client client  = Client.create(config);

        WebResource service = client.resource(getBaseURI());
        //Fluent interfaces
        System.out.println(service.path("rest").path("hello")
                .accept(MediaType.TEXT_PLAIN).get(ClientResponse.class).toString());
        //Get plain text
        System.out.println(service.path("rest").path("hello")
                .accept(MediaType.TEXT_PLAIN).get(String.class));
        //Get XML
        System.out.println(service.path("rest").path("hello")
                .accept(MediaType.TEXT_XML).get(String.class));
        //Get HTML
        System.out.println(service.path("rest").path("hello")
                .accept(MediaType.TEXT_HTML).get(String.class));
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/jersey").build();
    }
}
