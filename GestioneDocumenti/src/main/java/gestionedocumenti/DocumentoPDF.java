package gestionedocumenti;

public final class DocumentoPDF extends Documento{
    public DocumentoPDF() {
        super(TipoDocumento.DOCUMENTO_PDF);
    }

    @Override
    public void info() {
        System.out.println("Il documento è di tipo: " + getTIPO() + " e lo si può aprire con Adobe Acrobat.");
    }
}
