package chapter2.src;

import java.util.ArrayList;
import java.util.List;

public class RandomNumbers {

	public RandomNumbers() {
		// TODO Auto-generated constructor stub
	}

	public int randomElement(int[] array) {

		if (array.length == 0) {
			return 0;
		} else {
			int randomIndex = (int) (Math.random() * array.length);
			return array[randomIndex];
		}
	}

	public int randomElement(List<Integer> list) {

		if (list.size() == 0) {
			return 0;
		} else {
			int randomIndex = (int) (Math.random() * list.size());
			return list.get(randomIndex);
		}
	}

	public static void main(String[] args) {

		// int randomNum = (int)(Math.random() * 10);
		// int count = 0;
		//
		// while(randomNum != 10) {
		// randomNum = (int)(Math.random() * 10);
		// count++;
		// }
		//
		// System.out.println(randomNum);
		// System.out.println(count);

		RandomNumbers rn = new RandomNumbers();
		// Integer[] numArray = {1,2,3,4,5,6,7,8,9};
		int[] numArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] emptyArray = {};
		List<Integer> numList = new ArrayList<>();
		List<Integer> emptyList = new ArrayList<>();

		for (int i = 10; i <= 20; i++) {
			numList.add(i);
		}

		// 실행 코드
		// 배열
		System.out.println(rn.randomElement(emptyArray));
		System.out.println(rn.randomElement(numArray));
		
		// ArrayList
		System.out.println(rn.randomElement(emptyList));
		System.out.println(rn.randomElement(numList));
		
		// int[] 형 배열은 소스를 확인 할 수도 없고 ArrayList의 경우는 
		// 소스만 확인 가능하고 수정이 불가능하다.
		// 기본 제공되는 클래스이기 때문에 수정이 불가능하다.

	}
}
