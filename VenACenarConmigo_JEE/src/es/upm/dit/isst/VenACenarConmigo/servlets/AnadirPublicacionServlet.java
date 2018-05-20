package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.File;
import java.io.IOException;
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
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        	req.setCharacterEncoding("UTF-8");
        	Publicaciones publicacion = new Publicaciones();
        		Part filePart = req.getPart("file");
        		if (filePart.getSize()!=0) {
                	//filePart = req.getPart("file");
                	String fileName = extractFileName(filePart);
                	log(fileName);
            		//Direccion de las imágenes del proyecto (cambiar para la foto de perfil)
            		//String savePath = "/home/isst/eclipse-workspace/VenACenarConmigo/WebContent/imagen_public" + File.separator + fileName;
            		
                	//Si usas la máquina virtual, comenta esta linea y descomenta la de arriba
                	String savePath = "/home/pegaso/ISST/VenACenarConmigo/VenACenarConmigo_JEE/WebContent/imagen_public/" +"" + File.separator + fileName;
            		File fileSaveDir = new File(savePath);
            		filePart.write(savePath+File.separator);
            		publicacion.setAdjunto(fileName);
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
                log(idNuevaPublicacion+"");
                PublicacionesDAOImplementation.getInstance().createPublicaciones(publicacion);                
                List<Publicaciones> publicacionesUsuario = new ArrayList<>();
                publicacionesUsuario.add(publicacion);
                
                for(int i = publicaciones.size()-1; i>= 0; i--) {
                	if (publicaciones.get(i).getUsuario().getEmail().equals(usuario.getEmail())) {
                		publicacionesUsuario.add(publicaciones.get(i));
                	} 	
                }
                
                req.getSession().setAttribute("lista_publicaciones_usuario", publicacionesUsuario);
                resp.sendRedirect(req.getContextPath() + "/Perfil.jsp");     
        }
        private String extractFileName(Part part) {
    		String contentDisp = part.getHeader("content-disposition");
    		String[] items = contentDisp.split(";");
    		for (String s:items) {
    			if(s.trim().startsWith("filename")) {
    				return s.substring(s.indexOf("=")+2, s.length()-1);
    			}
    		}
    		return "";
    	}
}
