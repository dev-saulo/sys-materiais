package com.keeper.sys_materiais.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "UsuarioUSU")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_COD_UsuarioUSU")
    private Long id;

    @Column(name = "TXT_NomeUSU", nullable = false, length = 100)
    private String nome;

    @Column(name = "TXT_MatriculaUSU", nullable = false, length = 4, unique = true)
    private String matricula;

    @Column(name = "COD_StatusUSU")
    private Boolean ativo;

    @Column(name = "DAT_CadastroUSU", updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "DAT_AlteracaoUSU")
    private LocalDateTime dataAlteracao;

    // Construtores
    public Usuario() {}

    public Usuario(String nome, String matricula, Boolean ativo) {
        this.nome = nome;
        this.matricula = matricula;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

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
