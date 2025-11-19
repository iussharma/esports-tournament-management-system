/**
 * API Module - Firestore CRUD Operations
 * Replaces servlet endpoints with Firestore operations
 * Maps old servlet endpoints to new Firestore collections
 * 
 * SERVLET → FIRESTORE MAPPING:
 * /team?action=list    → firestore: db.collection('teams')
 * /team?action=form    → firestore: creates new team doc
 * /team?action=create  → firestore: teams.add()
 * /team?action=edit&id → firestore: teams.doc(id)
 * /team?action=update  → firestore: teams.doc(id).update()
 * /team?action=delete  → firestore: teams.doc(id).delete()
 * /player?action=list  → firestore: db.collection('players')
 * /tournament?action=* → firestore: db.collection('tournaments')
 * /match?action=*      → firestore: db.collection('matches')
 * /dashboard           → firestore: aggregated queries
 */

const API = {
  // ==================== TEAM API ====================
  
  /**
   * Get all teams
   * Replaces: GET /team?action=list
   */
  async getTeams() {
    if (USE_DEMO_MODE) {
      return getTeamsDemo();
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      const snapshot = await db.collection('teams')
        .orderBy('teamName')
        .get();

      const teams = [];
      snapshot.forEach(doc => {
        teams.push({
          teamId: doc.id,
          ...doc.data()
        });
      });
      return teams;
    } catch (error) {
      console.error('Error fetching teams:', error);
      showError('Failed to load teams');
      return [];
    }
  },

  /**
   * Get team by ID
   * Replaces: GET /team?action=edit&id=X
   */
  async getTeam(teamId) {
    if (USE_DEMO_MODE) {
      return getTeamDemo(teamId);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      const doc = await db.collection('teams').doc(teamId).get();
      if (doc.exists) {
        return {
          teamId: doc.id,
          ...doc.data()
        };
      }
      return null;
    } catch (error) {
      console.error('Error fetching team:', error);
      showError('Failed to load team');
      return null;
    }
  },

  /**
   * Create new team
   * Replaces: POST /team?action=create
   */
  async createTeam(teamData) {
    if (USE_DEMO_MODE) {
      return createTeamDemo(teamData);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      // Validate required fields
      if (!teamData.teamName) throw new Error('Team name is required');

      const docRef = await db.collection('teams').add({
        teamName: teamData.teamName,
        description: teamData.description || '',
        totalPoints: 0,
        wins: 0,
        losses: 0,
        createdAt: new Date(),
        updatedAt: new Date()
      });

      showSuccess('Team created successfully');
      return docRef.id;
    } catch (error) {
      console.error('Error creating team:', error);
      showError('Failed to create team: ' + error.message);
      return null;
    }
  },

  /**
   * Update team
   * Replaces: POST /team?action=update
   */
  async updateTeam(teamId, teamData) {
    if (USE_DEMO_MODE) {
      return updateTeamDemo(teamId, teamData);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      if (!teamData.teamName) throw new Error('Team name is required');

      await db.collection('teams').doc(teamId).update({
        teamName: teamData.teamName,
        description: teamData.description || '',
        updatedAt: new Date()
      });

      showSuccess('Team updated successfully');
      return true;
    } catch (error) {
      console.error('Error updating team:', error);
      showError('Failed to update team: ' + error.message);
      return false;
    }
  },

  /**
   * Delete team
   * Replaces: POST /team?action=delete
   */
  async deleteTeam(teamId) {
    if (USE_DEMO_MODE) {
      return deleteTeamDemo(teamId);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      await db.collection('teams').doc(teamId).delete();
      showSuccess('Team deleted successfully');
      return true;
    } catch (error) {
      console.error('Error deleting team:', error);
      showError('Failed to delete team: ' + error.message);
      return false;
    }
  },

  /**
   * Get team with players
   * Replaces: GET /team?action=view&id=X
   */
  async getTeamWithPlayers(teamId) {
    if (USE_DEMO_MODE) {
      return getTeamWithPlayersDemo(teamId);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      const teamDoc = await db.collection('teams').doc(teamId).get();
      if (!teamDoc.exists) return null;

      const playersSnapshot = await db.collection('players')
        .where('teamId', '==', teamId)
        .orderBy('role')
        .get();

      const players = [];
      playersSnapshot.forEach(doc => {
        players.push({
          playerId: doc.id,
          ...doc.data()
        });
      });

      return {
        teamId: teamDoc.id,
        ...teamDoc.data(),
        players: players
      };
    } catch (error) {
      console.error('Error fetching team with players:', error);
      showError('Failed to load team details');
      return null;
    }
  },

  // ==================== PLAYER API ====================

  /**
   * Get all players
   * Replaces: GET /player?action=list
   */
  async getPlayers() {
    if (USE_DEMO_MODE) {
      return getPlayersDemo();
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      const snapshot = await db.collection('players')
        .orderBy('playerName')
        .get();

      const players = [];
      snapshot.forEach(doc => {
        players.push({
          playerId: doc.id,
          ...doc.data()
        });
      });
      return players;
    } catch (error) {
      console.error('Error fetching players:', error);
      showError('Failed to load players');
      return [];
    }
  },

  /**
   * Get player by ID
   */
  async getPlayer(playerId) {
    if (USE_DEMO_MODE) {
      return getPlayerDemo(playerId);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      const doc = await db.collection('players').doc(playerId).get();
      if (doc.exists) {
        return {
          playerId: doc.id,
          ...doc.data()
        };
      }
      return null;
    } catch (error) {
      console.error('Error fetching player:', error);
      showError('Failed to load player');
      return null;
    }
  },

  /**
   * Create new player
   * Replaces: POST /player?action=create
   */
  async createPlayer(playerData) {
    if (USE_DEMO_MODE) {
      return createPlayerDemo(playerData);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      if (!playerData.playerName || !playerData.teamId) {
        throw new Error('Player name and team are required');
      }

      const docRef = await db.collection('players').add({
        playerName: playerData.playerName,
        role: playerData.role || 'Player',
        teamId: playerData.teamId,
        kd_ratio: parseFloat(playerData.kd_ratio) || 1.0,
        skill_rating: parseInt(playerData.skill_rating) || 50,
        createdAt: new Date(),
        updatedAt: new Date()
      });

      showSuccess('Player added successfully');
      return docRef.id;
    } catch (error) {
      console.error('Error creating player:', error);
      showError('Failed to create player: ' + error.message);
      return null;
    }
  },

  /**
   * Update player
   * Replaces: POST /player?action=update
   */
  async updatePlayer(playerId, playerData) {
    if (USE_DEMO_MODE) {
      return updatePlayerDemo(playerId, playerData);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      await db.collection('players').doc(playerId).update({
        playerName: playerData.playerName,
        role: playerData.role,
        kd_ratio: parseFloat(playerData.kd_ratio),
        skill_rating: parseInt(playerData.skill_rating),
        updatedAt: new Date()
      });

      showSuccess('Player updated successfully');
      return true;
    } catch (error) {
      console.error('Error updating player:', error);
      showError('Failed to update player: ' + error.message);
      return false;
    }
  },

  /**
   * Delete player
   * Replaces: POST /player?action=delete
   */
  async deletePlayer(playerId) {
    if (USE_DEMO_MODE) {
      return deletePlayerDemo(playerId);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      await db.collection('players').doc(playerId).delete();
      showSuccess('Player deleted successfully');
      return true;
    } catch (error) {
      console.error('Error deleting player:', error);
      showError('Failed to delete player: ' + error.message);
      return false;
    }
  },

  // ==================== TOURNAMENT API ====================

  /**
   * Get all tournaments
   * Replaces: GET /tournament?action=list
   */
  async getTournaments() {
    if (USE_DEMO_MODE) {
      return getTournamentsDemo();
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      const snapshot = await db.collection('tournaments')
        .orderBy('tournamentName')
        .get();

      const tournaments = [];
      snapshot.forEach(doc => {
        tournaments.push({
          tournamentId: doc.id,
          ...doc.data()
        });
      });
      return tournaments;
    } catch (error) {
      console.error('Error fetching tournaments:', error);
      showError('Failed to load tournaments');
      return [];
    }
  },

  /**
   * Get tournament by ID
   */
  async getTournament(tournamentId) {
    if (USE_DEMO_MODE) {
      return getTournamentDemo(tournamentId);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      const doc = await db.collection('tournaments').doc(tournamentId).get();
      if (doc.exists) {
        return {
          tournamentId: doc.id,
          ...doc.data()
        };
      }
      return null;
    } catch (error) {
      console.error('Error fetching tournament:', error);
      showError('Failed to load tournament');
      return null;
    }
  },

  /**
   * Create tournament
   * Replaces: POST /tournament?action=create
   */
  async createTournament(tournamentData) {
    if (USE_DEMO_MODE) {
      return createTournamentDemo(tournamentData);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      if (!tournamentData.tournamentName) {
        throw new Error('Tournament name is required');
      }

      const docRef = await db.collection('tournaments').add({
        tournamentName: tournamentData.tournamentName,
        description: tournamentData.description || '',
        status: 'upcoming',
        createdAt: new Date(),
        updatedAt: new Date()
      });

      showSuccess('Tournament created successfully');
      return docRef.id;
    } catch (error) {
      console.error('Error creating tournament:', error);
      showError('Failed to create tournament: ' + error.message);
      return null;
    }
  },

  /**
   * Update tournament
   */
  async updateTournament(tournamentId, tournamentData) {
    if (USE_DEMO_MODE) {
      return updateTournamentDemo(tournamentId, tournamentData);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      await db.collection('tournaments').doc(tournamentId).update({
        tournamentName: tournamentData.tournamentName,
        description: tournamentData.description,
        status: tournamentData.status,
        updatedAt: new Date()
      });

      showSuccess('Tournament updated successfully');
      return true;
    } catch (error) {
      console.error('Error updating tournament:', error);
      showError('Failed to update tournament: ' + error.message);
      return false;
    }
  },

  /**
   * Delete tournament
   */
  async deleteTournament(tournamentId) {
    if (USE_DEMO_MODE) {
      return deleteTournamentDemo(tournamentId);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      await db.collection('tournaments').doc(tournamentId).delete();
      showSuccess('Tournament deleted successfully');
      return true;
    } catch (error) {
      console.error('Error deleting tournament:', error);
      showError('Failed to delete tournament: ' + error.message);
      return false;
    }
  },

  // ==================== MATCH API ====================

  /**
   * Get all matches
   * Replaces: GET /match?action=list
   */
  async getMatches() {
    if (USE_DEMO_MODE) {
      return getMatchesDemo();
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      const snapshot = await db.collection('matches')
        .orderBy('scheduledDate', 'desc')
        .get();

      const matches = [];
      snapshot.forEach(doc => {
        matches.push({
          matchId: doc.id,
          ...doc.data()
        });
      });
      return matches;
    } catch (error) {
      console.error('Error fetching matches:', error);
      showError('Failed to load matches');
      return [];
    }
  },

  /**
   * Get match by ID
   */
  async getMatch(matchId) {
    if (USE_DEMO_MODE) {
      return getMatchDemo(matchId);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      const doc = await db.collection('matches').doc(matchId).get();
      if (doc.exists) {
        return {
          matchId: doc.id,
          ...doc.data()
        };
      }
      return null;
    } catch (error) {
      console.error('Error fetching match:', error);
      showError('Failed to load match');
      return null;
    }
  },

  /**
   * Create match
   * Replaces: POST /match?action=create
   */
  async createMatch(matchData) {
    if (USE_DEMO_MODE) {
      return createMatchDemo(matchData);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      if (!matchData.team1Id || !matchData.team2Id) {
        throw new Error('Both teams are required');
      }

      const docRef = await db.collection('matches').add({
        team1Id: matchData.team1Id,
        team2Id: matchData.team2Id,
        tournamentId: matchData.tournamentId || null,
        scheduledDate: matchData.scheduledDate,
        status: 'scheduled',
        team1Score: 0,
        team2Score: 0,
        createdAt: new Date(),
        updatedAt: new Date()
      });

      showSuccess('Match scheduled successfully');
      return docRef.id;
    } catch (error) {
      console.error('Error creating match:', error);
      showError('Failed to create match: ' + error.message);
      return null;
    }
  },

  /**
   * Update match result
   * Replaces: POST /match?action=result
   */
  async updateMatchResult(matchId, team1Score, team2Score) {
    if (USE_DEMO_MODE) {
      return updateMatchResultDemo(matchId, team1Score, team2Score);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      const matchDoc = await db.collection('matches').doc(matchId).get();
      if (!matchDoc.exists) throw new Error('Match not found');

      const matchData = matchDoc.data();

      // Update match status and scores
      await db.collection('matches').doc(matchId).update({
        status: 'completed',
        team1Score: parseInt(team1Score),
        team2Score: parseInt(team2Score),
        updatedAt: new Date()
      });

      // Update team stats
      const winningTeamId = team1Score > team2Score ? matchData.team1Id : matchData.team2Id;
      const losingTeamId = team1Score < team2Score ? matchData.team1Id : matchData.team2Id;

      const teamRef = db.collection('teams');
      
      if (team1Score !== team2Score) {
        await teamRef.doc(winningTeamId).update({
          wins: firebase.firestore.FieldValue.increment(1),
          totalPoints: firebase.firestore.FieldValue.increment(3)
        });

        await teamRef.doc(losingTeamId).update({
          losses: firebase.firestore.FieldValue.increment(1)
        });
      }

      showSuccess('Match result updated successfully');
      return true;
    } catch (error) {
      console.error('Error updating match result:', error);
      showError('Failed to update match result: ' + error.message);
      return false;
    }
  },

  /**
   * Delete match
   */
  async deleteMatch(matchId) {
    if (USE_DEMO_MODE) {
      return deleteMatchDemo(matchId);
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      await db.collection('matches').doc(matchId).delete();
      showSuccess('Match deleted successfully');
      return true;
    } catch (error) {
      console.error('Error deleting match:', error);
      showError('Failed to delete match: ' + error.message);
      return false;
    }
  },

  // ==================== DASHBOARD API ====================

  /**
   * Get dashboard stats
   * Replaces: GET /dashboard (DashboardServlet)
   */
  async getDashboardStats() {
    if (USE_DEMO_MODE) {
      return getDashboardStatsDemo();
    }

    try {
      const db = getDb();
      if (!db) throw new Error('Firestore not initialized');

      // Get counts
      const teamsSnapshot = await db.collection('teams').get();
      const tournamentsSnapshot = await db.collection('tournaments').get();
      const playersSnapshot = await db.collection('players').get();

      // Get top 5 teams
      const topTeamsSnapshot = await db.collection('teams')
        .orderBy('totalPoints', 'desc')
        .limit(5)
        .get();

      const topTeams = [];
      topTeamsSnapshot.forEach(doc => {
        topTeams.push({
          teamId: doc.id,
          ...doc.data()
        });
      });

      return {
        totalTeams: teamsSnapshot.size,
        totalTournaments: tournamentsSnapshot.size,
        totalPlayers: playersSnapshot.size,
        topTeams: topTeams
      };
    } catch (error) {
      console.error('Error fetching dashboard stats:', error);
      showError('Failed to load dashboard');
      return {
        totalTeams: 0,
        totalTournaments: 0,
        totalPlayers: 0,
        topTeams: []
      };
    }
  }
};

