package br.com.patasync.db.dao;

import br.com.patasync.db.DatabaseConnection;
import br.com.patasync.models.Tutor;

import java.sql.*;

public class TutorDAO {

    public Tutor buscarPorId(int tutorId) {
        String sql = "SELECT p.pessoa_id, p.nome, p.cpf, p.telefone, p.email, " +
                     "p.logradouro, p.numero, p.complemento, p.cep, p.cidade, p.estado, " +
                     "p.profissao, p.data_nascimento, p.sexo, p.estado_civil " +
                     "FROM pessoa p " +
                     "JOIN tutor t ON p.pessoa_id = t.pessoa_id " +
                     "WHERE t.tutor_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, tutorId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearTutor(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Tutor buscarPrimeiro() {
        String sql = "SELECT p.pessoa_id, p.nome, p.cpf, p.telefone, p.email, " +
                     "p.logradouro, p.numero, p.complemento, p.cep, p.cidade, p.estado, " +
                     "p.profissao, p.data_nascimento, p.sexo, p.estado_civil " +
                     "FROM pessoa p " +
                     "JOIN tutor t ON p.pessoa_id = t.pessoa_id " +
                     "ORDER BY t.tutor_id " +
                     "LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return mapearTutor(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Tutor buscarPorCPF(String cpf) {
        String sql = "SELECT p.pessoa_id, p.nome, p.cpf, p.telefone, p.email, " +
                     "p.logradouro, p.numero, p.complemento, p.cep, p.cidade, p.estado, " +
                     "p.profissao, p.data_nascimento, p.sexo, p.estado_civil, t.tutor_id " +
                     "FROM pessoa p " +
                     "JOIN tutor t ON p.pessoa_id = t.pessoa_id " +
                     "WHERE p.cpf = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Tutor tutor = mapearTutor(rs);
                tutor.setTutorId(rs.getInt("tutor_id"));
                return tutor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int buscarTutorIdPorCPF(String cpf) {
        String sql = "SELECT t.tutor_id " +
                     "FROM pessoa p " +
                     "JOIN tutor t ON p.pessoa_id = t.pessoa_id " +
                     "WHERE p.cpf = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("tutor_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private Tutor mapearTutor(ResultSet rs) throws SQLException {
        return new Tutor(
            rs.getString("nome"),
            rs.getString("cpf"),
            rs.getString("telefone"),
            rs.getString("email"),
            rs.getString("logradouro"),
            rs.getString("numero"),
            rs.getString("complemento"),
            rs.getString("cep"),
            rs.getString("cidade"),
            rs.getString("estado"),
            rs.getString("profissao"),
            rs.getDate("data_nascimento").toLocalDate(),
            rs.getString("sexo"),
            rs.getString("estado_civil")
        );
    }
}
