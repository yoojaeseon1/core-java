package chapter6.src;

import java.util.Arrays;

public class Entry<K, V> {
	
	private K key;
	private V value;
	
	
	public Entry(){
		
	}
	
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}



	public void setValue(V value) {
		this.value = value;
	}
	
	public static <T> void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		throw new RuntimeException();
	}



	public static void main(String[] args) {
		
		Integer[] array = {1,2,3,4,5};
		
//		Entry.swap(array, 0, 3);
		Entry.<Integer>swap(array, 0, 3);
		
		System.out.println(Arrays.toString(array));

	}
}
