package cz.jskrabal.test.updater;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;


public class Pojo {

	private static final AtomicLongFieldUpdater<Pojo> LONG_UPDATER = AtomicLongFieldUpdater.newUpdater(Pojo.class,
			"longVar");
	private static final AtomicReferenceFieldUpdater<Pojo, Double> DOUBLE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(
			Pojo.class, Double.class, "doubleVar");

	private final AtomicLong atomicLongVar = new AtomicLong(0);
	private volatile long longVar = 0;
	private volatile Double doubleVar = 0D;
	private final AtomicLong doubleToLongBits = new AtomicLong(0);


	public void setAtomicLongVar(long l) {
		atomicLongVar.set(l);
	}

	public void setLongVar(long l) {
		LONG_UPDATER.set(this, l);
	}

	public void setDoubleVar(double d) {
		DOUBLE_UPDATER.set(this, d);
	}

	public void setDoubleToLongBits(double d) {
		doubleToLongBits.set(Double.doubleToLongBits(d));
	}

	public void incrAtomicLongVar(long incr) {
		atomicLongVar.addAndGet(incr);
	}

	public void incrLongVar(long l) {
		LONG_UPDATER.addAndGet(this, l);
	}

	public void incrDoubleVar(double d) {
		DOUBLE_UPDATER.updateAndGet(this, old -> old + d);
	}

	public void incrDoubleToLongBits(double d) {
		doubleToLongBits.accumulateAndGet(Double.doubleToLongBits(d),
				(left, right) -> Double.doubleToLongBits(Double.longBitsToDouble(left) + Double.longBitsToDouble(right)));
	}
}