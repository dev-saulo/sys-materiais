package repository;

import com.keeper.sys_materiais.model.Usuario;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public Usuario save(Usuario usuario) {
        // Verificar se é um update ou insert
        Usuario existente = findByMatricula(usuario.getMatricula());
        if (existente != null) {
            // Atualizar usuário existente
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getMatricula().equals(usuario.getMatricula())) {
                    usuarios.set(i, usuario);
                    break;
                }
            }
        } else {
            // Adicionar novo usuário
            usuarios.add(usuario);
        }
        return usuario;
    }

    @Override
    public Usuario findByMatricula(String matricula) {
        return usuarios.stream()
                .filter(usuario -> usuario.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);
    }
}