// ==================== DEMO MODE FUNCTIONS ====================
// These functions simulate Firestore operations using localStorage

let demoTeams = [];
let demoPlayers = [];
let demoTournaments = [];
let demoMatches = [];

function loadDemoData() {
  // Initialize from localStorage or use defaults
  const stored = {
    teams: localStorage.getItem('demo_teams'),
    players: localStorage.getItem('demo_players'),
    tournaments: localStorage.getItem('demo_tournaments'),
    matches: localStorage.getItem('demo_matches')
  };

  if (stored.teams) {
    demoTeams = JSON.parse(stored.teams);
  } else {
    demoTeams = [
      { teamId: 'team1', teamName: 'Phoenix Rising', description: 'Elite CS:GO team', totalPoints: 120, wins: 8, losses: 2, createdAt: new Date() },
      { teamId: 'team2', teamName: 'Dragon Force', description: 'Valorant specialists', totalPoints: 95, wins: 6, losses: 4, createdAt: new Date() },
      { teamId: 'team3', teamName: 'Titan Squad', description: 'Dota 2 champions', totalPoints: 105, wins: 7, losses: 3, createdAt: new Date() },
    ];
    localStorage.setItem('demo_teams', JSON.stringify(demoTeams));
  }

  if (stored.players) {
    demoPlayers = JSON.parse(stored.players);
  } else {
    demoPlayers = [
      { playerId: 'p1', playerName: 'AlexPro', role: 'Rifler', teamId: 'team1', kd_ratio: 1.45, skill_rating: 92 },
      { playerId: 'p2', playerName: 'SniperKing', role: 'AWP', teamId: 'team1', kd_ratio: 1.65, skill_rating: 95 },
      { playerId: 'p3', playerName: 'SupportGod', role: 'Support', teamId: 'team2', kd_ratio: 0.95, skill_rating: 88 },
    ];
    localStorage.setItem('demo_players', JSON.stringify(demoPlayers));
  }

  if (stored.tournaments) {
    demoTournaments = JSON.parse(stored.tournaments);
  } else {
    demoTournaments = [
      { tournamentId: 't1', tournamentName: 'World Championship 2025', description: 'Global esports tournament', status: 'upcoming' },
      { tournamentId: 't2', tournamentName: 'Regional Qualifiers', description: 'Regional competition', status: 'ongoing' },
    ];
    localStorage.setItem('demo_tournaments', JSON.stringify(demoTournaments));
  }

  if (stored.matches) {
    demoMatches = JSON.parse(stored.matches);
  } else {
    demoMatches = [
      { matchId: 'm1', team1Id: 'team1', team2Id: 'team2', status: 'completed', team1Score: 16, team2Score: 14 },
      { matchId: 'm2', team1Id: 'team1', team2Id: 'team3', status: 'scheduled', team1Score: 0, team2Score: 0 },
    ];
    localStorage.setItem('demo_matches', JSON.stringify(demoMatches));
  }
}

