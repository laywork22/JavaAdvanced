package it.unisa.data;

import it.unisa.myannotations.AtMostThree;
import it.unisa.myannotations.FieldNumberConstraint;

@AtMostThree
@FieldNumberConstraint(2)
public class LongClass {
    public int v1;
    public int v2;

    public void m1() {}
    public void m2() {}
    public void m3() {}
    public void m4() {}
}
