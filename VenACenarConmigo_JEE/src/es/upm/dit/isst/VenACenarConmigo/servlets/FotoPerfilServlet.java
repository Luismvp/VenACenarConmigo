package es.upm.dit.isst.VenACenarConmigo.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.upm.dit.isst.VenACenarConmigo.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.VenACenarConmigo.dao.model.Usuario;

@MultipartConfig
@WebServlet("/FotoPerfilServlet")
public class FotoPerfilServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part filePart = req.getPart("file");
		String fileName = extractFileName(filePart);
		String savePath = "/home/isst/eclipse-workspace/VenACenarConmigo/WebContent/images" + File.separator + fileName;
		File fileSaveDir = new File(savePath);
		filePart.write(savePath+File.separator);
		String email = (String) req.getSession().getAttribute("email");
		Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(email);
		usuario.setNombreFoto(fileName);
		UsuarioDAOImplementation.getInstance().updateUsuario(usuario);
		req.getSession().setAttribute("usuario", usuario);
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
