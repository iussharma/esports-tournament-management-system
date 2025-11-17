package com.esports.model;

/**
 * Player model class representing an eSports player
 */
public class Player {
    private int playerId;
    private String playerName;
    private String role;
    private int teamId;
    private String skill;
    private int frags;
    private double kd;
    private String createdAt;
    private String updatedAt;

    // Constructors
    public Player() {}

    public Player(String playerName, String role, int teamId, String skill) {
        this.playerName = playerName;
        this.role = role;
        this.teamId = teamId;
        this.skill = skill;
        this.frags = 0;
        this.kd = 0.0;
    }

    public Player(int playerId, String playerName, String role, int teamId, String skill, int frags, double kd) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.role = role;
        this.teamId = teamId;
        this.skill = skill;
        this.frags = frags;
        this.kd = kd;
    }

    // Getters and Setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getFrags() {
        return frags;
    }

    public void setFrags(int frags) {
        this.frags = frags;
    }

    public double getKd() {
        return kd;
    }

    public void setKd(double kd) {
        this.kd = kd;
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
        return "Player{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", role='" + role + '\'' +
                ", teamId=" + teamId +
                ", skill='" + skill + '\'' +
                ", frags=" + frags +
                ", kd=" + kd +
                '}';
    }
}
