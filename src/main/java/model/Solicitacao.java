package model;

import java.time.LocalDateTime;
import java.util.List;

public class Solicitacao {

    private Long id;
    private String solicitante;
    private LocalDateTime data;
    private List<Long> itensIds;

    public Solicitacao() {}

    public Solicitacao(Long id, String solicitante, LocalDateTime data, List<Long> itensIds) {
        this.id = id;
        this.solicitante = solicitante;
        this.data = data;
        this.itensIds = itensIds;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSolicitante() { return solicitante; }
    public void setSolicitante(String solicitante) { this.solicitante = solicitante; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public List<Long> getItensIds() { return itensIds; }
    public void setItensIds(List<Long> itensIds) { this.itensIds = itensIds; }
}