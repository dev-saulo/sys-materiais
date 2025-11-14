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

    @Column(name = "TXT_NomeItemITE", nullable = false, length = 150)
    private String nome;

    @Column(name = "FK_UsuarioITE", nullable = false, length = 100)
    private String cadastradoPor;

    @Column(name = "COD_StatusITE")
    private Boolean ativo;

    @Column(name = "DAT_CadastroITE", updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "DAT_AlteracaoITE")
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

    // Hooks JPA para preencher datas automaticamente
    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDateTime.now();
        this.dataAlteracao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAlteracao = LocalDateTime.now();
    }
}
