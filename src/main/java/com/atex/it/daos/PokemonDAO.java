package com.atex.it.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.atex.it.db.DatabaseConnection;
import com.atex.it.models.ElectricPokemon;
import com.atex.it.models.Pokemon;
import com.atex.it.models.WaterPokemon;

public class PokemonDAO {
    // Obtener todos los Pokémon
    public List<Pokemon> getAllPokemon() throws SQLException {
        List<Pokemon> pokemonList = new ArrayList<>();
        String query = "SELECT * FROM pokemon";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String imageUrl = rs.getString("image_url");
                String type = rs.getString("type");

                if ("Electric".equalsIgnoreCase(type)) {
                    pokemonList.add(getElectricPokemon(id, name, imageUrl));
                } else if ("Water".equalsIgnoreCase(type)) {
                    pokemonList.add(getWaterPokemon(id, name, imageUrl));
                }
            }
        }
        return pokemonList;
    }

    private ElectricPokemon getElectricPokemon(Long id, String name, String imageUrl) throws SQLException {
        String query = "SELECT voltage FROM electric_pokemon WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int voltage = rs.getInt("voltage");
                return new ElectricPokemon(id, name, imageUrl, voltage);
            }
        }
        return null;
    }

    private WaterPokemon getWaterPokemon(Long id, String name, String imageUrl) throws SQLException {
        String query = "SELECT swim_speed FROM water_pokemon WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int swimSpeed = rs.getInt("swim_speed");
                return new WaterPokemon(id, name, imageUrl, swimSpeed);
            }
        }
        return null;
    }

        // Crear un nuevo Pokémon
        public void createPokemon(Pokemon pokemon) throws SQLException {
            // Inserta en la tabla base `pokemon`
            String insertPokemonQuery = "INSERT INTO pokemon (name, image_url, type) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(insertPokemonQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
        
                // Inserción en la tabla `pokemon`
                pstmt.setString(1, pokemon.getName());
                pstmt.setString(2, pokemon.getImageUrl());
                pstmt.setString(3, pokemon.getType());
                pstmt.executeUpdate();
        
                // Obtiene el ID generado automáticamente
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long generatedId = generatedKeys.getLong(1);
        
                    // Inserta en la tabla específica según el tipo de Pokémon
                    if (pokemon instanceof ElectricPokemon) {
                        insertElectricPokemon((ElectricPokemon) pokemon, generatedId);
                    } else if (pokemon instanceof WaterPokemon) {
                        insertWaterPokemon((WaterPokemon) pokemon, generatedId);
                    }
                } else {
                    throw new SQLException("Failed to retrieve generated key for Pokémon");
                }
            }
        }
        
        private void insertElectricPokemon(ElectricPokemon pokemon, Long id) throws SQLException {
            String query = "INSERT INTO electric_pokemon (id, voltage) VALUES (?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
        
                pstmt.setLong(1, id);
                pstmt.setInt(2, pokemon.getVoltage());
                pstmt.executeUpdate();
            }
        }
        
        private void insertWaterPokemon(WaterPokemon pokemon, Long id) throws SQLException {
            String query = "INSERT INTO water_pokemon (id, swim_speed) VALUES (?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
        
                pstmt.setLong(1, id);
                pstmt.setInt(2, pokemon.getSwimSpeed());
                pstmt.executeUpdate();
            }
        }
        
    
        // Actualizar un Pokémon
        public void updatePokemon(Pokemon pokemon) throws SQLException {
            String updatePokemonQuery = "UPDATE pokemon SET name = ?, image_url = ?, type = ? WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(updatePokemonQuery)) {
    
                pstmt.setString(1, pokemon.getName());
                pstmt.setString(2, pokemon.getImageUrl());
                pstmt.setString(3, pokemon.getType());
                pstmt.setLong(4, pokemon.getId());
                pstmt.executeUpdate();
    
                // Actualizar información específica según el tipo
                if (pokemon instanceof ElectricPokemon) {
                    updateElectricPokemon((ElectricPokemon) pokemon);
                } else if (pokemon instanceof WaterPokemon) {
                    updateWaterPokemon((WaterPokemon) pokemon);
                }
            }
        }
    
        private void updateElectricPokemon(ElectricPokemon pokemon) throws SQLException {
            String query = "UPDATE electric_pokemon SET voltage = ? WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
    
                pstmt.setInt(1, pokemon.getVoltage());
                pstmt.setLong(2, pokemon.getId());
                pstmt.executeUpdate();
            }
        }
    
        private void updateWaterPokemon(WaterPokemon pokemon) throws SQLException {
            String query = "UPDATE water_pokemon SET swim_speed = ? WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
    
                pstmt.setInt(1, pokemon.getSwimSpeed());
                pstmt.setLong(2, pokemon.getId());
                pstmt.executeUpdate();
            }
        }
    
        public void deletePokemon(Long id) throws SQLException {
            // Conexión a la base de datos
            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.setAutoCommit(false); // Deshabilitar autocommit para manejar transacciones manualmente
        
                try {
                    // Eliminar de la tabla hija correspondiente según el tipo
                    deleteFromChildTables(conn, id);
        
                    // Eliminar de la tabla padre
                    String deletePokemonQuery = "DELETE FROM pokemon WHERE id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(deletePokemonQuery)) {
                        pstmt.setLong(1, id);
                        int rowsAffected = pstmt.executeUpdate();
        
                        if (rowsAffected == 0) {
                            throw new SQLException("No Pokémon found with ID: " + id);
                        }
                    }
        
                    conn.commit(); // Confirmar los cambios
                } catch (SQLException e) {
                    conn.rollback(); // Revertir los cambios en caso de error
                    throw new SQLException("Error deleting Pokémon with ID: " + id, e);
                }
            }
        }
        
        // Método auxiliar para eliminar registros relacionados en las tablas hijas
        private void deleteFromChildTables(Connection conn, Long id) throws SQLException {
            // Intenta eliminar de la tabla `electric_pokemon`
            String deleteElectricQuery = "DELETE FROM electric_pokemon WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteElectricQuery)) {
                pstmt.setLong(1, id);
                pstmt.executeUpdate();
            }
        
            // Intenta eliminar de la tabla `water_pokemon`
            String deleteWaterQuery = "DELETE FROM water_pokemon WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteWaterQuery)) {
                pstmt.setLong(1, id);
                pstmt.executeUpdate();
            }
        }
        
}
