package acquisti;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Ordine {
    private final int id;
    private String stato;
    private final LocalDate dataOrdine;
    private LocalDate dataConsegna;
    private List<Prodotto> prodotti;
    private final Cliente cliente;

    public Ordine(int id, LocalDate dataOrdine, Cliente cliente, String stato, LocalDate dataConsegna) {
        this.id = id;
        this.dataOrdine = dataOrdine;
        this.cliente = cliente;
        this.stato = stato;
        this.prodotti = new ArrayList<>();
        this.dataConsegna = dataConsegna;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public LocalDate getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(LocalDate dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Stream<Prodotto> stream() {
        return prodotti.stream();
    }

    public void aggiungi(Prodotto p) {
        prodotti.add(p);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID ordine: ").append(id).
                append(", Stato: ").append(stato).
                append(", Data ordine: ").append(dataOrdine).
                append(", Data consegna: ").append(dataConsegna).append("\nProdotti:");

        for (Prodotto p : prodotti) {
            sb.append("\n").append(p);
        }

        sb.append("\n").append("acquisti.Cliente: ").append(cliente).append("\n");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ordine ordine = (Ordine) o;
        return id == ordine.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
