package repository;

import model.Solicitacao;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class SolicitacaoRepositoryImpl implements SolicitacaoRepository {

    private List<Solicitacao> solicitacoes = new ArrayList<>();
    private Long currentId = 1L;

    @Override
    public List<Solicitacao> findAll() {
        return new ArrayList<>(solicitacoes);
    }

    @Override
    public Solicitacao save(Solicitacao solicitacao) {
        if (solicitacao.getId() == null) {
            solicitacao.setId(currentId++);
            solicitacoes.add(solicitacao);
        } else {
            // Atualizar solicitação existente
            for (int i = 0; i < solicitacoes.size(); i++) {
                if (solicitacoes.get(i).getId().equals(solicitacao.getId())) {
                    solicitacoes.set(i, solicitacao);
                    break;
                }
            }
        }
        return solicitacao;
    }

    @Override
    public void deleteById(Long id) {
        Iterator<Solicitacao> iterator = solicitacoes.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }
}
