package com.esports.dao;

import com.esports.model.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Player operations
 */
public class PlayerDAO {
    /**
     * Get all players
     */
    public static List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Player player = new Player();
                player.setPlayerId(rs.getInt("player_id"));
                player.setPlayerName(rs.getString("player_name"));
                player.setRole(rs.getString("role"));
                player.setTeamId(rs.getInt("team_id"));
                player.setSkill(rs.getString("skill"));
                player.setFrags(rs.getInt("frags"));
                player.setKd(rs.getDouble("kd"));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * Get players by team ID
     */
    public static List<Player> getPlayersByTeamId(int teamId) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players WHERE team_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Player player = new Player();
                    player.setPlayerId(rs.getInt("player_id"));
                    player.setPlayerName(rs.getString("player_name"));
                    player.setRole(rs.getString("role"));
                    player.setTeamId(rs.getInt("team_id"));
                    player.setSkill(rs.getString("skill"));
                    player.setFrags(rs.getInt("frags"));
                    player.setKd(rs.getDouble("kd"));
                    players.add(player);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * Get player by ID
     */
    public static Player getPlayerById(int playerId) {
        String sql = "SELECT * FROM players WHERE player_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Player player = new Player();
                    player.setPlayerId(rs.getInt("player_id"));
                    player.setPlayerName(rs.getString("player_name"));
                    player.setRole(rs.getString("role"));
                    player.setTeamId(rs.getInt("team_id"));
                    player.setSkill(rs.getString("skill"));
                    player.setFrags(rs.getInt("frags"));
                    player.setKd(rs.getDouble("kd"));
                    return player;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create new player
     */
    public static boolean createPlayer(Player player) {
        String sql = "INSERT INTO players (player_name, role, team_id, skill, frags, kd) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getPlayerName());
            pstmt.setString(2, player.getRole());
            pstmt.setInt(3, player.getTeamId());
            pstmt.setString(4, player.getSkill());
            pstmt.setInt(5, player.getFrags());
            pstmt.setDouble(6, player.getKd());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update player
     */
    public static boolean updatePlayer(Player player) {
        String sql = "UPDATE players SET player_name = ?, role = ?, team_id = ?, skill = ?, frags = ?, kd = ? WHERE player_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player.getPlayerName());
            pstmt.setString(2, player.getRole());
            pstmt.setInt(3, player.getTeamId());
            pstmt.setString(4, player.getSkill());
            pstmt.setInt(5, player.getFrags());
            pstmt.setDouble(6, player.getKd());
            pstmt.setInt(7, player.getPlayerId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete player
     */
    public static boolean deletePlayer(int playerId) {
        String sql = "DELETE FROM players WHERE player_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, playerId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
