-- eSports Team Management System Database Script
-- Run this SQL script to create and populate the database

-- Create Database
CREATE DATABASE IF NOT EXISTS esports_db;
USE esports_db;

-- Table: admins
CREATE TABLE IF NOT EXISTS admins (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table: teams
CREATE TABLE IF NOT EXISTS teams (
    team_id INT PRIMARY KEY AUTO_INCREMENT,
    team_name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    total_points INT DEFAULT 0,
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table: players
CREATE TABLE IF NOT EXISTS players (
    player_id INT PRIMARY KEY AUTO_INCREMENT,
    player_name VARCHAR(100) NOT NULL,
    role VARCHAR(50),
    team_id INT NOT NULL,
    skill VARCHAR(50),
    frags INT DEFAULT 0,
    kd DECIMAL(5, 2) DEFAULT 0.0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE CASCADE
);

-- Table: tournaments
CREATE TABLE IF NOT EXISTS tournaments (
    tournament_id INT PRIMARY KEY AUTO_INCREMENT,
    tournament_name VARCHAR(100) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    prize_pool VARCHAR(100),
    status VARCHAR(50) DEFAULT 'upcoming',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table: matches
CREATE TABLE IF NOT EXISTS matches (
    match_id INT PRIMARY KEY AUTO_INCREMENT,
    tournament_id INT NOT NULL,
    team1_id INT NOT NULL,
    team2_id INT NOT NULL,
    team1_points INT DEFAULT 0,
    team2_points INT DEFAULT 0,
    winner_id VARCHAR(50),
    status VARCHAR(50) DEFAULT 'scheduled',
    match_date DATETIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id) ON DELETE CASCADE,
    FOREIGN KEY (team1_id) REFERENCES teams(team_id) ON DELETE CASCADE,
    FOREIGN KEY (team2_id) REFERENCES teams(team_id) ON DELETE CASCADE
);

-- Table: tournament_teams
CREATE TABLE IF NOT EXISTS tournament_teams (
    tournament_team_id INT PRIMARY KEY AUTO_INCREMENT,
    tournament_id INT NOT NULL,
    team_id INT NOT NULL,
    joined_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tournament_id) REFERENCES tournaments(tournament_id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES teams(team_id) ON DELETE CASCADE,
    UNIQUE KEY unique_tournament_team (tournament_id, team_id)
);

-- Insert Default Admin
INSERT INTO admins (username, password, email) VALUES
('AYUSH1234', 'Ayush@2908', 'admin@esports.com');

-- Insert Sample Teams
INSERT INTO teams (team_name, description, total_points, wins, losses) VALUES
('Phoenix Rising', 'Professional CS:GO team', 500, 25, 10),
('Shadow Legends', 'Competitive Valorant squad', 450, 22, 12),
('Dragon Force', 'Multi-game esports organization', 420, 20, 15),
('Thunder Hawks', 'Emerging competitive team', 380, 18, 18),
('Cyber Ninjas', 'High-skilled Dota 2 team', 350, 16, 20);

-- Insert Sample Players
INSERT INTO players (player_name, role, team_id, skill, frags, kd) VALUES
('Alex Striker', 'Fragger', 1, 'Pro', 2500, 1.85),
('Chris Rifler', 'Rifler', 1, 'Advanced', 2100, 1.45),
('Sam Support', 'Support', 1, 'Advanced', 1800, 1.20),
('Mike IGL', 'IGL', 1, 'Expert', 1500, 1.05),
('Jake AWPer', 'AWPer', 1, 'Expert', 2200, 1.65),

('Luna Ghost', 'Fragger', 2, 'Expert', 2300, 1.78),
('Nova Shield', 'Support', 2, 'Advanced', 1900, 1.30),
('Titan Force', 'Rifler', 2, 'Advanced', 2000, 1.40),
('Echo Lead', 'IGL', 2, 'Advanced', 1700, 1.15),
('Spark Burst', 'AWPer', 2, 'Expert', 2100, 1.55),

('Blaze Assault', 'Fragger', 3, 'Advanced', 2000, 1.50),
('Storm Breaker', 'Rifler', 3, 'Advanced', 1800, 1.35),
('Flame Support', 'Support', 3, 'Intermediate', 1500, 1.10),
('Vortex IGL', 'IGL', 3, 'Advanced', 1600, 1.12),
('Strike AWPer', 'AWPer', 3, 'Advanced', 1900, 1.45),

('Bullet Rain', 'Fragger', 4, 'Intermediate', 1600, 1.25),
('Shield Wall', 'Support', 4, 'Intermediate', 1300, 0.95),
('Cyber Rifler', 'Rifler', 4, 'Intermediate', 1500, 1.15),
('Nexus IGL', 'IGL', 4, 'Intermediate', 1400, 1.05),
('Wind Arrow', 'AWPer', 4, 'Intermediate', 1700, 1.30),

('Ninja Strike', 'Fragger', 5, 'Advanced', 1900, 1.55),
('Shadow Clone', 'Support', 5, 'Intermediate', 1400, 1.05),
('Smoke Screen', 'Rifler', 5, 'Advanced', 1700, 1.30),
('Silent Move', 'IGL', 5, 'Intermediate', 1500, 1.10),
('Blade Master', 'AWPer', 5, 'Advanced', 1800, 1.40);

-- Insert Sample Tournaments
INSERT INTO tournaments (tournament_name, description, start_date, end_date, prize_pool, status) VALUES
('International Championship 2025', 'World-class esports tournament', '2025-03-01', '2025-03-30', '$500,000', 'upcoming'),
('Regional Qualifiers', 'Regional competitive tournament', '2025-02-15', '2025-02-28', '$50,000', 'ongoing'),
('Weekly Challenge', 'Weekly competitive matches', '2025-01-20', '2025-01-27', '$10,000', 'completed'),
('Spring Masters', 'Spring season tournament', '2025-04-01', '2025-05-31', '$200,000', 'upcoming'),
('Pro League Finals', 'Professional league final matches', '2025-06-15', '2025-06-30', '$300,000', 'upcoming');

-- Insert Sample Tournament Teams
INSERT INTO tournament_teams (tournament_id, team_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 2), (2, 3),
(3, 1), (3, 2), (3, 4),
(4, 1), (4, 2), (4, 3), (4, 4),
(5, 1), (5, 2), (5, 3), (5, 4), (5, 5);

-- Insert Sample Matches
INSERT INTO matches (tournament_id, team1_id, team2_id, team1_points, team2_points, winner_id, status, match_date) VALUES
(2, 1, 2, 2, 1, '1', 'completed', '2025-02-20 18:00:00'),
(2, 3, 4, 1, 2, '4', 'completed', '2025-02-20 19:00:00'),
(2, 1, 3, 0, 0, NULL, 'scheduled', '2025-02-25 18:00:00'),
(3, 1, 4, 16, 8, '1', 'completed', '2025-01-22 20:00:00'),
(3, 2, 4, 14, 12, '2', 'completed', '2025-01-22 21:00:00'),
(1, 1, 2, 0, 0, NULL, 'scheduled', '2025-03-15 19:00:00'),
(1, 3, 4, 0, 0, NULL, 'scheduled', '2025-03-16 19:00:00'),
(1, 2, 5, 0, 0, NULL, 'scheduled', '2025-03-17 20:00:00');

-- Create Views for easier querying
CREATE OR REPLACE VIEW team_leaderboard AS
SELECT 
    team_id,
    team_name,
    total_points,
    wins,
    losses,
    (wins + losses) as total_matches,
    ROUND((wins / (wins + losses)) * 100, 2) as win_percentage,
    RANK() OVER (ORDER BY total_points DESC) as rank
FROM teams
ORDER BY total_points DESC;

CREATE OR REPLACE VIEW player_stats AS
SELECT 
    p.player_id,
    p.player_name,
    p.role,
    t.team_name,
    p.skill,
    p.frags,
    p.kd,
    (SELECT COUNT(*) FROM matches WHERE (team1_id = p.team_id OR team2_id = p.team_id) AND status = 'completed') as team_matches
FROM players p
JOIN teams t ON p.team_id = t.team_id
ORDER BY p.kd DESC;

CREATE OR REPLACE VIEW tournament_stats AS
SELECT 
    t.tournament_id,
    t.tournament_name,
    t.status,
    t.start_date,
    t.end_date,
    COUNT(DISTINCT tt.team_id) as participating_teams,
    COUNT(DISTINCT m.match_id) as total_matches,
    (SELECT COUNT(*) FROM matches WHERE tournament_id = t.tournament_id AND status = 'completed') as completed_matches
FROM tournaments t
LEFT JOIN tournament_teams tt ON t.tournament_id = tt.tournament_id
LEFT JOIN matches m ON t.tournament_id = m.tournament_id
GROUP BY t.tournament_id, t.tournament_name, t.status, t.start_date, t.end_date;

-- Create Indexes for better performance
CREATE INDEX idx_team_points ON teams(total_points DESC);
CREATE INDEX idx_player_team ON players(team_id);
CREATE INDEX idx_match_tournament ON matches(tournament_id);
CREATE INDEX idx_match_status ON matches(status);
CREATE INDEX idx_tournament_status ON tournaments(status);
CREATE INDEX idx_admin_username ON admins(username);

-- Display confirmation message
SELECT CONCAT('eSports Team Management System Database Created Successfully!') as status;
