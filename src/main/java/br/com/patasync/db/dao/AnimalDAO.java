package br.com.patasync.db.dao;

import br.com.patasync.db.DatabaseConnection;
import br.com.patasync.models.Animal;
import br.com.patasync.models.Tutor;

import java.sql.*;

public class AnimalDAO {

    public Animal buscarPorId(int animalId, Tutor tutor) {
        String sql = "SELECT animal_id, nome, especie, raca, idade, peso " +
                     "FROM animal WHERE animal_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, animalId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearAnimal(rs, tutor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Animal buscarPrimeiroPorTutor(int tutorId, Tutor tutor) {
        String sql = "SELECT animal_id, nome, especie, raca, idade, peso " +
                     "FROM animal WHERE tutor_id = ? ORDER BY animal_id LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, tutorId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearAnimal(rs, tutor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Animal mapearAnimal(ResultSet rs, Tutor tutor) throws SQLException {
        return new Animal(
            rs.getString("nome"),
            rs.getString("especie"),
            rs.getString("raca"),
            rs.getInt("idade"),
            rs.getDouble("peso"),
            tutor
        );
    }
}
