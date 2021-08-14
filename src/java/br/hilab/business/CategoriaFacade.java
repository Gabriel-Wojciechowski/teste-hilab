
package br.hilab.business;

import br.hilab.domain.Categoria;
import br.hilab.dao.CategoriaDAO;
import br.hilab.dao.utils.ConnectionFactory;
import java.util.List;

public class CategoriaFacade {

    public static Categoria buscar(Categoria categoria) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            CategoriaDAO dao = new CategoriaDAO(factory.getConnection());

            categoria = dao.buscar(categoria);
            if (categoria == null) {
                throw new RuntimeException();
            }

            return categoria;
        }
    }

    public static List<Categoria> buscarTodos() {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            CategoriaDAO dao = new CategoriaDAO(factory.getConnection());
            List<Categoria> categorias = dao.buscarTodos();
            return categorias;
        }
    }

}
