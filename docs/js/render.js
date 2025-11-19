/**
 * Render Module - DOM manipulation and dynamic content rendering
 * Replaces JSP templating with DOM element updates
 * Handles form submissions, navigation, and data binding
 */

const Render = {
  /**
   * Render teams list
   * Replaces: team/list.jsp <c:forEach items="${teams}">
   */
  async renderTeamsList(containerSelector = '[data-page="teams-list"]') {
    try {
      const container = document.querySelector(containerSelector);
      if (!container) return;

      const teams = await API.getTeams();
      const tbody = container.querySelector('tbody');
      
      if (!tbody) return;

      tbody.innerHTML = '';

      if (teams.length === 0) {
        tbody.innerHTML = `
          <tr>
            <td colspan="7" class="text-center">
              No teams found. <a href="/team-form.html">Create one</a>
            </td>
          </tr>
        `;
        return;
      }

      teams.forEach(team => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${team.teamId}</td>
          <td>${team.teamName}</td>
          <td>${team.description || 'N/A'}</td>
          <td><strong>${team.totalPoints || 0}</strong></td>
          <td>${team.wins || 0}</td>
          <td>${team.losses || 0}</td>
          <td>
            <a href="/team-form.html?id=${team.teamId}" class="btn btn-sm btn-primary">Edit</a>
            <a href="/team-view.html?id=${team.teamId}" class="btn btn-sm btn-info">View</a>
            <button class="btn btn-sm btn-danger" onclick="Render.deleteTeam('${team.teamId}')">Delete</button>
          </td>
        `;
        tbody.appendChild(row);
      });
    } catch (error) {
      console.error('Error rendering teams list:', error);
      showError('Failed to render teams');
    }
  },

  /**
   * Render team form
   * Replaces: team/form.jsp form population
   */
  async renderTeamForm(containerId = 'teamForm') {
    try {
      const form = document.getElementById(containerId);
      if (!form) return;

      // Check for edit mode (ID in URL)
      const params = new URLSearchParams(window.location.search);
      const teamId = params.get('id');

      if (teamId) {
        // Edit mode - load team data
        const team = await API.getTeam(teamId);
        if (team) {
          document.getElementById('teamName').value = team.teamName || '';
          document.getElementById('description').value = team.description || '';
          document.querySelector('[data-field="mode"]').value = 'update';
          document.querySelector('[data-field="teamId"]').value = teamId;
          document.querySelector('h1').textContent = 'Edit Team';
          document.querySelector('button[type="submit"]').textContent = 'Update Team';
        }
      }

      // Handle form submission
      form.addEventListener('submit', async (e) => {
        e.preventDefault();
        await Render.submitTeamForm(form, teamId);
      });
    } catch (error) {
      console.error('Error rendering team form:', error);
      showError('Failed to load team form');
    }
  },

  /**
   * Handle team form submission
   */
  async submitTeamForm(form, teamId) {
    try {
      const teamName = document.getElementById('teamName').value.trim();
      const description = document.getElementById('description').value.trim();

      if (!teamName) {
        showError('Team name is required');
        return;
      }

      const teamData = { teamName, description };

      let success;
      if (teamId) {
        success = await API.updateTeam(teamId, teamData);
      } else {
        const newId = await API.createTeam(teamData);
        success = !!newId;
      }

      if (success) {
        setTimeout(() => {
          window.location.href = '/team-list.html';
        }, 1000);
      }
    } catch (error) {
      console.error('Error submitting team form:', error);
      showError('Failed to save team');
    }
  },

  /**
   * Delete team with confirmation
   */
  async deleteTeam(teamId) {
    if (confirm('Are you sure you want to delete this team?')) {
      const success = await API.deleteTeam(teamId);
      if (success) {
        setTimeout(() => {
          Render.renderTeamsList();
        }, 1000);
      }
    }
  },

  /**
   * Render team view (team with players)
   * Replaces: team/view.jsp
   */
  async renderTeamView(containerId = 'teamViewContainer') {
    try {
      const params = new URLSearchParams(window.location.search);
      const teamId = params.get('id');

      if (!teamId) {
        showError('Team not found');
        return;
      }

      const teamData = await API.getTeamWithPlayers(teamId);
      if (!teamData) {
        showError('Team not found');
        return;
      }

      const container = document.getElementById(containerId);
      if (!container) return;

      // Render team details
      const detailsHtml = `
        <div class="team-details">
          <h2>${teamData.teamName}</h2>
          <div class="detail-card">
            <p><strong>Description:</strong> ${teamData.description || 'N/A'}</p>
            <p><strong>Total Points:</strong> ${teamData.totalPoints || 0}</p>
            <p><strong>Wins:</strong> ${teamData.wins || 0}</p>
            <p><strong>Losses:</strong> ${teamData.losses || 0}</p>
          </div>
        </div>
      `;

      // Render players
      let playersHtml = '<h3>Team Players</h3>';
      if (teamData.players && teamData.players.length > 0) {
        playersHtml += '<table class="data-table"><thead><tr><th>Name</th><th>Role</th><th>K/D</th><th>Skill</th></tr></thead><tbody>';
        teamData.players.forEach(player => {
          playersHtml += `
            <tr>
              <td>${player.playerName}</td>
              <td>${player.role}</td>
              <td>${player.kd_ratio.toFixed(2)}</td>
              <td>${player.skill_rating}</td>
            </tr>
          `;
        });
        playersHtml += '</tbody></table>';
      } else {
        playersHtml += '<p>No players in this team yet.</p>';
      }

      container.innerHTML = detailsHtml + playersHtml;
    } catch (error) {
      console.error('Error rendering team view:', error);
      showError('Failed to load team details');
    }
  },

  /**
   * Render players list
   * Replaces: player/list.jsp
   */
  async renderPlayersList(containerSelector = '[data-page="players-list"]') {
    try {
      const container = document.querySelector(containerSelector);
      if (!container) return;

      const players = await API.getPlayers();
      const tbody = container.querySelector('tbody');
      
      if (!tbody) return;

      tbody.innerHTML = '';

      if (players.length === 0) {
        tbody.innerHTML = `
          <tr>
            <td colspan="6" class="text-center">
              No players found. <a href="/player-form.html">Add one</a>
            </td>
          </tr>
        `;
        return;
      }

      players.forEach(player => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${player.playerId}</td>
          <td>${player.playerName}</td>
          <td>${player.role}</td>
          <td>${player.kd_ratio?.toFixed(2) || 'N/A'}</td>
          <td>${player.skill_rating || 'N/A'}</td>
          <td>
            <a href="/player-form.html?id=${player.playerId}" class="btn btn-sm btn-primary">Edit</a>
            <button class="btn btn-sm btn-danger" onclick="Render.deletePlayer('${player.playerId}')">Delete</button>
          </td>
        `;
        tbody.appendChild(row);
      });
    } catch (error) {
      console.error('Error rendering players list:', error);
      showError('Failed to render players');
    }
  },

  /**
   * Render player form
   */
  async renderPlayerForm(containerId = 'playerForm') {
    try {
      const form = document.getElementById(containerId);
      if (!form) return;

      // Get teams for dropdown
      const teams = await API.getTeams();
      const teamSelect = document.getElementById('teamId');
      if (teamSelect) {
        teamSelect.innerHTML = '<option value="">Select Team</option>';
        teams.forEach(team => {
          const option = document.createElement('option');
          option.value = team.teamId;
          option.textContent = team.teamName;
          teamSelect.appendChild(option);
        });
      }

      // Check for edit mode
      const params = new URLSearchParams(window.location.search);
      const playerId = params.get('id');

      if (playerId) {
        const player = await API.getPlayer(playerId);
        if (player) {
          document.getElementById('playerName').value = player.playerName || '';
          document.getElementById('role').value = player.role || '';
          document.getElementById('teamId').value = player.teamId || '';
          document.getElementById('kd_ratio').value = player.kd_ratio || '';
          document.getElementById('skill_rating').value = player.skill_rating || '';
          document.querySelector('h1').textContent = 'Edit Player';
          document.querySelector('button[type="submit"]').textContent = 'Update Player';
        }
      }

      form.addEventListener('submit', async (e) => {
        e.preventDefault();
        await Render.submitPlayerForm(form, playerId);
      });
    } catch (error) {
      console.error('Error rendering player form:', error);
      showError('Failed to load player form');
    }
  },

  /**
   * Submit player form
   */
  async submitPlayerForm(form, playerId) {
    try {
      const playerName = document.getElementById('playerName').value.trim();
      const teamId = document.getElementById('teamId').value;
      const role = document.getElementById('role').value.trim();
      const kd_ratio = document.getElementById('kd_ratio').value;
      const skill_rating = document.getElementById('skill_rating').value;

      if (!playerName || !teamId) {
        showError('Player name and team are required');
        return;
      }

      const playerData = { playerName, teamId, role, kd_ratio, skill_rating };

      let success;
      if (playerId) {
        success = await API.updatePlayer(playerId, playerData);
      } else {
        const newId = await API.createPlayer(playerData);
        success = !!newId;
      }

      if (success) {
        setTimeout(() => {
          window.location.href = '/player-list.html';
        }, 1000);
      }
    } catch (error) {
      console.error('Error submitting player form:', error);
      showError('Failed to save player');
    }
  },

  /**
   * Delete player
   */
  async deletePlayer(playerId) {
    if (confirm('Are you sure you want to delete this player?')) {
      const success = await API.deletePlayer(playerId);
      if (success) {
        setTimeout(() => {
          Render.renderPlayersList();
        }, 1000);
      }
    }
  },

  /**
   * Render tournaments list
   */
  async renderTournamentsList(containerSelector = '[data-page="tournaments-list"]') {
    try {
      const container = document.querySelector(containerSelector);
      if (!container) return;

      const tournaments = await API.getTournaments();
      const tbody = container.querySelector('tbody');
      
      if (!tbody) return;

      tbody.innerHTML = '';

      if (tournaments.length === 0) {
        tbody.innerHTML = `
          <tr>
            <td colspan="5" class="text-center">
              No tournaments found. <a href="/tournament-form.html">Create one</a>
            </td>
          </tr>
        `;
        return;
      }

      tournaments.forEach(tournament => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${tournament.tournamentId}</td>
          <td>${tournament.tournamentName}</td>
          <td>${tournament.description || 'N/A'}</td>
          <td><span class="badge badge-${tournament.status}">${tournament.status}</span></td>
          <td>
            <a href="/tournament-form.html?id=${tournament.tournamentId}" class="btn btn-sm btn-primary">Edit</a>
            <button class="btn btn-sm btn-danger" onclick="Render.deleteTournament('${tournament.tournamentId}')">Delete</button>
          </td>
        `;
        tbody.appendChild(row);
      });
    } catch (error) {
      console.error('Error rendering tournaments list:', error);
      showError('Failed to render tournaments');
    }
  },

  /**
   * Delete tournament
   */
  async deleteTournament(tournamentId) {
    if (confirm('Are you sure you want to delete this tournament?')) {
      const success = await API.deleteTournament(tournamentId);
      if (success) {
        setTimeout(() => {
          Render.renderTournamentsList();
        }, 1000);
      }
    }
  },

  /**
   * Render matches list
   */
  async renderMatchesList(containerSelector = '[data-page="matches-list"]') {
    try {
      const container = document.querySelector(containerSelector);
      if (!container) return;

      const matches = await API.getMatches();
      const tbody = container.querySelector('tbody');
      
      if (!tbody) return;

      tbody.innerHTML = '';

      if (matches.length === 0) {
        tbody.innerHTML = `
          <tr>
            <td colspan="7" class="text-center">
              No matches found. <a href="/match-form.html">Schedule one</a>
            </td>
          </tr>
        `;
        return;
      }

      // Need to fetch team names
      const teams = await API.getTeams();
      const teamMap = {};
      teams.forEach(t => { teamMap[t.teamId] = t.teamName; });

      matches.forEach(match => {
        const row = document.createElement('tr');
        const statusClass = match.status === 'completed' ? 'badge-completed' : 'badge-scheduled';
        row.innerHTML = `
          <td>${match.matchId}</td>
          <td>${teamMap[match.team1Id] || 'Unknown'}</td>
          <td>${teamMap[match.team2Id] || 'Unknown'}</td>
          <td><span class="badge ${statusClass}">${match.status}</span></td>
          <td>${match.team1Score} - ${match.team2Score}</td>
          <td>
            ${match.status === 'scheduled' ? 
              `<button class="btn btn-sm btn-success" onclick="Render.editMatch('${match.matchId}')">Result</button>` : 
              `<span>Final</span>`
            }
          </td>
          <td>
            <button class="btn btn-sm btn-danger" onclick="Render.deleteMatch('${match.matchId}')">Delete</button>
          </td>
        `;
        tbody.appendChild(row);
      });
    } catch (error) {
      console.error('Error rendering matches list:', error);
      showError('Failed to render matches');
    }
  },

  /**
   * Delete match
   */
  async deleteMatch(matchId) {
    if (confirm('Are you sure you want to delete this match?')) {
      const success = await API.deleteMatch(matchId);
      if (success) {
        setTimeout(() => {
          Render.renderMatchesList();
        }, 1000);
      }
    }
  },

  /**
   * Render dashboard
   * Replaces: dashboard.jsp stats display
   */
  async renderDashboard(containerId = 'dashboardContainer') {
    try {
      protectAdminPage(); // Ensure user is logged in

      const container = document.getElementById(containerId);
      if (!container) return;

      const stats = await API.getDashboardStats();
      const user = getCurrentUser();

      // Render stats cards
      const statsHtml = `
        <div class="stats-section">
          <div class="stat-card stat-teams">
            <h3>Total Teams</h3>
            <p class="stat-number">${stats.totalTeams}</p>
            <a href="/team-list.html" class="card-link">View All</a>
          </div>
          <div class="stat-card stat-tournaments">
            <h3>Total Tournaments</h3>
            <p class="stat-number">${stats.totalTournaments}</p>
            <a href="/tournament-list.html" class="card-link">View All</a>
          </div>
          <div class="stat-card">
            <h3>Quick Actions</h3>
            <ul class="quick-actions">
              <li><a href="/team-form.html">+ New Team</a></li>
              <li><a href="/player-form.html">+ New Player</a></li>
              <li><a href="/match-form.html">+ Schedule Match</a></li>
              <li><a href="/tournament-form.html">+ New Tournament</a></li>
            </ul>
          </div>
        </div>
      `;

      // Render top teams table
      let topTeamsHtml = '<h2>Top 5 Teams (by Points)</h2>';
      if (stats.topTeams && stats.topTeams.length > 0) {
        topTeamsHtml += '<table class="data-table"><thead><tr><th>Rank</th><th>Team Name</th><th>Points</th><th>Wins</th><th>Losses</th></tr></thead><tbody>';
        stats.topTeams.forEach((team, index) => {
          const badge = index === 0 ? '🥇' : index === 1 ? '🥈' : index === 2 ? '🥉' : (index + 1);
          topTeamsHtml += `
            <tr>
              <td><span class="rank-badge">${badge}</span></td>
              <td><strong>${team.teamName}</strong></td>
              <td><span class="points-badge">${team.totalPoints}</span></td>
              <td>${team.wins || 0}</td>
              <td>${team.losses || 0}</td>
            </tr>
          `;
        });
        topTeamsHtml += '</tbody></table>';
      } else {
        topTeamsHtml += '<p>No teams yet. <a href="/team-form.html">Create one</a></p>';
      }

      // Navigation section
      const navHtml = `
        <div class="navigation-section">
          <h2>Navigation</h2>
          <div class="nav-grid">
            <a href="/team-list.html" class="nav-card">
              <h3>🏆 Teams</h3>
              <p>Manage teams and view details</p>
            </a>
            <a href="/player-list.html" class="nav-card">
              <h3>👥 Players</h3>
              <p>Manage player profiles</p>
            </a>
            <a href="/tournament-list.html" class="nav-card">
              <h3>🎮 Tournaments</h3>
              <p>Create and manage tournaments</p>
            </a>
            <a href="/match-list.html" class="nav-card">
              <h3>⚔️ Matches</h3>
              <p>Schedule and track matches</p>
            </a>
          </div>
        </div>
      `;

      container.innerHTML = statsHtml + topTeamsHtml + navHtml;
    } catch (error) {
      console.error('Error rendering dashboard:', error);
      showError('Failed to load dashboard');
    }
  }
};

// Initialize page rendering on DOM ready
document.addEventListener('DOMContentLoaded', function() {
  // Auto-render based on page element
  if (document.querySelector('[data-page="teams-list"]')) {
    Render.renderTeamsList();
  }
  if (document.querySelector('[data-page="players-list"]')) {
    Render.renderPlayersList();
  }
  if (document.querySelector('[data-page="tournaments-list"]')) {
    Render.renderTournamentsList();
  }
  if (document.querySelector('[data-page="matches-list"]')) {
    Render.renderMatchesList();
  }
  if (document.getElementById('dashboardContainer')) {
    Render.renderDashboard();
  }
  if (document.getElementById('teamForm')) {
    Render.renderTeamForm();
  }
  if (document.getElementById('playerForm')) {
    Render.renderPlayerForm();
  }
  if (document.getElementById('teamViewContainer')) {
    Render.renderTeamView();
  }
});
