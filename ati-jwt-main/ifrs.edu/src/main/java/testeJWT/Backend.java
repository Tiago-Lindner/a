package testeJWT;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

@Path("/getsum")
public class Backend {

    private static final Logger LOGGER = Logger.getLogger(Backend.class.getName());
    
    
    @Inject
    @Claim(standard = Claims.email)
    String email;

    @GET
    @Path("/{a}/{b}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({ "Admin" })
    public int getSum(@PathParam("a") int a, @PathParam("b") int b){
        LOGGER.log(Level.INFO, "Backend: {0}", email);
        return a + b;
    }
}
