package com.example.weatherdemo.service;

import java.util.List;

import com.example.weatherdemo.model.Weather;


public interface WeatherService {

	public List<Weather> getWeatherData(String city) throws Exception;
	
}




