package cz.jskrabal.example.atomic;

import java.util.concurrent.atomic.LongAccumulator;

public class AtomicExample {
	private static final LongAccumulator ACCUMULATOR =
			new LongAccumulator((x, y) -> x * y, 1);

	public static void main(String[] args) {
		ACCUMULATOR.accumulate(10);
		System.out.println(ACCUMULATOR.get());
		ACCUMULATOR.accumulate(10);
		System.out.println(ACCUMULATOR.get());
		ACCUMULATOR.accumulate(10);
		System.out.println(ACCUMULATOR.get());
	}
}

