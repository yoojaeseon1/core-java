package chapter8.src;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DownStream {

	public static void main(String[] args) {

		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());

		// Map<String, Set<Locale>> countryToLocaleSet =
		// locales.collect(Collectors.groupingBy(Locale::getCountry,
		// Collectors.toSet()));
		//
		// for(Entry country : countryToLocaleSet.entrySet()) {
		// System.out.println("key : " + country.getKey());
		// System.out.println("value : " + country.getValue());
		// System.out.println("----------");
		// }

		// Map<String, Long> countryToLocaleCounts =
		// locales.collect(Collectors.groupingBy(Locale::getCountry,
		// Collectors.counting()));
		//
		// for(Entry country : countryToLocaleCounts.entrySet()) {
		// System.out.println("key : " + country.getKey());
		// System.out.println("value : " + country.getValue());
		// System.out.println("----------");
		// }

		City[] citiesArray = new City[5];

		citiesArray[0] = new City("New Work", "Queens", 10000);
		citiesArray[1] = new City("New Work", "Bronx", 20000);
		citiesArray[2] = new City("Texas", "yoooo", 30000);
		citiesArray[3] = new City("New Work", "Manhattan", 40000);
		citiesArray[4] = new City("Texas", "yoo", 50000);

		Stream<City> cities = Stream.of(citiesArray);

//		Map<String, Integer> stateToCityPopulation = cities
//				.collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
//		for (Entry city : stateToCityPopulation.entrySet()) {
//			System.out.println("key : " + city.getKey());
//			System.out.println("value : " + city.getValue());
//			System.out.println("----------");
//		}

//		Map<String, Optional<City>> stateToLargestCity = cities.collect(
//				Collectors.groupingBy(City::getState, Collectors.maxBy(Comparator.comparing(City::getPopulation))));
//
//		for (Entry<String, Optional<City>> city : stateToLargestCity.entrySet()) {
//			System.out.println("key : " + city.getKey());
//			System.out.println("value : " + city.getValue().toString());
//			System.out.println("----------");
//		}

//		Map<String, Optional<String>> stateToLongestCityName = cities.collect(Collectors.groupingBy(City::getState,
//				Collectors.mapping(City::getName, Collectors.maxBy(Comparator.comparing(String::length)))));
//
//		for (Entry<String, Optional<String>> city : stateToLongestCityName.entrySet()) {
//			System.out.println("key : " + city.getKey());
//			System.out.println("value : " + city.getValue().toString());
//			System.out.println("----------");
//		}

		Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities
				.collect(Collectors.groupingBy(City::getState, Collectors.summarizingInt(City::getPopulation)));

		for (Entry<String, IntSummaryStatistics> city : stateToCityPopulationSummary.entrySet()) {
			System.out.println("key : " + city.getKey());
			System.out.println("value : " + city.getValue().toString());
			System.out.println("----------");
		}

	}

}

class City {

	private String state;
	private String name;
	private int population;

	public City() {
		// TODO Auto-generated constructor stub
	}

	public City(String state, String name, int population) {
		this.state = state;
		this.name = name;
		this.population = population;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "City [state=" + state + ", name=" + name + ", population=" + population + "]";
	}

}
