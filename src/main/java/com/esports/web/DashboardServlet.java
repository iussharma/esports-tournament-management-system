package com.esports.web;

import java.io.IOException;
import java.util.List;

import com.esports.dao.TeamDAO;
import com.esports.dao.TournamentDAO;
import com.esports.model.Team;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int totalTeams = 0;
        int totalTournaments = 0;
        try {
            totalTeams = TeamDAO.getAllTeams().size();
            totalTournaments = TournamentDAO.getCount();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Team> topTeams = TeamDAO.getTopTeams(5);

        request.setAttribute("totalTeams", totalTeams);
        request.setAttribute("totalTournaments", totalTournaments);
        request.setAttribute("topTeams", topTeams);

        request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
    }
}
