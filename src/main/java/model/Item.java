package model;

public class Item {

    private Long id;
    private String codigo;
    private String nome;
    private String cadastradoPor;
    private boolean ativo;

    public Item() {}

    public Item(Long id, String codigo, String nome, String cadastradoPor, boolean ativo) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.cadastradoPor = cadastradoPor;
        this.ativo = ativo;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCadastradoPor() { return cadastradoPor; }
    public void setCadastradoPor(String cadastradoPor) { this.cadastradoPor = cadastradoPor; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
