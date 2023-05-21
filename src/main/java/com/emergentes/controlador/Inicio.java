package com.emergentes.controlador;

import com.emergentes.dao.SeminarioDAOimpl;
import com.emergentes.modelo.Seminario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.emergentes.dao.SeminarioDAO;

@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id;
            Seminario seminario = new Seminario();
            SeminarioDAO dao = new SeminarioDAOimpl();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("seminario", seminario);
                    request.getRequestDispatcher("frmseminario.jsp").forward(request, response);
                    break;

                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    seminario = dao.getById(id);
                    request.setAttribute("aviso", seminario);
                    request.getRequestDispatcher("frmseminario.jsp").forward(request, response);
                    break;

                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("Inicio");
                    break;

                    /*caso terminado*/
                case "view":
                    List<Seminario> lista = dao.getAll();
                    request.setAttribute("seminarios", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;

                default:
                    break;

            }

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            SeminarioDAO dao = new SeminarioDAOimpl();

            int id = Integer.parseInt(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            String expositor = request.getParameter("expositor");
            String fecha = request.getParameter("fecha");
            String hora = request.getParameter("hora");
            int cupo = Integer.parseInt(request.getParameter("cupo"));

            Seminario seminario = new Seminario();
            seminario.setId(id);
            seminario.setTitulo(titulo);
            seminario.setExpositor(expositor);
            seminario.setFecha(fecha);
            seminario.setHora(hora);
            seminario.setCupo(cupo);

            if (id == 0) {
                dao.insert(seminario);
            } else {
                dao.update(seminario);
            }

        } catch (Exception e) {
            System.out.println("ERROR AL GUARDAR datos..." + e.getMessage());
        }

        response.sendRedirect("Inicio");

    }

}
