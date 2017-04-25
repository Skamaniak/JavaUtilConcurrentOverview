package cz.jskrabal.example;

import java.util.function.BiFunction;

public class BiFunctionExample {
	public static void main(String[] args) {

		BiFunction<String, Object, String> songify =
				(x, y) -> "Pen-"+ x + "-" + y + "-Pen!";

		System.out.println(
				songify.apply("Pineapple", new Apple())
		);
	}

	public static class Apple {
		@Override
		public String toString() {
			return "Apple";
		}
	}
}
