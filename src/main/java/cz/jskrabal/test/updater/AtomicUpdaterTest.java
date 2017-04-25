package cz.jskrabal.test.updater;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AtomicUpdaterTest {
	public static final int ITERATIONS = 10_000_000;

	public static void main(String[] args) {
		Pojo pojo = new Pojo();

		List<String> results = new ArrayList<>();

		long start = System.currentTimeMillis();
		for(long i = 0; i < ITERATIONS ; i++) {
			pojo.setAtomicLongVar(i);
		}
		results.add("Atomic long set " + (System.currentTimeMillis() - start) + " ms");

		start = System.currentTimeMillis();
		for(long i = 0; i < ITERATIONS ; i++) {
			pojo.getAtomicLongVar();
		}
		results.add("Updater long get " + (System.currentTimeMillis() - start) + " ms");

		start = System.currentTimeMillis();
		for(long i = 0; i < ITERATIONS ; i++) {
			pojo.setLongVar(i);
		}
		results.add("Updater long set " + (System.currentTimeMillis() - start) + " ms");

		start = System.currentTimeMillis();
		for(long i = 0; i < ITERATIONS ; i++) {
			pojo.getLongVar();
		}
		results.add("Updater long get " + (System.currentTimeMillis() - start) + " ms");

		start = System.currentTimeMillis();
		for(long i = 0; i < ITERATIONS ; i++) {
			pojo.incrAtomicLongVar(i);
		}
		results.add("Atomic long incr " + (System.currentTimeMillis() - start) + " ms");

		start = System.currentTimeMillis();
		for(long i = 0; i < ITERATIONS ; i++) {
			pojo.incrLongVar(i);
		}
		results.add("Updater long incr " + (System.currentTimeMillis() - start) + " ms");

		results.add("---");

		start = System.currentTimeMillis();
		for(double i = 0; i < ITERATIONS ; i++) {
			pojo.setDoubleVar(i);
		}
		results.add("Updater Double Reference set " + (System.currentTimeMillis() - start) + " ms");

		start = System.currentTimeMillis();
		for(double i = 0; i < ITERATIONS ; i++) {
			pojo.getDoubleVar();
		}
		results.add("Updater Double Reference get " + (System.currentTimeMillis() - start) + " ms");

		start = System.currentTimeMillis();
		for(double i = 0; i < ITERATIONS ; i++) {
			pojo.setDoubleToLongBits(i);
		}
		results.add("Updater DoubleAsLongBits Reference set " + (System.currentTimeMillis() - start) + " ms");

		start = System.currentTimeMillis();
		for(double i = 0; i < ITERATIONS ; i++) {
			pojo.getDoubleToLongBits();
		}
		results.add("Updater DoubleAsLongBits Reference get " + (System.currentTimeMillis() - start) + " ms");

		start = System.currentTimeMillis();
		for(double i = 0; i < ITERATIONS ; i++) {
			pojo.incrDoubleVar(i);
		}
		results.add("Updater Double Reference incr " + (System.currentTimeMillis() - start) + " ms");

		start = System.currentTimeMillis();
		for(double i = 0; i < ITERATIONS ; i++) {
			pojo.incrDoubleToLongBits(i);
		}
		results.add("Updater DoubleAsLongBits Reference incr " + (System.currentTimeMillis() - start) + " ms");


		System.out.println(results.stream()
				.collect(Collectors.joining("\n")));
	}

}


