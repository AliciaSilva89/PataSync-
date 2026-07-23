package br.com.patasync.db.dao;

import br.com.patasync.db.DatabaseConnection;
import br.com.patasync.models.Animal;
import br.com.patasync.models.Tutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Animal buscarPorNomeETutor(String nome, Tutor tutor) {
        String sql = "SELECT animal_id, nome, especie, raca, idade, peso " +
                     "FROM animal WHERE nome ILIKE ? AND tutor_id = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            stmt.setInt(2, 1); // tutor_id fixo por enquanto, precisa ser ajustado
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearAnimal(rs, tutor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Animal> buscarTodosPorTutor(int tutorId, Tutor tutor) {
        String sql = "SELECT animal_id, nome, especie, raca, idade, peso " +
                     "FROM animal WHERE tutor_id = ? ORDER BY nome";

        List<Animal> animais = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, tutorId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                animais.add(mapearAnimal(rs, tutor));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animais;
    }

    public int inserirAnimal(Animal animal, int tutorId) {
        String sql = "INSERT INTO animal (nome, especie, raca, idade, peso, tutor_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?) RETURNING animal_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setInt(4, animal.getIdade());
            stmt.setDouble(5, animal.getPeso());
            stmt.setInt(6, tutorId);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("animal_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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
