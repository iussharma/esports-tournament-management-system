package com.esports.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.esports.model.Match;

/**
 * DAO class for Match operations
 */
public class MatchDAO {
    /**
     * Get all matches
     */
    public static List<Match> getAllMatches() {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * FROM matches";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Match match = mapResultSetToMatch(rs);
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    /**
     * Get matches by tournament ID
     */
    public static List<Match> getMatchesByTournamentId(int tournamentId) {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * FROM matches WHERE tournament_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tournamentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Match match = mapResultSetToMatch(rs);
                    matches.add(match);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    /**
     * Get match by ID
     */
    public static Match getMatchById(int matchId) {
        String sql = "SELECT * FROM matches WHERE match_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matchId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMatch(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create new match
     */
    public static boolean createMatch(Match match) {
        String sql = "INSERT INTO matches (tournament_id, team1_id, team2_id, team1_points, team2_points, winner_id, status, match_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, match.getTournamentId());
            pstmt.setInt(2, match.getTeam1Id());
            pstmt.setInt(3, match.getTeam2Id());
            pstmt.setInt(4, match.getTeam1Points());
            pstmt.setInt(5, match.getTeam2Points());
            pstmt.setString(6, match.getWinnerId());
            pstmt.setString(7, match.getStatus());
            pstmt.setString(8, match.getMatchDate());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update match
     */
    public static boolean updateMatch(Match match) {
        String sql = "UPDATE matches SET tournament_id = ?, team1_id = ?, team2_id = ?, team1_points = ?, team2_points = ?, winner_id = ?, status = ?, match_date = ? WHERE match_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, match.getTournamentId());
            pstmt.setInt(2, match.getTeam1Id());
            pstmt.setInt(3, match.getTeam2Id());
            pstmt.setInt(4, match.getTeam1Points());
            pstmt.setInt(5, match.getTeam2Points());
            pstmt.setString(6, match.getWinnerId());
            pstmt.setString(7, match.getStatus());
            pstmt.setString(8, match.getMatchDate());
            pstmt.setInt(9, match.getMatchId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete match
     */
    public static boolean deleteMatch(int matchId) {
        String sql = "DELETE FROM matches WHERE match_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matchId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get matches for a team
     */
    public static List<Match> getMatchesByTeamId(int teamId) {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * FROM matches WHERE team1_id = ? OR team2_id = ? ORDER BY match_date DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            pstmt.setInt(2, teamId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    matches.add(mapResultSetToMatch(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    /**
     * Helper method to map ResultSet to Match object
     */
    private static Match mapResultSetToMatch(ResultSet rs) throws SQLException {
        Match match = new Match();
        match.setMatchId(rs.getInt("match_id"));
        match.setTournamentId(rs.getInt("tournament_id"));
        match.setTeam1Id(rs.getInt("team1_id"));
        match.setTeam2Id(rs.getInt("team2_id"));
        match.setTeam1Points(rs.getInt("team1_points"));
        match.setTeam2Points(rs.getInt("team2_points"));
        match.setWinnerId(rs.getString("winner_id"));
        match.setStatus(rs.getString("status"));
        match.setMatchDate(rs.getString("match_date"));
        return match;
    }
}
