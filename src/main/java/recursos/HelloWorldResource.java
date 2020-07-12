/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Salomão
 */
@Path("hello")
public class HelloWorldResource {
    
    @GET
    public String hellorWorld() {
        return "Hello World Java web Services!";
    }
    
}
