package com.esports.dao;

import com.esports.model.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for Team operations
 */
public class TeamDAO {
    /**
     * Get all teams
     */
    public static List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM teams ORDER BY total_points DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Team team = new Team();
                team.setTeamId(rs.getInt("team_id"));
                team.setTeamName(rs.getString("team_name"));
                team.setDescription(rs.getString("description"));
                team.setTotalPoints(rs.getInt("total_points"));
                team.setWins(rs.getInt("wins"));
                team.setLosses(rs.getInt("losses"));
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    /**
     * Get team by ID
     */
    public static Team getTeamById(int teamId) {
        String sql = "SELECT * FROM teams WHERE team_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Team team = new Team();
                    team.setTeamId(rs.getInt("team_id"));
                    team.setTeamName(rs.getString("team_name"));
                    team.setDescription(rs.getString("description"));
                    team.setTotalPoints(rs.getInt("total_points"));
                    team.setWins(rs.getInt("wins"));
                    team.setLosses(rs.getInt("losses"));
                    return team;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create new team
     */
    public static boolean createTeam(Team team) {
        String sql = "INSERT INTO teams (team_name, description, total_points, wins, losses) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, team.getTeamName());
            pstmt.setString(2, team.getDescription());
            pstmt.setInt(3, team.getTotalPoints());
            pstmt.setInt(4, team.getWins());
            pstmt.setInt(5, team.getLosses());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Update team
     */
    public static boolean updateTeam(Team team) {
        String sql = "UPDATE teams SET team_name = ?, description = ?, total_points = ?, wins = ?, losses = ? WHERE team_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, team.getTeamName());
            pstmt.setString(2, team.getDescription());
            pstmt.setInt(3, team.getTotalPoints());
            pstmt.setInt(4, team.getWins());
            pstmt.setInt(5, team.getLosses());
            pstmt.setInt(6, team.getTeamId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete team
     */
    public static boolean deleteTeam(int teamId) {
        String sql = "DELETE FROM teams WHERE team_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get top teams by points
     */
    public static List<Team> getTopTeams(int limit) {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM teams ORDER BY total_points DESC LIMIT ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Team team = new Team();
                    team.setTeamId(rs.getInt("team_id"));
                    team.setTeamName(rs.getString("team_name"));
                    team.setDescription(rs.getString("description"));
                    team.setTotalPoints(rs.getInt("total_points"));
                    team.setWins(rs.getInt("wins"));
                    team.setLosses(rs.getInt("losses"));
                    teams.add(team);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }
}
