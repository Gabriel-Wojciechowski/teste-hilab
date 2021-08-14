package br.hilab.dao;

import br.hilab.domain.Categoria;
import br.hilab.domain.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String QUERY_BUSCAR = "SELECT nome, data_nasc, email, id_categoria, descricao FROM usuario u INNER JOIN categoria c ON u.id_categoria = c.id WHERE u.id = ?;";
    private static final String QUERY_BUSCAR_TODOS = "SELECT u.id id_usuario, nome, data_nasc, email, id_categoria, descricao FROM usuario u INNER JOIN categoria c ON u.id_categoria = c.id ORDER BY u.id ASC;";
    private static final String QUERY_INSERIR = "INSERT INTO usuario(nome, data_nasc, email, id_categoria) VALUES (?, ?, ?, ?);";
    private static final String QUERY_REMOVER = "DELETE FROM usuario WHERE id = ?;";
    private static final String QUERY_EDITAR = "UPDATE usuario SET nome = ?, data_nasc = ?, email = ?, id_categoria = ? WHERE id = ?;";

    private Connection con = null;

    public UsuarioDAO(Connection con) {
        if (con == null) {
            throw new RuntimeException("Conexão nula ao criar UsuarioDAO.");
        }
        this.con = con;
    }

    public Usuario buscar(Usuario usuario) {
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setInt(1, usuario.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Categoria categoria = new Categoria();
                
                usuario.setNome(rs.getString("nome"));
                Date date = rs.getDate("data_nasc");
                if(date != null) usuario.setDataNasc(date.toLocalDate());
                usuario.setEmail(rs.getString("email"));
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setDescricao(rs.getString("descricao"));
                usuario.setCategoria(categoria);
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando usuario: " + usuario.getId(), e);
        }
    }

    public List<Usuario> buscarTodos() {
        List<Usuario> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                Categoria categoria = new Categoria();
                
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                Date date = rs.getDate("data_nasc");
                if(date != null) usuario.setDataNasc(date.toLocalDate());
                usuario.setEmail(rs.getString("email"));
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setDescricao(rs.getString("descricao"));
                usuario.setCategoria(categoria);
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro buscando todos os usuários: "
                    + QUERY_BUSCAR_TODOS, e);

        }
    }

    public void inserir(Usuario usuario) {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, usuario.getNome());
            st.setDate(2, Date.valueOf(usuario.getDataNasc()));
            st.setString(3, usuario.getEmail());
            st.setInt(4, usuario.getCategoria().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar usuario: "
                    + QUERY_INSERIR, e);
        }
    }

    public void remover(Usuario usuario) {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, usuario.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar usuario: "
                    + QUERY_REMOVER, e);
        }
    }

    public void editar(Usuario usuario) {
        try (PreparedStatement st = con.prepareStatement(QUERY_EDITAR)) {
            st.setString(1, usuario.getNome());
            st.setDate(2, Date.valueOf(usuario.getDataNasc()));
            st.setString(3, usuario.getEmail());
            st.setInt(4, usuario.getCategoria().getId());
            st.setInt(5, usuario.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao editar usuario: "
                    + QUERY_EDITAR, e);
        }
    }
}
