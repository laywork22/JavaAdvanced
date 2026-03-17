public class Studente extends Persona{
    private String matricola;

    public Studente(String nome, String cognome, String codiceFiscale, String matricola) {
        super(nome, cognome, codiceFiscale);
        this.matricola = matricola;
    }

    public Studente() {
        super();
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }


    @DaCompletare(assegnatoA = "Mario Rossi", entroData = "20-03-2026")
    @Requisito("Non deve essere null")
    @Requisito("Deve essere autogenerato")
    public String generaMatricola() {
        return null;
    }

    @Override
    public String toString() {
        return "Studente{" +
                "matricola='" + matricola + '\'' +
                "} " + super.toString();
    }
}
