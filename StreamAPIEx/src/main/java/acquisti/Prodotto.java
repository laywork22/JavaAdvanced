package acquisti;

import java.util.Objects;

public class Prodotto {
    private final int id;
    private final String nome;
    private final String categoria;
    private double prezzo;

    public Prodotto(int id, String nome, String categoria, double prezzo) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo){
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return  "{id prodotto=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return id == prodotto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
