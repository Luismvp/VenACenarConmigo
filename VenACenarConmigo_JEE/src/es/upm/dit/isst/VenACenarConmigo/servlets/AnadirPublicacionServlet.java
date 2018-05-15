package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.upm.dit.isst.VenACenarConmigo.dao.PublicacionesDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Publicaciones;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@MultipartConfig
@WebServlet("/AnadirPublicacionServlet")
public class AnadirPublicacionServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        		Publicaciones publicacion = new Publicaciones();
        		if (req.getPart("file")!= null) {
                	Part filePart = req.getPart("file");
                    InputStream fileContent = filePart.getInputStream();
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    byte[] buffer = new byte[10240];
                    for (int length = 0; (length = fileContent.read(buffer)) > 0;) output.write(buffer, 0, length);
                    publicacion.setAdjunto(output.toByteArray());
        		}
                String email = (String) req.getSession().getAttribute("email");
                Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);
                String comentario = req.getParameter("comentario");    
                List<Publicaciones> publicaciones = PublicacionesDAOImplementation.getInstance().readAllPublicaciones();
                publicacion.setTexto(comentario);
                publicacion.setUsuario(usuario);
                
                Calendar fecha = Calendar.getInstance();
                publicacion.setFecha(fecha);
                
                int idNuevaPublicacion = 1;
                if(publicaciones.size()!=0) {
                	idNuevaPublicacion=publicaciones.size()+1;
                }
                publicacion.setiDPublicacion(idNuevaPublicacion);
                PublicacionesDAOImplementation.getInstance().createPublicaciones(publicacion);                
                List<Publicaciones> publicacionesUsuario = new ArrayList<>();
                publicacionesUsuario.add(publicacion);
                for(int i = publicaciones.size()-1; i>= 0; i--) {
                	if (publicaciones.get(i).getUsuario().getEmail() == usuario.getEmail()) {
                		publicacionesUsuario.add(publicaciones.get(i));
                	} 	
                }
                
                req.getSession().setAttribute("lista_publicaciones_usuario", publicacionesUsuario);
                resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");     
        }
}
