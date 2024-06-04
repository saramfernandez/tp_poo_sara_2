package Buquealtoque;
import java.util.ArrayList;
import java.util.List;

public class Persona {
	    private String email;
	    private String nombre;
	    private String apellido;
	    private String dni;
	    private String password;
	    private String tipo;
		private boolean premium;

	    // Constructor
	    public Persona(String email, String nombre, String apellido, String dni, String password, String tipo, boolean premium) {
	        this.email = email;
	        this.nombre = nombre;
	        this.apellido = apellido;
	        this.dni = dni;
	        this.password = password;
	        this.tipo = tipo;
			this.premium = premium;
	    }

	    // Getters
	    public String getEmail() {
	        return email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public String getApellido() {
	        return apellido;
	    }

	    public String getTipo() {
	        return tipo;
	    }
	    public String getDNI() {
	    	return dni;
	    }

		public boolean getPremium() {
			return premium;
		}

		//Setters
		public void setPremium(boolean premium) {
			this.premium = premium;
		}

	    // Lista de usuarios ya cargados de ejemplo o como BBDD
	    private static List<Persona> usuarios = new ArrayList<>();

	    static {
	        usuarios.add(new Persona("omarbrondo@gmail.com", "Omar", "Brondo", "31895218", "felicitas", "Cliente",false));
	        usuarios.add(new Persona("barby@outlook.com", "Barby", "Carrizo", "33037264", "gordo", "Empleado",false));
			usuarios.add(new Persona("saramarielf@gmail.com", "Sara", "Fernandez", "40463702", "1234", "Cliente",false));
			usuarios.add(new Persona("admin", "admin", "admin", "1234", "admin", "Administrador",false));
			usuarios.add(new Persona("ADMIN", "ADMIN", "admin", "1234", "ADMIN", "Administrador",false));
			usuarios.add(new Persona("jperez@buquealtoque.com", "Juan", "Perez", "38456123", "1234", "Empleado",false));
			usuarios.add(new Persona("ptorres@buquealtoque.com", "Pedro", "Torres", "25656556", "1234", "Soporte",false));
	        //Se pueden agregar mas usuarios en esta parte
	    }

	    // Método para validar usuario
	    public static Persona validarUsuario(String email, String password) {
	        for (Persona usuario : usuarios) {
	            if (usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
	                return usuario;
	            }
	        }
	        return null;
	    }

	    // Método para agregar un nuevo usuario
	    public static void agregarUsuario(String email, String nombre, String apellido, String dni, String password, String tipo, boolean premium) {
	        usuarios.add(new Persona(email, nombre, apellido, dni, password, tipo,premium));
	    }
		
	}

