package testeBff;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/sum")
public class bff {
    
    private static final Logger LOGGER = Logger.getLogger(bff.class.getName());

    @Inject
    @Claim(standard = Claims.full_name)
    String fullName;

    @Inject
    @RestClient
    BackendClient back;

    @GET
    @Path("/{a}/{b}")
    @RolesAllowed({"User", "Admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public int getSum(@PathParam("a") int a, @PathParam ("b") int b){
        LOGGER.log(Level.INFO, "BFF: {0}", fullName);
        return back.getSum(a, b);
    };

}
