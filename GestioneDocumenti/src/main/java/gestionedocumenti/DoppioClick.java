package gestionedocumenti;

public class DoppioClick {
    public void apri(Documento d) {
        if (d != null) {
            switch(d) {
                case DocumentoExcel de ->
                        System.out.println("Sto aprendo il documento Excel con MS Excel");
                case DocumentoPDF dp ->
                        System.out.println("Sto aprendo il documento PDF con Adobe ACrobat");
                case DocumentoWord dw ->
                        System.out.println("Sto aprendo il documento Word con MS Word");
                default ->
                        System.err.println("Il documento non è in un formato supportato");
            }
        } else{
            System.err.println("Errore: documento inesistente");
        }

    }
}
