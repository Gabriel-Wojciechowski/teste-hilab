
package br.hilab.business;


import br.hilab.domain.Usuario;
import br.hilab.dao.UsuarioDAO;
import br.hilab.dao.utils.ConnectionFactory;
import java.util.List;

public class UsuarioFacade {

    public static Usuario buscar(Usuario usuario) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(factory.getConnection());

            usuario = dao.buscar(usuario);
            if (usuario == null) {
                throw new RuntimeException();
            }

            return usuario;
        }
    }

    public static List<Usuario> buscarTodos() {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(factory.getConnection());
            List<Usuario> usuarios = dao.buscarTodos();
            return usuarios;
        }
    }

    public static void inserir(Usuario usuario) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(factory.getConnection());
            dao.inserir(usuario);
        }
    }
    
    public static void editar(Usuario usuario) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(factory.getConnection());
            dao.editar(usuario);
        }
    }
    
    public static void remover(Usuario usuario) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UsuarioDAO dao = new UsuarioDAO(factory.getConnection());

            usuario = dao.buscar(usuario);
            if (usuario == null) {
                throw new RuntimeException();
            }
            dao.remover(usuario);
        }
    }

}
