
public class Usuarios {

	String usuario;
	String contraseña;
	
	public Usuarios(String usuario, String contraseña) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "Usuarios [usuario=" + usuario + ", contraseña=" + contraseña + "]";
	}
	
	
}
