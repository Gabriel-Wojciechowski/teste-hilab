package br.hilab.dao.utils.start_env.data;

import br.hilab.dao.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {

    private static Statement query = null;
    private static Connection con = null;

    public static void main(String[] args) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            con = factory.getConnection();
            query = con.createStatement();

            //Drop and recreate schema
            query.executeUpdate("DROP SCHEMA IF EXISTS public CASCADE;");
            query.executeUpdate("CREATE SCHEMA public;");

            //Giving permissions
            query.executeUpdate("GRANT ALL ON SCHEMA public TO postgres;");
            query.executeUpdate("GRANT ALL ON SCHEMA public TO public;");
            
            //Creating New Tables
            query.executeUpdate("CREATE TABLE categoria (" +
                                " id SERIAL UNIQUE PRIMARY KEY, " +
                                " descricao varchar(255)); ");
            query.executeUpdate("CREATE TABLE usuario (" +
                                " id SERIAL UNIQUE PRIMARY KEY, " +
                                " nome varchar(255), " +
                                " data_nasc date, " +
                                " email varchar(255), " +
                                " id_categoria int, " +
                                " FOREIGN KEY (id_categoria) REFERENCES categoria(id)); ");
            System.out.println("Tabelas criadas com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar ao criar tabelas.");
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
