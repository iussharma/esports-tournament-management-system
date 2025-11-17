package com.esports.web;

import java.io.IOException;
import java.util.List;

import com.esports.dao.TournamentDAO;
import com.esports.model.Tournament;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet for handling tournament operations
 */
@WebServlet(name = "TournamentServlet", urlPatterns = {"/tournament", "/tournaments"})
public class TournamentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listTournaments(request, response);
                break;
            case "form":
                showForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listTournaments(request, response);
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
                createTournament(request, response);
                break;
            case "update":
                updateTournament(request, response);
                break;
            case "delete":
                deleteTournament(request, response);
                break;
            default:
                listTournaments(request, response);
        }
    }

    private void listTournaments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tournament> tournaments = TournamentDAO.getAllTournaments();
        request.setAttribute("tournaments", tournaments);
        request.getRequestDispatcher("/WEB-INF/tournament/list.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/tournament/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tournamentId = Integer.parseInt(request.getParameter("id"));
        Tournament tournament = TournamentDAO.getTournamentById(tournamentId);
        request.setAttribute("tournament", tournament);
        request.getRequestDispatcher("/WEB-INF/tournament/form.jsp").forward(request, response);
    }

    private void createTournament(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tournamentName = request.getParameter("tournamentName");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String prizePool = request.getParameter("prizePool");

        Tournament tournament = new Tournament(tournamentName, description, startDate, endDate, prizePool);
        TournamentDAO.createTournament(tournament);
        response.sendRedirect("tournament?action=list");
    }

    private void updateTournament(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
        String tournamentName = request.getParameter("tournamentName");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String prizePool = request.getParameter("prizePool");

        Tournament tournament = TournamentDAO.getTournamentById(tournamentId);
        tournament.setTournamentName(tournamentName);
        tournament.setDescription(description);
        tournament.setStartDate(startDate);
        tournament.setEndDate(endDate);
        tournament.setPrizePool(prizePool);
        TournamentDAO.updateTournament(tournament);
        response.sendRedirect("tournament?action=list");
    }

    private void deleteTournament(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int tournamentId = Integer.parseInt(request.getParameter("id"));
        TournamentDAO.deleteTournament(tournamentId);
        response.sendRedirect("tournament?action=list");
    }
}
