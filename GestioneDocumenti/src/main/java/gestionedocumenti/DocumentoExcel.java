package gestionedocumenti;

public final class DocumentoExcel extends Documento{
    public DocumentoExcel() {
        super(TipoDocumento.DOCUMENTO_EXCEL);
    }

    @Override
    public void info() {
        System.out.println("Il documento è di tipo: " + getTIPO() + " e lo si può aprire con Microsoft Excel.");
    }
}
