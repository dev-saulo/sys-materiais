package repository;

import com.keeper.sys_materiais.model.Usuario;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository {
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    Usuario findByMatricula(String matricula);
}
