package acquisti;

import java.util.Objects;

public class Cliente {
    private final int id;
    private String nome;
    private int livello;

    public Cliente(int id, String nome, int livello) {
        this.id = id;
        this.nome = nome;
        this.livello = livello;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    @Override
    public String toString() {
        return
                "{id cliente=" + id +
                ", nome='" + nome + '\'' +
                ", livello=" + livello +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
