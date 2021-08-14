package br.hilab.servlet;

import br.hilab.business.CategoriaFacade;
import br.hilab.business.UsuarioFacade;
import br.hilab.dao.UsuarioDAO;
import br.hilab.domain.Categoria;
import br.hilab.domain.Usuario;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        
        String action = request.getParameter("action");
        if(action == null) action = "";
        
        Usuario usuario = new Usuario();
        Categoria categoria = new Categoria();
        String email;
        switch (action) {
            case "formNew":
                request.setAttribute("categorias", CategoriaFacade.buscarTodos());
                
                rd = getServletContext().getRequestDispatcher("/manterUsuario.jsp?action=formNew");
                rd.forward(request, response);
                
                break;
            case "new":
                usuario.setNome(request.getParameter("nome"));
                usuario.setDataNasc(LocalDate.parse(request.getParameter("dataNasc")));
                categoria.setId(Integer.parseInt(request.getParameter("categoria")));
                usuario.setCategoria(categoria);
                
                email = request.getParameter("email");
                usuario.setEmail(email);
                if(email.matches("^(.+)@(.+)(.com|.br)$")){
                    UsuarioFacade.inserir(usuario);
                
                    request.setAttribute("usuarios", UsuarioFacade.buscarTodos());
                    
                    rd = getServletContext().getRequestDispatcher("/listarUsuarios.jsp");
                    rd.forward(request, response);

                    break;
                } else {
                    request.setAttribute("usuario", usuario);
                    request.setAttribute("categorias", CategoriaFacade.buscarTodos());
                    request.setAttribute("msg", "Email inserido incorreto");
                    
                    rd = getServletContext().getRequestDispatcher("/manterUsuario.jsp?action=formNew");
                    rd.forward(request, response);
                    
                    break;
                }
            case "formUpdate":
                usuario.setId(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("usuario", UsuarioFacade.buscar(usuario));
                request.setAttribute("categorias", CategoriaFacade.buscarTodos());
                
                rd = getServletContext().getRequestDispatcher("/manterUsuario.jsp?action=formUpdate");
                rd.forward(request, response);
                
                break;
            case "update":
                usuario.setId(Integer.parseInt(request.getParameter("id")));
                usuario.setNome(request.getParameter("nome"));
                usuario.setDataNasc(LocalDate.parse(request.getParameter("dataNasc")));
                categoria.setId(Integer.parseInt(request.getParameter("categoria")));
                usuario.setCategoria(categoria);
                
                email = request.getParameter("email");
                usuario.setEmail(email);
                if(email.matches("^(.+)@(.+)(.com|.br)$")){
                    UsuarioFacade.editar(usuario);
                
                    request.setAttribute("usuarios", UsuarioFacade.buscarTodos());
                    
                    rd = getServletContext().getRequestDispatcher("/listarUsuarios.jsp");
                    rd.forward(request, response);

                    break;
                } else {
                    request.setAttribute("usuario", usuario);
                    request.setAttribute("categorias", CategoriaFacade.buscarTodos());
                    request.setAttribute("msg", "Email inserido incorreto");
                    
                    rd = getServletContext().getRequestDispatcher("/manterUsuario.jsp?action=formUpdate");
                    rd.forward(request, response);
                    
                    break;
                }
            case "remove":
                usuario.setId(Integer.parseInt(request.getParameter("id")));
                UsuarioFacade.remover(usuario);
                
                //Sem break para que o código default seja executado logo em seguida
            default:
                request.setAttribute("usuarios", UsuarioFacade.buscarTodos());
                
                rd = getServletContext().getRequestDispatcher("/listarUsuarios.jsp");
                rd.forward(request, response);
                
                break;
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
