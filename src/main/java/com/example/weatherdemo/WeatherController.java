package com.example.weatherdemo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.weatherdemo.model.Weather;
import com.example.weatherdemo.service.WeatherService;
import com.example.weatherdemo.service.impl.WeatherServiceImpl;

@RestController
public class WeatherController{
	
	@Autowired
	WeatherService weatherService;
	
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

	
	@RequestMapping(value = "/weather/{city}", method=RequestMethod.GET)
	public ResponseEntity<String> getWeatherSuggestion(@PathVariable String city) { 
		System.out.println("City Name: "+city);
		boolean isRainingLast3Days = false;
		int temperatue =0;
		
		try {
			List<Weather> weathers = weatherService.getWeatherData(city);
		}catch(Exception e) {
			logger.error("Error while calling Weather API:"+e.toString());
		}
		
		if(isRainingLast3Days)
			return new ResponseEntity<String>("Carry Umbrella",HttpStatus.OK);
		else if (temperatue > 40)
			return new ResponseEntity<String>("Use Sunscreen Lotions",HttpStatus.OK);
		
		return new ResponseEntity<String>("No Suggestions",HttpStatus.OK);
	}
}
