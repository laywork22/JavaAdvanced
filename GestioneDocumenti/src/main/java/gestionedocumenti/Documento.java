package gestionedocumenti;

public abstract sealed class Documento permits DocumentoExcel, DocumentoPDF, DocumentoWord {
    private final TipoDocumento TIPO;

    protected Documento(TipoDocumento tipo) {
        TIPO = tipo;
    }

    public abstract void info();

    public TipoDocumento getTIPO() {
        return TIPO;
    }
}