// Team demo functions
async function getTeamsDemo() { return demoTeams; }
async function getTeamDemo(teamId) { return demoTeams.find(t => t.teamId === teamId) || null; }
async function createTeamDemo(data) { const id = 'team' + Date.now(); demoTeams.push({ teamId: id, ...data, totalPoints: 0, wins: 0, losses: 0 }); localStorage.setItem('demo_teams', JSON.stringify(demoTeams)); return id; }
async function updateTeamDemo(teamId, data) { const t = demoTeams.find(x => x.teamId === teamId); if (t) Object.assign(t, data); localStorage.setItem('demo_teams', JSON.stringify(demoTeams)); return true; }
async function deleteTeamDemo(teamId) { demoTeams = demoTeams.filter(t => t.teamId !== teamId); localStorage.setItem('demo_teams', JSON.stringify(demoTeams)); return true; }
async function getTeamWithPlayersDemo(teamId) { const team = demoTeams.find(t => t.teamId === teamId); if (!team) return null; return { ...team, players: demoPlayers.filter(p => p.teamId === teamId) }; }

// Player demo functions
async function getPlayersDemo() { return demoPlayers; }
async function getPlayerDemo(playerId) { return demoPlayers.find(p => p.playerId === playerId) || null; }
async function createPlayerDemo(data) { const id = 'player' + Date.now(); demoPlayers.push({ playerId: id, ...data }); localStorage.setItem('demo_players', JSON.stringify(demoPlayers)); return id; }
async function updatePlayerDemo(playerId, data) { const p = demoPlayers.find(x => x.playerId === playerId); if (p) Object.assign(p, data); localStorage.setItem('demo_players', JSON.stringify(demoPlayers)); return true; }
async function deletePlayerDemo(playerId) { demoPlayers = demoPlayers.filter(p => p.playerId !== playerId); localStorage.setItem('demo_players', JSON.stringify(demoPlayers)); return true; }

