/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.banik.registrations.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sk.banik.registrations.entities.Registration;
import sk.banik.registrations.entities.RegistrationPK;
import sk.banik.registrations.session.RegistrationFacade;

/**
 *
 * @author majky
 */
@WebServlet(name = "RegistrationsServlet",
        urlPatterns = {"/registrations",
            "/edit_reg",
            "/save_reg",
            "/add_reg"})
public class RegistrationsServlet extends HttpServlet {

    @EJB
    private RegistrationFacade regFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userPath = request.getServletPath();

        if (userPath.equals("/registrations")) {
            List<Registration> registrations = null;

            String agentIdString = request.getParameter("agent");
            String managerIdString = request.getParameter("manager");
            if (agentIdString != null) {
                short agentId = Short.parseShort(agentIdString);

                registrations = regFacade.findAllByAgent(agentId);
            } else if (managerIdString != null) {
                short managerId = Short.parseShort(managerIdString);

                registrations = regFacade.findAllByManager(managerId);
            } else {
                // load registrations by parameters
                registrations = regFacade.findAll();
            }

            // set property for registrations view
            if (registrations != null && registrations.size() > 0) {
                request.setAttribute("registrations", registrations);
            }

            // forward to registrations view
            request.getRequestDispatcher("/WEB-INF/view/view_regs.jsp").forward(request, response);
        } else if (userPath.equals("/edit_reg")) {
            try {
                String icoString = request.getParameter("ico");
                String regDateString = request.getParameter("reg_date");

                DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy-hh:mm:ss");
                Date regDate = (Date) formatter.parse(regDateString);

                Registration foundReg = regFacade.find(new RegistrationPK(icoString, regDate));

                request.setAttribute("registration", foundReg);

                request.getRequestDispatcher("/WEB-INF/view/edit_reg.jsp").forward(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(RegistrationsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("");
        } else if (userPath.equals("/save_reg")) {
            try {
                String icoString = request.getParameter("icoOrigin");
                String regDateString = request.getParameter("regDateOrigin");

                DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy-hh:mm:ss");
                Date regDate = (Date) formatter.parse(regDateString);

                Registration foundReg = regFacade.find(new RegistrationPK(icoString, regDate));

                String companyName = request.getParameter("companyName");

                foundReg.setCompanyName(companyName);

                regFacade.edit(foundReg);

                List<Registration> regs = new LinkedList<>();
                regs.add(foundReg);

                request.setAttribute("registrations", regs);

            } catch (ParseException ex) {
                Logger.getLogger(RegistrationsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("/WEB-INF/view/view_regs.jsp").forward(request, response);
        } else if (userPath.equals("/add_reg")) {
            

            request.getRequestDispatcher("/WEB-INF/view/view_regs.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
