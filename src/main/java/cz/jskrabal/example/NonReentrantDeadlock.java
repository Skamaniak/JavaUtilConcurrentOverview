package cz.jskrabal.example;

import java.util.concurrent.locks.StampedLock;

public class NonReentrantDeadlock {
	public static void main(String[] args) {

		StampedLock lock = new StampedLock();

		lock.writeLock();
		System.out.println("Locked for write");
		lock.writeLock();
		System.out.println("Unreachable print");
	}
}
