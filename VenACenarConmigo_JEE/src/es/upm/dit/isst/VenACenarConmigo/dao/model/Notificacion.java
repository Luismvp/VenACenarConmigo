package es.upm.dit.isst.VenACenarConmigo.dao.model;

	import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
	@Entity
	public class Notificacion implements Serializable{
		@Id
		private int idNotificacion;
		@ManyToOne
		private Convite convite;
		@ManyToOne
		private AsistenciaConvite asistencia;
		private boolean isChecked;
		private boolean hasStarted;
		private boolean hasFinished;
		
		public boolean isHasStarted() {
			return hasStarted;
		}
		public void setHasStarted(boolean hasStarted) {
			this.hasStarted = hasStarted;
		}
		public boolean isHasFinished() {
			return hasFinished;
		}
		public void setHasFinished(boolean hasFinished) {
			this.hasFinished = hasFinished;
		}
		public int getIdNotificacion() {
			return idNotificacion;
		}
		public void setIdNotificacion(int idNotificacion) {
			this.idNotificacion = idNotificacion;
		}
		public Convite getConvite() {
			return convite;
		}
		public void setConvite(Convite convite) {
			this.convite = convite;
		}
		public AsistenciaConvite getAsistencia() {
			return asistencia;
		}
		public void setAsistencia(AsistenciaConvite asistencia) {
			this.asistencia = asistencia;
		}
		public boolean isChecked() {
			return isChecked;
		}
		public void setChecked(boolean isChecked) {
			this.isChecked = isChecked;
		}
}
