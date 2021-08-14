package br.hilab.dao;

import br.hilab.domain.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private static final String QUERY_BUSCAR = "SELECT descricao FROM categoria WHERE id = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT id, descricao FROM categoria;";

    private Connection con = null;

    public CategoriaDAO(Connection con) {
        if (con == null) {
            throw new RuntimeException("Conexão nula ao criar CategoriaDAO.");
        }
        this.con = con;
    }

    public Categoria buscar(Categoria categoria) {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, categoria.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                categoria.setDescricao(rs.getString("descricao"));
                return categoria;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando categoria: " + categoria.getId(), e);
        }
    }

    public List<Categoria> buscarTodos() {
        List<Categoria> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
                lista.add(categoria);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando todas os categoria: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }
}
