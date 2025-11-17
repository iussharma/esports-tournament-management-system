package com.esports.model;

/**
 * Team model class representing an eSports team
 */
public class Team {
    private int teamId;
    private String teamName;
    private String description;
    private int totalPoints;
    private int wins;
    private int losses;
    private String createdAt;
    private String updatedAt;

    // Constructors
    public Team() {}

    public Team(String teamName, String description) {
        this.teamName = teamName;
        this.description = description;
        this.totalPoints = 0;
        this.wins = 0;
        this.losses = 0;
    }

    public Team(int teamId, String teamName, String description, int totalPoints, int wins, int losses) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.description = description;
        this.totalPoints = totalPoints;
        this.wins = wins;
        this.losses = losses;
    }

    // Getters and Setters
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
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
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", totalPoints=" + totalPoints +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}
