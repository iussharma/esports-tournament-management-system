package com.esports.web;

import java.io.IOException;
import java.util.List;

import com.esports.dao.PlayerDAO;
import com.esports.dao.TeamDAO;
import com.esports.model.Player;
import com.esports.model.Team;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet for handling player operations (CRUD)
 */
@WebServlet(name = "PlayerServlet", urlPatterns = {"/player", "/players"})
public class PlayerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listPlayers(request, response);
                break;
            case "form":
                showForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listPlayers(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "create";
        }

        switch (action) {
            case "create":
                createPlayer(request, response);
                break;
            case "update":
                updatePlayer(request, response);
                break;
            case "delete":
                deletePlayer(request, response);
                break;
            default:
                listPlayers(request, response);
        }
    }

    private void listPlayers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Player> players = PlayerDAO.getAllPlayers();
        request.setAttribute("players", players);
        request.getRequestDispatcher("/WEB-INF/player/list.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Team> teams = TeamDAO.getAllTeams();
        request.setAttribute("teams", teams);
        request.getRequestDispatcher("/WEB-INF/player/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("id"));
        Player player = PlayerDAO.getPlayerById(playerId);
        List<Team> teams = TeamDAO.getAllTeams();
        request.setAttribute("player", player);
        request.setAttribute("teams", teams);
        request.getRequestDispatcher("/WEB-INF/player/form.jsp").forward(request, response);
    }

    private void createPlayer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String playerName = request.getParameter("playerName");
        String role = request.getParameter("role");
        int teamId = Integer.parseInt(request.getParameter("teamId"));
        String skill = request.getParameter("skill");

        Player player = new Player(playerName, role, teamId, skill);
        PlayerDAO.createPlayer(player);
        response.sendRedirect("player?action=list");
    }

    private void updatePlayer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int playerId = Integer.parseInt(request.getParameter("playerId"));
        String playerName = request.getParameter("playerName");
        String role = request.getParameter("role");
        int teamId = Integer.parseInt(request.getParameter("teamId"));
        String skill = request.getParameter("skill");

        Player player = PlayerDAO.getPlayerById(playerId);
        player.setPlayerName(playerName);
        player.setRole(role);
        player.setTeamId(teamId);
        player.setSkill(skill);
        PlayerDAO.updatePlayer(player);
        response.sendRedirect("player?action=list");
    }

    private void deletePlayer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int playerId = Integer.parseInt(request.getParameter("id"));
        PlayerDAO.deletePlayer(playerId);
        response.sendRedirect("player?action=list");
    }
}
