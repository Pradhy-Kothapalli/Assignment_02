package com.csc340.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sunny
 */
@RestController
public class RestApiController {

    /**
     * Joke API endpoint.
     *
     * @return a joke. If its a 2 part joke, then it returns the setup and the delivery, else returns the joke.
     */
    
    
    @GetMapping("/joke")
    public Object getJoke() {
        try {
            String url = "https://v2.jokeapi.dev/joke/Any?safe-mode";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            //Print the whole response to console.
            System.out.println(root);

            //Print relevant info to the console
            String type = root.get("type").asText();
            if (type.equalsIgnoreCase("twopart"))
            {
                String setup = root.get("setup").asText();
                String delivery = root.get("delivery").asText();
                System.out.println("setup: "+setup + ", Delivery: " +delivery);
                
            }
            else
            {
                String joke = root.get("joke").asText();
                System.out.println(joke);
            }
                //String country = root.get("country").asText();
                
            

            return root;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiController.class.getName()).log(Level.SEVERE,
                    null, ex);
            return "error in /joke";
        }

    }
    
}
