import annotation.StrictImmutable;

@StrictImmutable
public class TestStrictImmutableProcessor {
    public final int c;
    public double d;

    public TestStrictImmutableProcessor(int c) {
        this.c = c;
        this.d = d;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public int getC() {
        return c;
    }
}
