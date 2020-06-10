package chapter8.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalTest {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("10");
		list.add("100");
		list.add("1000");
		
		Stream<String> stream = list.stream().filter(s -> s.length() > 1);
		
//		Optional<String> optional = stream.max((x,y) -> x.compareTo(y));
		Optional<String> optional = stream.max(String::compareTo);
		
		
//		System.out.println(optional.orElse("haha"));
		
//		System.out.println(optional.orElseGet(()->System.getProperty("user.dir")));
//		System.out.println(optional.orElseThrow(IllegalStateException::new));
		
		
		optional.ifPresent(v->list.add(v));
		
		for(String element : list) {
			System.out.println(element);
		}
		
		Optional<Boolean> added = optional.map(list::add);

//		System.out.println(added.get());
		
		
		
		
	}
	
	public static Optional<Double> inverse(Double x) {
//		return x==0 ? Optional.empty() : Optional.of(1 / x);
		return Optional.ofNullable(1 / x);
	}

}
