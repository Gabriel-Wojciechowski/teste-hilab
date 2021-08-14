package br.hilab.dao.utils.start_env.data;

import br.hilab.dao.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;

public class CreateCategoria {

    private static Statement query = null;
    private static Connection con = null;

    public static void main(String[] args) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            con = factory.getConnection();
            query = con.createStatement();

            query.executeUpdate("INSERT INTO categoria(descricao) VALUES "
                    + "('Desenvolvedor'),"
                    + "('BDA'),"
                    + "('Gerente de Sistemas'),"
                    + "('Arquiteto de Software');");

            System.out.println("Categorias criadas com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar ao criar categoria.");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                    con = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (query != null) {
                try {
                    query.close();
                    query = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
