
package com.example.weatherdemo.service.impl;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.weatherdemo.exception.MyWeatherException;
import com.example.weatherdemo.model.Weather;
import com.example.weatherdemo.model.WeatherDetails;
import com.example.weatherdemo.service.WeatherService;


@Service
public class WeatherServiceImpl  implements WeatherService {


    @Autowired
    RestTemplate restTemplate;

    @Value("${weather.api.url}")
    private String url;

    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Override
    public List<Weather> getWeatherData(String city) throws Exception{

        // get the api response

        List<Weather> weatherResponse = getWeathers(city);
        logger.info("Weather Reponse ",weatherResponse);
        //since report gets generted every 5 hour split the date-time to just have date

        //weatherResponse .forEach(day ->{
          //  DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //LocalDate date1 = LocalDate.parse(day.getDate().split(" ")[0], df);
            //day.setDate(date1.toString());
        //});


        return weatherResponse;

    }

    private List<Weather> getWeathers(String city)throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {

            return restTemplate.exchange(
                    url+city,
                    HttpMethod.GET,
                    entity,
                    WeatherDetails.class).getBody().getWeather();

        }
        catch (Exception e)
        {
            throw new MyWeatherException("Error occured while caling openweather",e);
        }
        
    }
}