package com.csc340.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
        //getCatFact();
        //getWords();
        //System.exit(0);
    }

    /**
     * Get a random cat fact and print to console
     */
    public static void getCatFact() {
        try {
            String url = "https://catfact.ninja/fact";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonFact = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonFact);

            String fact = root.findValue("fact").asText();
            String length = root.findValue("length").asText();

            System.out.println("**********CAT FACT********");
            System.out.println("Fact: " + fact);
            System.out.println("Length: " + length);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiApplication.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
            System.out.println("error in getCatFact");

        }
    }

    /**
    * Use the Data Muse Word Finder API to get synonyms.
    */
    public static void getWords() {
        String word = "happy";

        try {
            String url = "https://api.datamuse.com/words?ml=" + word;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String similarWords = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(similarWords);

            System.out.println("\nwords similar to " + word + ":");
            for (int i = 0; i < 5; i++) {
                System.out.println(root.get(i).get("word"));
            }
        } catch (JsonProcessingException ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }
}