// Tournament demo functions
async function getTournamentsDemo() { return demoTournaments; }
async function getTournamentDemo(tournamentId) { return demoTournaments.find(t => t.tournamentId === tournamentId) || null; }
async function createTournamentDemo(data) { const id = 't' + Date.now(); demoTournaments.push({ tournamentId: id, ...data, status: 'upcoming' }); localStorage.setItem('demo_tournaments', JSON.stringify(demoTournaments)); return id; }
async function updateTournamentDemo(tournamentId, data) { const t = demoTournaments.find(x => x.tournamentId === tournamentId); if (t) Object.assign(t, data); localStorage.setItem('demo_tournaments', JSON.stringify(demoTournaments)); return true; }
async function deleteTournamentDemo(tournamentId) { demoTournaments = demoTournaments.filter(t => t.tournamentId !== tournamentId); localStorage.setItem('demo_tournaments', JSON.stringify(demoTournaments)); return true; }

// Match demo functions
async function getMatchesDemo() { return demoMatches; }
async function getMatchDemo(matchId) { return demoMatches.find(m => m.matchId === matchId) || null; }
async function createMatchDemo(data) { const id = 'm' + Date.now(); demoMatches.push({ matchId: id, ...data, status: 'scheduled', team1Score: 0, team2Score: 0 }); localStorage.setItem('demo_matches', JSON.stringify(demoMatches)); return id; }
async function updateMatchResultDemo(matchId, t1, t2) { const m = demoMatches.find(x => x.matchId === matchId); if (m) { m.status = 'completed'; m.team1Score = t1; m.team2Score = t2; } localStorage.setItem('demo_matches', JSON.stringify(demoMatches)); return true; }
async function deleteMatchDemo(matchId) { demoMatches = demoMatches.filter(m => m.matchId !== matchId); localStorage.setItem('demo_matches', JSON.stringify(demoMatches)); return true; }

// Dashboard demo
async function getDashboardStatsDemo() {
  return {
    totalTeams: demoTeams.length,
    totalTournaments: demoTournaments.length,
    totalPlayers: demoPlayers.length,
    topTeams: demoTeams.sort((a, b) => b.totalPoints - a.totalPoints).slice(0, 5)
  };
}
