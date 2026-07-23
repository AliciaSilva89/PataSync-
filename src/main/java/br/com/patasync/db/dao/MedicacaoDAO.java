package br.com.patasync.db.dao;

import br.com.patasync.db.DatabaseConnection;
import br.com.patasync.models.Medicacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicacaoDAO {

    public List<Medicacao> buscarTodas() {
        String sql = "SELECT medicamento_id, nome, principio_ativo, classe_indicacao, " +
                     "dosagem_media, categoria_clinica, valor_unitario, observacoes " +
                     "FROM medicamentos ORDER BY nome";

        List<Medicacao> medicamentos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                medicamentos.add(mapearMedicacao(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamentos;
    }

    public Medicacao buscarPorId(int medicamentoId) {
        String sql = "SELECT medicamento_id, nome, principio_ativo, classe_indicacao, " +
                     "dosagem_media, categoria_clinica, valor_unitario, observacoes " +
                     "FROM medicamentos WHERE medicamento_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, medicamentoId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearMedicacao(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int inserirMedicacao(Medicacao medicacao) {
        String sql = "INSERT INTO medicamentos (nome, principio_ativo, classe_indicacao, " +
                     "dosagem_media, categoria_clinica, valor_unitario, observacoes) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING medicamento_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, medicacao.getNome());
            stmt.setString(2, medicacao.getPrincipioAtivo());
            stmt.setString(3, medicacao.getClasseIndicacao());
            stmt.setString(4, medicacao.getDosagem());
            stmt.setString(5, medicacao.getCategoriaClinica());
            stmt.setDouble(6, medicacao.getValorUnitario());
            stmt.setString(7, medicacao.getObservacoes());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("medicamento_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private Medicacao mapearMedicacao(ResultSet rs) throws SQLException {
        Medicacao medicacao = new Medicacao(
            rs.getString("nome"),
            rs.getString("dosagem_media"),
            1, // quantidade padrão, será ajustada na seleção
            rs.getDouble("valor_unitario")
        );
        
        medicacao.setPrincipioAtivo(rs.getString("principio_ativo"));
        medicacao.setClasseIndicacao(rs.getString("classe_indicacao"));
        medicacao.setCategoriaClinica(rs.getString("categoria_clinica"));
        medicacao.setObservacoes(rs.getString("observacoes"));
        
        return medicacao;
    }
}
