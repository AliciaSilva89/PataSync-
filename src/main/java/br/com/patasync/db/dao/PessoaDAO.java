package br.com.patasync.db.dao;

import br.com.patasync.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaDAO {

    public int inserirPessoa(String nome, String cpf, String telefone, String email,
                             String logradouro, String numero, String complemento,
                             String cep, String cidade, String estado, String profissao,
                             String dataNascimento, String sexo, String estadoCivil) {
        String sql = "INSERT INTO pessoa (nome, cpf, telefone, email, logradouro, numero, " +
                     "complemento, cep, cidade, estado, profissao, data_nascimento, sexo, estado_civil) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING pessoa_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setString(5, logradouro);
            stmt.setString(6, numero);
            stmt.setString(7, complemento);
            stmt.setString(8, cep);
            stmt.setString(9, cidade);
            stmt.setString(10, estado);
            stmt.setString(11, profissao);
            stmt.setString(12, dataNascimento);
            stmt.setString(13, sexo);
            stmt.setString(14, estadoCivil);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("pessoa_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int buscarPessoaIdPorCPF(String cpf) {
        String sql = "SELECT pessoa_id FROM pessoa WHERE cpf = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("pessoa_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
