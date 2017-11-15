<%-- 
    Document   : index
    Created on : 18-10-2017, 0:32:15
    Author     : ricar
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Posicion"%>
<%@page import="java.util.List"%>
<%@page import="model.Pais"%>
<%@page import="model.Jugador"%>
<%@page import="model.Data"%>
<%@page import="java.sql.*"%>
<%@page import="model.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css">
        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>
        <script src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript">
            //Importa la tabla desde la libreria de bootstrap
            $(document).ready(function () {
                $('#unico').DataTable();
                //$('#tablaServlet').DataTable();
            });
        </script>
        <title>Sistema de Jugadores</title>
    </head>
    <body>
        <%
            Data d = new Data();
        %>
        <h1>Crear Jugador</h1>
        <script>
            function buscar() {
                // Función buscar desde la libreria jquery
                var txtFiltro = $("#txtBuscar").val();

                $.ajax({

                    url: "buscar.do",
                    data: {
                        filtro: txtFiltro
                    },
                    success: function (result) {
                        $("#unico").html(result);
                    }
                });
            }
        </script>
        <!-- Tabla General -->
        <table id="unico" class="table table-striped table-bordered dt-responsive nowrap" border="1">
            <tr>
                <td>
                    <!-- Form Crear -->
                    <form id="formBuscar" action="index.jsp" method="POST">
                </td>
                <td>
                    <input type="text" name="txtBuscar" placeholder="Buscar: " />
                    <!-- El boton obtiene la función buscar -->

                    <input type="submit"  value="Buscar" name="btnBuscar"/>
                </td>
                </form>
            </tr>
            <br>
            <!-- Dentro de la misma tabla generar el formulario crear -->
            <tr>
                <td>
                    <form action="crear.do" method="POST">
                </td>
                <td>
                    <input type="text" name="txtId" placeholder="ID" readonly />
                </td>
                <td>
                    <input type="text" name="txtNombre" placeholder="Nombre: " required/>
                </td>
                <td>
                    <select name="cboPais">
                        <%
                            // Llena el combo con los valores de la bd
                            List<Pais> pa = d.getPaises();

                            for (Pais country : pa) {
                                out.println("<option value='" + country.getId() + "'>" + country.getNombre() + "</option>");
                            }
                        %>
                    </select>
                </td>
                <td>
                    <select name="cboPosicion">
                        <%
                            // Llena el combo con los valores de la tabla posicion desde la bd
                            List<Posicion> pos = d.getPosiciones();

                            for (Posicion posicion : pos) {
                                out.println("<option value='" + posicion.getId() + "'>" + posicion.getNombre() + "</option>");
                            }
                        %>
                    </select>
                </td>
                <td>
                    <input type="number" name="txtDorsal" placeholder="Dorsal: " />
                </td>
                <td>
                    <input type="submit" name="btnCrear" value="Crear" />
                </td>
                </form>
            </tr>
            <br>
            <tr>
                <td>ID</td>
                <td>Nombre</td>
                <td>País</td>
                <td>Posición</td>
                <td>Dorsal</td>
                <td>Eliminar</td>
                <td>Actualizar</td>
            </tr>
            <%
                String filtro = request.getParameter("txtBuscar");
                List<Jugador> jugadores = null;
                if (request.getParameter("btnBuscar") != null) {
                    jugadores = d.getJugadores(filtro);
                } else {
                    jugadores = d.getTodosLosJugadores();
                }

                int auxId = 0;
                if (request.getParameter("btnActualizar") != null) {
                    auxId = Integer.parseInt(request.getParameter("idActualizar"));
                    //out.println("<td>" + auxId + "</td>");
                }

                //Muestra toda la tabla con todos los jugadores
                for (Jugador j : jugadores) {
                    if (auxId != j.getId()) {
                        out.println("<tr>");
                        out.println("<td>" + j.getId() + "</td>");
                        out.println("<td>" + j.getNombre() + "</td>");
                        out.println("<td>" + j.getPais() + "</td>");
                        out.println("<td>" + j.getPosicion() + "</td>");
                        out.println("<td>" + j.getDorsal() + "</td>");
                        // Boton Eliminar
                        out.println("<td colspan='1'>");
                        out.println("<form action='eliminar.do' method='GET'>");
                        out.println("<input type='hidden' value='" + j.getId() + "' name='id'>");
                        out.println("<input type='submit' name='btnEliminar' value='Eliminar'>");
                        out.println("</form>");
                        out.println("</td>");
                        // Boton Actualizar
                        out.println("<td colspan='1'>");
                        out.println("<form action='index.jsp' method='POST'>");
                        out.println("<input type='hidden' value='" + j.getId() + "' name='idActualizar'>");
                        out.println("<input type='submit' name='btnActualizar' value='Actualizar'>");
                        out.println("</form>");
                        out.println("</td>");

                        out.println("</tr>");
                    } else {
                        out.println("<tr>");
                        out.println("<form action='actualizar.do' method='POST'>");

                        out.println("<td>");
                        out.println("<input type='hidden' name='txtIdAct' value='" + j.getId() + "'>");
                        out.println("</td>");

                        out.println("<td>");
                        out.println("<input type='text' name='txtNombreAct' value='" + j.getNombre() + "'>");
                        out.println("</td>");

                        out.println("<td>");
                        out.println("<select name='cboPaisAct'>");
                        for (Pais state : d.getPaises()) {
                            out.print("<option value='" + state.getId() + "'>" + state.getNombre() + "</option>");
                        }
                        out.println("</select>");
                        out.println("</td>");

                        out.println("<td>");
                        out.println("<select name='cboPosicionAct'>");
                        for (Posicion auxPo : d.getPosiciones()) {
                            out.print("<option value='" + auxPo.getId() + "'>" + auxPo.getNombre() + "</option>");
                        }
                        out.println("</select>");
                        out.println("</td>");

                        out.println("<td>");
                        out.println("<input type='text' name='txtDorsalAct' value='" + j.getDorsal() + "'>");
                        out.println("</td>");

                        // Boton Guardar dentro de la celda
                        out.println("<td>");
                        out.println("<input type='submit' name='btnGuardar' value='Guardar'>");
                        out.println("</form>");
                        out.println("</td>");

                        //cancelar
                        out.println("<td>");
                        out.println("<a href='index.jsp'>Cancelar</a>");
                        out.println("</td>");

                        out.println("</tr>");
                    }

                }

            %>

        </table>
        <br>
        <h4>
            <%         
                out.println("Existen: " + d.getTodosLosJugadores().size() + " Jugadores");
            %>
        </h4>
    </body>

</html>
