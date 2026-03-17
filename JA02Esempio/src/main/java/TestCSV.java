public class TestCSV {
    @CSV
    private Studente s1;
    @CSV
    private Studente s2;
    @CSV
    private Persona p1;

    public void printAll() {
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(p1);
    }
}
