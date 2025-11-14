package com.keeper.sys_materiais.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Tb_Solicitacoes")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_COD_SolicitacaoSOL")
    private Long id;

    @Column(name = "TXT_SolicitanteSOL", nullable = false, length = 100)
    private String solicitante;

    @Column(name = "DAT_SolicitacaoSOL")
    private LocalDateTime data;

    @Column(name = "COD_StatusSOL")
    private Boolean ativo;

    @Column(name = "DAT_CadastroSOL", updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "DAT_AlteracaoSOL")
    private LocalDateTime dataAlteracao;

    // Relacionamento com itens solicitados (N:N via tabela intermediária)
    @ManyToMany
    @JoinTable(
            name = "Tb_Solicitacao_Itens",
            joinColumns = @JoinColumn(name = "FK_SolicitacaoSOL"),
            inverseJoinColumns = @JoinColumn(name = "FK_ItemITE")
    )
    private List<Item> itens;

    // Construtores
    public Solicitacao() {}

    public Solicitacao(String solicitante, LocalDateTime data, Boolean ativo, List<Item> itens) {
        this.solicitante = solicitante;
        this.data = data;
        this.ativo = ativo;
        this.itens = itens;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSolicitante() { return solicitante; }
    public void setSolicitante(String solicitante) { this.solicitante = solicitante; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public LocalDateTime getDataAlteracao() { return dataAlteracao; }
    public void setDataAlteracao(LocalDateTime dataAlteracao) { this.dataAlteracao = dataAlteracao; }

    public List<Item> getItens() { return itens; }
    public void setItens(List<Item> itens) { this.itens = itens; }

    // Hooks JPA para datas automáticas
    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDateTime.now();
        this.dataAlteracao = LocalDateTime.now();
        if (this.data == null) {
            this.data = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAlteracao = LocalDateTime.now();
    }
}