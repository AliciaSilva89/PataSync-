package br.com.patasync.db.dao;

import br.com.patasync.db.DatabaseConnection;
import br.com.patasync.models.*;

import java.sql.*;

public class FuncionarioDAO {

    public Veterinario buscarVeterinarioPorId(int funcionarioId) {
        String sql = "SELECT p.pessoa_id, p.nome, p.cpf, p.telefone, p.email, " +
                     "p.logradouro, p.numero, p.complemento, p.cep, p.cidade, p.estado, " +
                     "p.profissao, p.data_nascimento, p.sexo, p.estado_civil, f.salario " +
                     "FROM pessoa p " +
                     "JOIN funcionario f ON p.pessoa_id = f.pessoa_id " +
                     "WHERE f.funcionario_id = ? AND f.tipo_funcionario = 'VETERINARIO'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, funcionarioId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearVeterinario(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Veterinario buscarPrimeiroVeterinario() {
        String sql = "SELECT p.pessoa_id, p.nome, p.cpf, p.telefone, p.email, " +
                     "p.logradouro, p.numero, p.complemento, p.cep, p.cidade, p.estado, " +
                     "p.profissao, p.data_nascimento, p.sexo, p.estado_civil, f.salario " +
                     "FROM pessoa p " +
                     "JOIN funcionario f ON p.pessoa_id = f.pessoa_id " +
                     "WHERE f.tipo_funcionario = 'VETERINARIO' " +
                     "ORDER BY f.funcionario_id " +
                     "LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return mapearVeterinario(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Atendente buscarAtendentePorId(int funcionarioId) {
        String sql = "SELECT p.pessoa_id, p.nome, p.cpf, p.telefone, p.email, " +
                     "p.logradouro, p.numero, p.complemento, p.cep, p.cidade, p.estado, " +
                     "p.profissao, p.data_nascimento, p.sexo, p.estado_civil, f.salario " +
                     "FROM pessoa p " +
                     "JOIN funcionario f ON p.pessoa_id = f.pessoa_id " +
                     "WHERE f.funcionario_id = ? AND f.tipo_funcionario = 'ATENDENTE'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, funcionarioId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearAtendente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Atendente buscarPrimeiroAtendente() {
        String sql = "SELECT p.pessoa_id, p.nome, p.cpf, p.telefone, p.email, " +
                     "p.logradouro, p.numero, p.complemento, p.cep, p.cidade, p.estado, " +
                     "p.profissao, p.data_nascimento, p.sexo, p.estado_civil, f.salario " +
                     "FROM pessoa p " +
                     "JOIN funcionario f ON p.pessoa_id = f.pessoa_id " +
                     "WHERE f.tipo_funcionario = 'ATENDENTE' " +
                     "ORDER BY f.funcionario_id " +
                     "LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return mapearAtendente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AssistenteVeterinario buscarAssistentePorId(int funcionarioId) {
        String sql = "SELECT p.pessoa_id, p.nome, p.cpf, p.telefone, p.email, " +
                     "p.logradouro, p.numero, p.complemento, p.cep, p.cidade, p.estado, " +
                     "p.profissao, p.data_nascimento, p.sexo, p.estado_civil, f.salario " +
                     "FROM pessoa p " +
                     "JOIN funcionario f ON p.pessoa_id = f.pessoa_id " +
                     "WHERE f.funcionario_id = ? AND f.tipo_funcionario = 'ASSISTENTE'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, funcionarioId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearAssistente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AssistenteVeterinario buscarPrimeiroAssistente() {
        String sql = "SELECT p.pessoa_id, p.nome, p.cpf, p.telefone, p.email, " +
                     "p.logradouro, p.numero, p.complemento, p.cep, p.cidade, p.estado, " +
                     "p.profissao, p.data_nascimento, p.sexo, p.estado_civil, f.salario " +
                     "FROM pessoa p " +
                     "JOIN funcionario f ON p.pessoa_id = f.pessoa_id " +
                     "WHERE f.tipo_funcionario = 'ASSISTENTE' " +
                     "ORDER BY f.funcionario_id " +
                     "LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return mapearAssistente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Veterinario mapearVeterinario(ResultSet rs) throws SQLException {
        return new Veterinario(
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
            rs.getString("estado_civil"),
            rs.getDouble("salario")
        );
    }

    private Atendente mapearAtendente(ResultSet rs) throws SQLException {
        return new Atendente(
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
            rs.getString("estado_civil"),
            rs.getDouble("salario")
        );
    }

    private AssistenteVeterinario mapearAssistente(ResultSet rs) throws SQLException {
        return new AssistenteVeterinario(
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
            rs.getString("estado_civil"),
            rs.getDouble("salario")
        );
    }

    public int inserirFuncionario(int pessoaId, double salario, String tipoFuncionario) {
        String sql = "INSERT INTO funcionario (pessoa_id, salario, tipo_funcionario) " +
                     "VALUES (?, ?, ?) RETURNING funcionario_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, pessoaId);
            stmt.setDouble(2, salario);
            stmt.setString(3, tipoFuncionario);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("funcionario_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Atendente buscarAtendentePorCPF(String cpf) {
        String sql = "SELECT p.pessoa_id, p.nome, p.cpf, p.telefone, p.email, " +
                     "p.logradouro, p.numero, p.complemento, p.cep, p.cidade, p.estado, " +
                     "p.profissao, p.data_nascimento, p.sexo, p.estado_civil, f.salario " +
                     "FROM pessoa p " +
                     "JOIN funcionario f ON p.pessoa_id = f.pessoa_id " +
                     "WHERE p.cpf = ? AND f.tipo_funcionario = 'ATENDENTE'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearAtendente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
