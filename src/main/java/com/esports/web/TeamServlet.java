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
 * Servlet for handling team operations (CRUD)
 */
@WebServlet(name = "TeamServlet", urlPatterns = {"/team", "/teams"})
public class TeamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listTeams(request, response);
                break;
            case "form":
                showForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "view":
                viewTeam(request, response);
                break;
            default:
                listTeams(request, response);
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
                createTeam(request, response);
                break;
            case "update":
                updateTeam(request, response);
                break;
            case "delete":
                deleteTeam(request, response);
                break;
            default:
                listTeams(request, response);
        }
    }

    private void listTeams(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Team> teams = TeamDAO.getAllTeams();
        request.setAttribute("teams", teams);
        request.getRequestDispatcher("/WEB-INF/team/list.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/team/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = Integer.parseInt(request.getParameter("id"));
        Team team = TeamDAO.getTeamById(teamId);
        request.setAttribute("team", team);
        request.getRequestDispatcher("/WEB-INF/team/form.jsp").forward(request, response);
    }

    private void createTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String teamName = request.getParameter("teamName");
        String description = request.getParameter("description");
        Team team = new Team(teamName, description);
        TeamDAO.createTeam(team);
        response.sendRedirect("team?action=list");
    }

    private void updateTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int teamId = Integer.parseInt(request.getParameter("teamId"));
        String teamName = request.getParameter("teamName");
        String description = request.getParameter("description");
        Team team = TeamDAO.getTeamById(teamId);
        team.setTeamName(teamName);
        team.setDescription(description);
        TeamDAO.updateTeam(team);
        response.sendRedirect("team?action=list");
    }

    private void deleteTeam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int teamId = Integer.parseInt(request.getParameter("id"));
        TeamDAO.deleteTeam(teamId);
        response.sendRedirect("team?action=list");
    }

    private void viewTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teamId = Integer.parseInt(request.getParameter("id"));
        Team team = TeamDAO.getTeamById(teamId);
        List<Player> players = PlayerDAO.getPlayersByTeamId(teamId);
        request.setAttribute("team", team);
        request.setAttribute("players", players);
        request.getRequestDispatcher("/WEB-INF/team/view.jsp").forward(request, response);
    }
}
