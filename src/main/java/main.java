import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Subdivision;

import java.io.IOException;
import java.net.InetAddress;

import java.io.*;
import java.net.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main
{
    public static void main(String[] args) throws IOException, GeoIp2Exception {
        String LicenseKey = "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";
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("ShodanData_Sanitization by: Obfuscated Future");

        final JsonMapper mapper = new JsonMapper();
        final File JSON_MAP = new File("/Users/chase/IdeaProjects/ShodanData_Sanitization/src/main/java/mapJSON.ldjson");

        WebServiceClient client = new WebServiceClient.Builder(690395, LicenseKey).host("geolite.info").build();

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

        InetAddress ipAddress = null;
        // Do the lookup
        CityResponse response = null;

        String countryName = null;

        String stateName = null;

        City city = null;
        String cityName = null;

        Location location = null;
        Double latitude = null;
        Double longitude = null;
        Integer accuracy = null;
        String timezone = null;

        String userType = null;
        SequenceWriter seq = mapper.writer().withRootValueSeparator(System.lineSeparator()).writeValues(JSON_MAP);

        FileReader reader = null;
        try {
            reader = new FileReader("/Users/chase/IdeaProjects/ShodanData_Sanitization/Traffic_cams.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Scanner s = new Scanner(reader);
        while (s.hasNextLine())
        {
            String shodan_cam = s.nextLine();
            CSVobj shodan_cam_obj = null;

            try {
                shodan_cam_obj = objectMapper.readValue(shodan_cam, CSVobj.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            String ip = shodan_cam_obj.getIPStr();
            ipAddress = InetAddress.getByName(ip);
            // Do the lookup
            response = client.city(ipAddress);
            Country country = response.getCountry();
            countryName = country.getName();/**/
            Subdivision subdivision = response.getMostSpecificSubdivision();
            stateName = subdivision.getName();/**/
            city = response.getCity();
            cityName = city.getName();/**/
            location = response.getLocation();
            latitude = location.getLatitude();/**/
            longitude = location.getLongitude();/**/
            accuracy = location.getAccuracyRadius();/**/
            timezone = location.getTimeZone();/**/
            userType = response.getTraits().getUserType();

            if (accuracy == 1000)
            {
                //Do latitude and longitude deviations
                double randomlatDeviation = Math.random() * 0.001;
                double randomlongDeviation = Math.random() * 0.001;

                Random rand = new Random();
                boolean neg = rand.nextBoolean();

                if (neg) {
                    latitude = latitude + (latitude * randomlatDeviation * -1);
                } else {
                    latitude = latitude + (latitude * randomlatDeviation);
                }
                neg = rand.nextBoolean();
                if (neg) {
                    longitude = longitude + (longitude * randomlongDeviation * -1);
                } else {
                    longitude = longitude + (longitude * randomlatDeviation);
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("link", ip+":"+shodan_cam_obj.getPort()+"/IMAGE.JPG?cidx=2023921149571043407");
            map.put("ip", ip);
            map.put("country", countryName);
            map.put("state", stateName);
            map.put("city", cityName);
            map.put("latitude", latitude);
            map.put("longitude", longitude);
            map.put("accuracy", accuracy);
            map.put("timezone", timezone);

            map.put("userType", userType);

            LocatedJson cam = new LocatedJson(ip, countryName, stateName, cityName, latitude, longitude, accuracy, timezone, userType);
            System.out.println(cam);
            seq.write(cam);
        }
        /** End of ldjson creation **/
        /** Start of ldjson to csv file **/
        //final CsvMapper CSV_MAPPER = new CsvMapper();


        final File outputFile = new File("data.ldjson");



    }
}
