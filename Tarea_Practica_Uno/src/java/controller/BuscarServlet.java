package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Data;
import model.Jugador;

/**
 *
 * @author ricar
 */
@WebServlet(name = "BuscarServlet", urlPatterns = {"/buscar.do"})
public class BuscarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            // Rescata el filtro del contenido del txtBuscar
//            
//            String filtro = request.getParameter("filtro");
//            Data d = new Data();
//            //Genera la tabla con el filtro
//            out.println("<table id='tablaServlet'>");
//            out.println("<tr>");
//            out.println("<td>ID</td>");
//            out.println("<td>Nombre</td>");
//            out.println("<td>País</td>");
//            out.println("<td>Posición</td>");
//            out.println("<td>Dorsal</td>");
//            out.println("<td>Eliminar</td>");
//            out.println("<td>Actualizar</td>");
//            out.println("</tr>");
//
//            try {
//                for (Jugador j : d.getJugadores(filtro)) {
//                    out.println("<tr>");
//                    out.println("<td>" + j.getId() + "</td>");
//                    out.println("<td>" + j.getNombre() + "</td>");
//                    out.println("<td>" + j.getPais() + "</td>");
//                    out.println("<td>" + j.getPosicion() + "</td>");
//                    out.println("<td>" + j.getDorsal() + "</td>");
//
//                    out.println("<td colspan='1'>");
//                    out.println("<form action='eliminar.do' method='GET'>");
//                    out.println("<input type='hidden' value='" + j.getId() + "' name='id'>");
//                    out.println("<input type='submit' value='Eliminar'>");
//                    out.println("</form>");
//                    out.println("</td>");
//
//                    out.println("<td colspan='1'>");
//                    out.println("<form action='actualizar.do' method='GET'>");
//                    out.println("<input type='hidden' value='" + j.getId() + "' name='id'>");
//                    out.println("<input type='submit' value='Actualizar'>");
//                    out.println("</form>");
//                    out.println("</td>");
//
//                    out.println("</tr>");
//                    out.println("</table>");
//                }
//
//                out.println("<a href='index.jsp'>Volver</a>");
//            } catch (SQLException ex) {
//                Logger.getLogger(BuscarServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
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
