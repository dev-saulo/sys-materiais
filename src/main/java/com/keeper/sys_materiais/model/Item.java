package com.keeper.sys_materiais.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tb_itenscadastrados")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_COD_ItemITE")
    private Long id;

    // Campo exposto na API, mas ainda não persistido no banco
    @Transient
    private String codigo;

    @Column(name = "TXT_NomeItemITE", nullable = false, length = 150)
    private String nome;

    @Column(name = "FK_UsuarioITE", nullable = false, length = 100)
    private String cadastradoPor;

    @Column(name = "COD_StatusITE", columnDefinition = "tinyint(1) DEFAULT 1")
    private Boolean ativo;

    @Column(name = "DAT_CadastroITE", updatable = false, insertable = false, columnDefinition = "datetime DEFAULT current_timestamp()")
    private LocalDateTime dataCadastro;

    @Column(name = "DAT_AlteracaoITE", insertable = false, columnDefinition = "datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()")
    private LocalDateTime dataAlteracao;

    // Construtores
    public Item() {}

    public Item(String nome, String cadastradoPor, Boolean ativo) {
        this.nome = nome;
        this.cadastradoPor = cadastradoPor;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCadastradoPor() { return cadastradoPor; }
    public void setCadastradoPor(String cadastradoPor) { this.cadastradoPor = cadastradoPor; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public LocalDateTime getDataAlteracao() { return dataAlteracao; }
    public void setDataAlteracao(LocalDateTime dataAlteracao) { this.dataAlteracao = dataAlteracao; }
}
