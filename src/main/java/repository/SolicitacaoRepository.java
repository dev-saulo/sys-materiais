package repository;

import com.keeper.sys_materiais.model.Solicitacao;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolicitacaoRepository {
    List<Solicitacao> findAll();
    Solicitacao save(Solicitacao solicitacao);
    void deleteById(Long id);
}
