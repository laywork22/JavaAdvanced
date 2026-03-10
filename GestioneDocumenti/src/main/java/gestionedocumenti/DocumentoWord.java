package gestionedocumenti;

public final class DocumentoWord extends Documento {
    public DocumentoWord() {
        super(TipoDocumento.DOCUMENTO_WORD);
    }

    @Override
    public void info() {
        System.out.println("Il documento è di tipo: " + getTIPO() + " e lo si può aprire con Microsoft Word.");

    }
}
