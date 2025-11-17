package com.esports.model;

/**
 * Match model class representing a competitive match between teams
 */
public class Match {
    private int matchId;
    private int tournamentId;
    private int team1Id;
    private int team2Id;
    private int team1Points;
    private int team2Points;
    private String winnerId;
    private String status; // scheduled, completed, cancelled
    private String matchDate;
    private String createdAt;
    private String updatedAt;

    // Constructors
    public Match() {}

    public Match(int tournamentId, int team1Id, int team2Id, String matchDate) {
        this.tournamentId = tournamentId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.matchDate = matchDate;
        this.status = "scheduled";
        this.team1Points = 0;
        this.team2Points = 0;
    }

    public Match(int matchId, int tournamentId, int team1Id, int team2Id, int team1Points, 
                 int team2Points, String winnerId, String status, String matchDate) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1Points = team1Points;
        this.team2Points = team2Points;
        this.winnerId = winnerId;
        this.status = status;
        this.matchDate = matchDate;
    }

    // Getters and Setters
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(int team1Id) {
        this.team1Id = team1Id;
    }

    public int getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(int team2Id) {
        this.team2Id = team2Id;
    }

    public int getTeam1Points() {
        return team1Points;
    }

    public void setTeam1Points(int team1Points) {
        this.team1Points = team1Points;
    }

    public int getTeam2Points() {
        return team2Points;
    }

    public void setTeam2Points(int team2Points) {
        this.team2Points = team2Points;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(String winnerId) {
        this.winnerId = winnerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", tournamentId=" + tournamentId +
                ", team1Id=" + team1Id +
                ", team2Id=" + team2Id +
                ", status='" + status + '\'' +
                '}';
    }
}
