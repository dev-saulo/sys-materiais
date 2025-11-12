package model;

public class Usuario {

    private String matricula;
    private String nome;
    private boolean ativo;

    public Usuario() {}

    public Usuario(String matricula, String nome, boolean ativo) {
        this.matricula = matricula;
        this.nome = nome;
        this.ativo = ativo;
    }

    // Getters and Setters
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}