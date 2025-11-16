package com.keeper.sys_materiais.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tb_usuarios")
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

    @Column(name = "DAT_CadastroUSU", updatable = false, insertable = false, columnDefinition = "datetime DEFAULT current_timestamp()")
    private LocalDateTime dataCadastro;

    @Column(name = "DAT_AlteracaoUSU", insertable = false, columnDefinition = "datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()")
    private LocalDateTime dataAlteracao;

    public Usuario() {}

    public Usuario(String nome, String matricula, Boolean ativo) {
        this.nome = nome;
        this.matricula = matricula;
        this.ativo = ativo;
    }

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
}
