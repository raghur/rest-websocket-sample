
package JerseyRest;

import org.example.BayeuxInitializer;
import org.example.EventBroadcaster;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/** Example resource class hosted at the URI path "/v2/user/someuser"
 */
@Path("/user/{name}")
public class MyResource {
    private static ConcurrentMap<String,String> users;
    @Context
    ServletContext context;


    static {
        users = new ConcurrentHashMap<String, String>();
    }
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("name") String name) {
        if (users.containsKey(name)) {
            return "Hi there, " + name;
        } else {
            return "Invalid user!";
        }
    }

    private EventBroadcaster getBroadcaster() {
        return (EventBroadcaster)context.getAttribute(BayeuxInitializer.BROADCAST_SVC);
    }
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String createIt(@PathParam("name") String name) {
        if (users.containsKey(name)) {
            return "User already exists";
        }
        users.putIfAbsent(name,"");
        getBroadcaster().userAdded(name);
        return "user added successfully!";
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("name") String name) {
        boolean removed = users.remove(name, "");
        if (removed)
            getBroadcaster().userRemoved(name);
        return removed? "User deleted": "User not found";
    }
}
