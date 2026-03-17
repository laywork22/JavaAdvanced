package it.unisa.data;

import it.unisa.myannotations.AtMostThree;
import it.unisa.myannotations.FieldNumberConstraint;

@AtMostThree
@FieldNumberConstraint(2)
public class ShortClass {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void t1() {}
}
