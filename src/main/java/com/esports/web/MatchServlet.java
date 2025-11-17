package com.esports.web;

import java.io.IOException;
import java.util.List;

import com.esports.dao.MatchDAO;
import com.esports.dao.TournamentDAO;
import com.esports.model.Match;
import com.esports.model.Tournament;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet for handling match operations
 */
@WebServlet(name = "MatchServlet", urlPatterns = {"/match", "/matches"})
public class MatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listMatches(request, response);
                break;
            case "form":
                showForm(request, response);
                break;
            case "schedule":
                scheduleMatch(request, response);
                break;
            case "submitResult":
                showResultForm(request, response);
                break;
            default:
                listMatches(request, response);
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
                createMatch(request, response);
                break;
            case "updateResult":
                updateMatchResult(request, response);
                break;
            default:
                listMatches(request, response);
        }
    }

    private void listMatches(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Match> matches = MatchDAO.getAllMatches();
        request.setAttribute("matches", matches);
        request.getRequestDispatcher("/WEB-INF/match/list.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Tournament> tournaments = TournamentDAO.getAllTournaments();
        request.setAttribute("tournaments", tournaments);
        request.getRequestDispatcher("/WEB-INF/match/form.jsp").forward(request, response);
    }

    private void scheduleMatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showForm(request, response);
    }

    private void showResultForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matchId = Integer.parseInt(request.getParameter("id"));
        Match match = MatchDAO.getMatchById(matchId);
        request.setAttribute("match", match);
        request.getRequestDispatcher("/WEB-INF/match/result.jsp").forward(request, response);
    }

    private void createMatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int tournamentId = Integer.parseInt(request.getParameter("tournamentId"));
        int team1Id = Integer.parseInt(request.getParameter("team1Id"));
        int team2Id = Integer.parseInt(request.getParameter("team2Id"));
        String matchDate = request.getParameter("matchDate");

        Match match = new Match(tournamentId, team1Id, team2Id, matchDate);
        MatchDAO.createMatch(match);
        response.sendRedirect("match?action=list");
    }

    private void updateMatchResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int matchId = Integer.parseInt(request.getParameter("matchId"));
        int team1Points = Integer.parseInt(request.getParameter("team1Points"));
        int team2Points = Integer.parseInt(request.getParameter("team2Points"));

        Match match = MatchDAO.getMatchById(matchId);
        match.setTeam1Points(team1Points);
        match.setTeam2Points(team2Points);
        match.setStatus("completed");

        // Determine winner
        String winnerId;
        if (team1Points > team2Points) {
            winnerId = String.valueOf(match.getTeam1Id());
        } else if (team2Points > team1Points) {
            winnerId = String.valueOf(match.getTeam2Id());
        } else {
            winnerId = "0"; // Draw
        }
        match.setWinnerId(winnerId);

        MatchDAO.updateMatch(match);
        response.sendRedirect("match?action=list");
    }
}
