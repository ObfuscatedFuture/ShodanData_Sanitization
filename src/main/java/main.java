import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class main
{
    public static void main(String[] args)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("ShodanData_Sanitization by: Obfuscated Future");


        FileReader reader = null;
        try {
            reader = new FileReader("/Users/chase/IdeaProjects/ShodanData_Sanitization/Traffic_cams.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //TODO Allow dynamic text files to be read from web
        /*
        try {
            URL url = new URL("https://github.com/ObfuscatedFuture/ShodanData_Sanitization/blob/5267386c80bf796428427015401123556a90c9c8/Traffic_cams.json");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            Scanner s = new Scanner(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        Scanner s = new Scanner(reader);
        String test = s.nextLine();
        System.out.println(test);
        CSVobj t = null;
        try {
            t = objectMapper.readValue(test, CSVobj.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(t.getIP());

    }
}
