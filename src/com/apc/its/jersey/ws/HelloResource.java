package com.apc.its.jersey.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * POJO, no interface no extends
 * The class registers its methods for the HTTP GET request using the @GET annotation.
 * Using the @Produces annotation, it defines that it can deliver several MIME types,
 * text, XML and HTML.
 * The browser requests per default the HTML MIME type.
 * <p/>
 * User: kav192
 * Date: 9/12/12
 * Time: 9:48 AM
 */

//Sets the path to base URL + /hello
@Path("/hello")
public class HelloResource {

    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Amit!! Welcome to Jersey World!!";
    }

    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello>Hello! This is an XML response!!</hello>";
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html><title>Hello Jersey</title>"
                + "<body><h1>Hello! This is an HTML response!!</body></html> ";
    }
}
