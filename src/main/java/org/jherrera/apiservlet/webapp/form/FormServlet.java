package org.jherrera.apiservlet.webapp.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

@WebServlet("/Servlet")
public class FormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Connection conn = null;
            ResultSet resultado = null;


            String titulo = "Esto es una Prueba de TITULO";
            String mensaje = "Esto es una Prueba de MENSAJE";

            String cabeceraHTML = "<HTML>"+
                    "<META NAME=\"title\" CONTENT=\""+titulo+", examen de Servlets Java y JDBC\">"+
                    "<meta name=\"author\" content=\"Javier Herrera\">"+
                    "<HEAD>"+
                    "<TITLE>"+titulo+"</TITLE>"+
                    "<STYLE>"+
                    "h3 { font-size: 12pt; font-family: Arial; color: white; background-color:#FFFFFF ; line-height: 134%; "+
                    "text-align: justify; margin-top:6; margin-bottom:6; margin-left:6; margin-right:6}"+
                    "h5 { color: #42628e; text-align: left; text-indent:0px; margin-top:0; margin-right: 10; margin-left: 10;"+
                    "margin-bottom:0 ;background-color: white; font-size:12pt; font-family: Courier}"+
                    "h4 { font-size: 14pt; font-family: Arial; color: white; background-color:#42628e ; line-height: 134%; "+
                    "text-align: center; margin-top:6; margin-bottom:6; margin-left:6; margin-right:6}"+
                    "p { text-align: justify; margin-top:6; margin-bottom:6; margin-left:10; margin-right:10}"+
                    "h1 { color: white; text-align: center; background-color: #42628e; font-size: 20pt; }"+
                    "h2 { color: white; text-align: center; background-color: #42628e; font-size: 20pt; font-family: Courier}"+
                    "A.sub { text-decoration:underline; }"+
                    "A { text-decoration:none; }"+
                    "</STYLE>"+
                    "</HEAD>"+
                    "<BODY bgColor=\"#FFFFFF\" text=\"#000000\" link=\"#004466\" vlink=\"#888888\" >";

            String tituloHTML = "<!-------------------------------------------------------------------->"+
                    "<!-- TITULO -->"+
                    "<!-------------------------------------------------------------------->"+
                    "<h1><br/>"+mensaje+"<br/>&nbsp;</h1>";


            StringBuilder tablaAsignaturas = new StringBuilder("<h5><br/>" +
                    "<center><table border=\"1\" cellpadding=\"6\" style=\" font-size: 10pt; font-family: Courier; color: white; background-color:#547cb4 ; line-height: 134%; text-align: justify; margin-top:6; margin-bottom:6; margin-left:20; margin-right:20\">" +
                    "<tr><th>Nombre</th><th>Materia</th><th>Correlatividad</th><th>Año</th><th>Obligatorio</th></tr>");


            String url = "jdbc:mysql://localhost:3306/java_evaluacion";
            String username = "root";
            String password = "sasa";
            String sql = "SELECT nombre, materia, correlatividad, anio, Obligatorio FROM java_evaluacion.asignaturas";


            try{
            /*
                PreparedStatement stmt = getConnection(url, username, password).prepareStatement(sql);

                try{
                    resultado = stmt.executeQuery(sql);
                    stmt.close();
                }catch (SQLException e){
                    out.println("Error al crear la consulta: " + e.getMessage());
                    System.out.println("Error al crear la consulta: " + e.getMessage());
                    return;
                }

             */
                resultado = null;
                while (resultado.next()) {
                    tablaAsignaturas.append("<tr>");
                    tablaAsignaturas.append("<td>").append(resultado.getString("Nombre")).append("</td>");
                    tablaAsignaturas.append("<td>").append(resultado.getString("Materia")).append("</td>");
                    tablaAsignaturas.append("<td>").append(resultado.getString("Correlatividad")).append("</td>");
                    tablaAsignaturas.append("<td>").append(resultado.getString("Anio")).append("</td>");
                    tablaAsignaturas.append("<td>").append(resultado.getString("Obligatorio")).append("</td>");
                    tablaAsignaturas.append("</tr>");
                }

            } catch (SQLException e) {
                System.out.println("Error de Conexion a BD" + e.getMessage());
                throw new RuntimeException(e);
            } finally {
                try {
                    resultado.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            tablaAsignaturas.append("</table></center><br/></h5></HTML>");

            String pieHTML = "<HTML>"+
                    "<br/>"+
                    "<br/>"+
                    "<!-------------------------------------------------------------------->"+
                    "<!--Franja de Abajo -->"+
                    "<!-------------------------------------------------------------------->"+
                    "<div id= \"nifty3 \">"+
                    "<table width= \"100% \" bgcolor= \"#42628e \">"+
                    "<tr><td wifth= \"20% \">&nbsp;</td><td width= \"60% \">"+
                    "<p align= \"center \" ><h2 style= \"background-color:#FFFFFF \"><a href= \"(colocar tu mail) \"> </a></h2></p>"+
                    "</td><td wifth= \"20% \">&nbsp;</td></tr>"+
                    "</table>"+
                    "</div>"+

                    "<!--Fin Tabla principal-->"+
                    "</TD>"+
                    "</TR>"+
                    "</TABLE>"+
                    "</BODY>"+
                    "</HTML>";

            out.println("<!DOCTYPE html>" + cabeceraHTML + tituloHTML + tablaAsignaturas + pieHTML);


            //out.println(tituloHTML + tablaAsignaturas + pieHTML);


            /*

            String metodoHttp = request.getMethod();
            String requestUri = request.getRequestURI();
            String requestUrl = request.getRequestURL().toString();
            String contexPath = request.getContextPath();
            String servletPath = request.getServletPath();
            String ipCliente = request.getRemoteAddr();
            String ip = request.getLocalAddr();
            int port = request.getLocalPort();
            String scheme = request.getScheme();
            String host = request.getHeader("host");
            String url = scheme + "://" + host + contexPath + servletPath;
            String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath;



            out.println("<ul>");
            out.println("<li>metodo http: " + metodoHttp + "</li>");
            out.println("<li>request uri: " + requestUri + "</li>");
            out.println("<li>request url: " + requestUrl + "</li>");
            out.println("<li>context path: " + contexPath + "</li>");
            out.println("<li>servlet path: " + servletPath + "</li>");
            out.println("<li>ip local: " + ip + "</li>");
            out.println("<li>ip cliente: " + ipCliente + "</li>");
            out.println("<li>puerto local: " + port + "</li>");
            out.println("<li>scheme: " + scheme + "</li>");
            out.println("<li>host: " + host + "</li>");
            out.println("<li>url: " + url + "</li>");
            out.println("<li>url2: " + url2 + "</li>");
             */


        }
    }
}