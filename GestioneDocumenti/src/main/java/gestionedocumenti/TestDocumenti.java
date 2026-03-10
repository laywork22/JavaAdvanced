package gestionedocumenti;

public class TestDocumenti {
    public static void main(String[] args) {
        DocumentoPDF d1 = new DocumentoPDF();
        DocumentoWord d2 = new DocumentoWord();
        DocumentoExcel d3 = new DocumentoExcel();

        Documento d = null;

        DoppioClick dc = new DoppioClick();

        System.out.println("=== TEST DOCUMENTI ===");
        d1.info();
        d2.info();
        d3.info();

        System.out.println("=== TEST DOPPIOCLICK ===");
        dc.apri(d1);
        dc.apri(d2);
        dc.apri(d3);
        dc.apri(d);
    }
}
