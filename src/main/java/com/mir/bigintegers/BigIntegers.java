package com.mir.bigintegers;

import java.math.BigInteger;

/**
 * Created by Mir on 24/09/2016.
 */
public class BigIntegers {

    public static void main (String[] args) {
        BigInteger  i = BigInteger.valueOf(100);
        BigInteger j = BigInteger.valueOf(300);

        BigInteger times = i.multiply(j);
        BigInteger add = i.add(j);
        System.out.println("i=" + i.longValueExact() + " j=" + j.longValue() + " max=" + i.max(j).longValueExact());

        long l = 1L;
        float f = 1F;
        double d = 1D;

        System.out.println(" l=" + Long.MAX_VALUE + " f=" + Float.MAX_VALUE + " d=" + Double.MAX_VALUE);

    }
}
