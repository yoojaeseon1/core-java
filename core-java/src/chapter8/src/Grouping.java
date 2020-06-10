package chapter8.src;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grouping {

	public static void main(String[] args) {
		
		
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		
		
//		Map<String, List<Locale>> countryToLocales = locales.collect(Collectors.groupingBy(Locale::getCountry));
		
		
//		List<Locale> swissLocales = countryToLocales.get("CH");
//		
//		System.out.println(swissLocales.toString());
		
		Map<Boolean, List<Locale>> englishAndOtherLocales = locales.collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));
		
		List<Locale> englishLocales = englishAndOtherLocales.get(true);
		
		System.out.println(englishLocales.toString());
		

	}

}
