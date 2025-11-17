package com.esports.dao;

import com.esports.model.Tournament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Tournament operations
 */
public class TournamentDAO {
    /**
     * Get all tournaments
     */
    public static List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = new ArrayList<>();
        String sql = "SELECT * FROM tournaments ORDER BY start_date DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                tournaments.add(mapResultSetToTournament(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournaments;
    }

    /**
     * Get tournament by ID
     */
    public static Tournament getTournamentById(int tournamentId) {
        String sql = "SELECT * FROM tournaments WHERE tournament_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tournamentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTournament(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create new tournament
     */
    public static boolean createTournament(Tournament tournament) {
        String sql = "INSERT INTO tournaments (tournament_name, description, start_date, end_date, prize_pool, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tournament.getTournamentName());
            pstmt.setString(2, tournament.getDescription());
            pstmt.setString(3, tournament.getStartDate());
            pstmt.setString(4, tournament.getEndDate());
            pstmt.setString(5, tournament.getPrizePool());
            pstmt.setString(6, tournament.getStatus());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update tournament
     */
    public static boolean updateTournament(Tournament tournament) {
        String sql = "UPDATE tournaments SET tournament_name = ?, description = ?, start_date = ?, end_date = ?, prize_pool = ?, status = ? WHERE tournament_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tournament.getTournamentName());
            pstmt.setString(2, tournament.getDescription());
            pstmt.setString(3, tournament.getStartDate());
            pstmt.setString(4, tournament.getEndDate());
            pstmt.setString(5, tournament.getPrizePool());
            pstmt.setString(6, tournament.getStatus());
            pstmt.setInt(7, tournament.getTournamentId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete tournament
     */
    public static boolean deleteTournament(int tournamentId) {
        String sql = "DELETE FROM tournaments WHERE tournament_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tournamentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get tournament count
     */
    public static int getCount() {
        String sql = "SELECT COUNT(*) AS c FROM tournaments";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) return rs.getInt("c");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Helper method to map ResultSet to Tournament object
     */
    private static Tournament mapResultSetToTournament(ResultSet rs) throws SQLException {
        Tournament tournament = new Tournament();
        tournament.setTournamentId(rs.getInt("tournament_id"));
        tournament.setTournamentName(rs.getString("tournament_name"));
        tournament.setDescription(rs.getString("description"));
        tournament.setStartDate(rs.getString("start_date"));
        tournament.setEndDate(rs.getString("end_date"));
        tournament.setPrizePool(rs.getString("prize_pool"));
        tournament.setStatus(rs.getString("status"));
        return tournament;
    }
}
