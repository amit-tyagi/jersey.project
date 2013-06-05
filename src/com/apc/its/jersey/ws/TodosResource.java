package com.apc.its.jersey.ws;

import com.apc.its.dao.TodoDao;
import com.apc.its.jaxb.model.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: kav192
 * Date: 9/12/12
 * Time: 11:55 AM
 */
// will map the resource to the URL 'todos'
@Path("/todos")
public class TodosResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    //For browser
    @GET
    @Produces({MediaType.TEXT_XML})
    public List<Todo> getTodosHTML() {
        List<Todo> todos = new ArrayList<Todo>();
        todos.addAll(TodoDao.instance.getModel().values());
        return todos;
    }

    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<Todo>();
        todos.addAll(TodoDao.instance.getModel().values());
        return todos;
    }

    // retuns the number of todos
    // Use http://localhost:8080/<context-root>/<jersey-servlet-mapping>/todos/count
    // to get the total number of records
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = TodoDao.instance.getModel().size();
        return String.valueOf(count);
    }

    // Defines that the next path parameter after todos is
    // treated as a parameter and passed to the TodoResources
    // Allows to type http://localhost:8080/<context-root>/<jersey-servlet-mapping>/todos/1
    // 1 will be treaded as parameter todoParam and passed to TodoResource
    @Path("{todoParam}")
    public TodoResource getTodo(@PathParam("todoParam") String id) {
        return new TodoResource(uriInfo, request, id);
    }

//    @POST
//    @Produces(MediaType.TEXT_HTML)
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public void newTodo(@FormParam("id") String id, @FormParam("summary") String summary,
//                        @FormParam("description") String description,
//                        @Context HttpServletResponse servletResponse) throws IOException {
//        Todo todo = new Todo(id, summary);
//        if(description != null) {
//            todo.setDescription(description);
//        }
//        TodoDao.instance.getModel().put(id, todo);
//
//        servletResponse.sendRedirect("../create_todo.html");
//    }
}
