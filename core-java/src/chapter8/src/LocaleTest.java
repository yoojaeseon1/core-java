package chapter8.src;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocaleTest {

	public static void main(String[] args) {
		
		
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		
//		Map<String, String> languageNames = locales.collect(
//				Collectors.toMap(
//						Locale::getDisplayLanguage,
//						Locale::getDisplayLanguage,
//						(existingValue, newValue) -> "duplicated : " + existingValue
//						)
//				);
//		
//		for(Entry entry : languageNames.entrySet()) {
//			
//			System.out.println("key  : " + entry.getKey());
//			System.out.println("value : " + entry.getValue());
//			System.out.println("---------");
//			
//		}
		
//		Map<String, Set<String>> countryLanguageSets = locales.collect(
//				Collectors.toMap(
//						Locale::getDisplayCountry,
//						l -> Collections.singleton(l.getDisplayLanguage()),
//						(a,b) ->{
//							Set<String> union = new HashSet<>(a);
//							union.addAll(b);
//							return union;
//						}
//						)
//				);
//		
//		for(Entry entry : countryLanguageSets.entrySet()) {
//			
//			System.out.println("key : " + entry.getKey());
//			System.out.println("value : " + entry.getValue());
//			
//		}
		
		Person[] persons = new Person[3];
		
		persons[0] = new Person(123, "yoo");
		persons[1] = new Person(1234, "yoo11");
		persons[2] = new Person(345, "yoo22");
		
		Stream<Person> stream = Stream.of(persons);
		
		Map<Integer, Person> idToPerson = stream.collect(
				Collectors.toMap(
						Person::getId,
						Function.identity(),
						(existingvalue, newValue) -> {throw new IllegalStateException();},
						TreeMap::new
						)
				);
	}

}
