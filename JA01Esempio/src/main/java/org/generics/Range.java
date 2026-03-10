package org.generics;

//Per confrontare due generics si fa estendere l'interfaccia Comparable a T
public class Range<T extends Number & Comparable<T>> {
    private T low;
    private T high;

    public Range(T low, T high) {
        this.low = low;
        this.high = high;

        if (low.compareTo(high) > 0) {
            throw new IllegalArgumentException();
        }
    }

    public boolean checkElement(T elem) {
        return elem.compareTo(low) > 0 && elem.compareTo(high) < 0;
    }

    public T getHigh() {
        return high;
    }

    public T getLow() {
        return low;
    }

    @Override
    public String toString() {
        return "Low: " + low + ", high: " + high;
    }


}
