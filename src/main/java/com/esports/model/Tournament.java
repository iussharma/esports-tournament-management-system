package com.esports.model;

/**
 * Tournament model class representing an eSports tournament
 */
public class Tournament {
    private int tournamentId;
    private String tournamentName;
    private String description;
    private String startDate;
    private String endDate;
    private String prizePool;
    private String status; // upcoming, ongoing, completed
    private String createdAt;
    private String updatedAt;

    // Constructors
    public Tournament() {}

    public Tournament(String tournamentName, String description, String startDate, String endDate, String prizePool) {
        this.tournamentName = tournamentName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prizePool = prizePool;
        this.status = "upcoming";
    }

    public Tournament(int tournamentId, String tournamentName, String description, String startDate, 
                     String endDate, String prizePool, String status) {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prizePool = prizePool;
        this.status = status;
    }

    // Getters and Setters
    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(String prizePool) {
        this.prizePool = prizePool;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Tournament{" +
                "tournamentId=" + tournamentId +
                ", tournamentName='" + tournamentName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
