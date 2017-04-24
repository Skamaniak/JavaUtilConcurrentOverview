package cz.jskrabal.example;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by Jan Skrabal skrabalja@gmail.com
 */
public class StampedAtomicReference {
    static AtomicStampedReference<Object> atomicStampedReference = new AtomicStampedReference<>("initial value", 0);

    public static void main(String[] args) {
        int[] stampHolder = new int[1];
        Object ref = atomicStampedReference.get(stampHolder);

        if(ref == null){
            //prepare optimistic modification
        }

        //if another thread changes the reference and stamp here,
        //it can be detected

        int[] stampHolder2 = new int[1];
        Object ref2 = atomicStampedReference.get(stampHolder);

        if(ref == ref2 && stampHolder[0] == stampHolder2[0]){
            //no modification since optimistic modification was prepared

        } else {
            //retry from scratch.
        }
    }
}
