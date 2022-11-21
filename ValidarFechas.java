package ejercicioFechas;

//Se importan las clases que se van a utilizar.
import java.util.Scanner;

public class ValidarFechas {
	
	// Método principal.
	public static void main(String[] args) {
		// Inicialización de las variables.
		String fecha; // Almacena la entrada del usuario.
		Scanner input = new Scanner(System.in); // Recoge el texto de la terminal.
		
		System.out.println("Introduce una fecha (dd-mm-aaaa): ");
		fecha = input.nextLine(); // Guarda la entrada del usuario.
		input.close(); // Se cierra "input" ya que no se va a utilizar más.
		
		// Validación de la entrada.
		if(validarFormato(fecha)) // Se comprueba si el formato es correcto.
			if(validarFecha(fecha)) // Se comprueba si la fecha es válida.
				System.out.println("La fecha " + fecha + " es valida.");
			else
				System.out.println("La fecha " + fecha + " no es valida."); 
		else
			System.out.println("El formato introducido no es valido.");
	}
	
	// Método para validar el formato dd-mm-aaaa.
	public static boolean validarFormato(String fecha) {
		// Inicialización de las variables.
		boolean valido; // Será devuelto como resultado del método.
		String patronFecha = "\\d{2}-\\d{2}-\\d{4}"; // Es el patrón que sigue una fecha en formato dd-mm-aaaa.
		
		// Si "fecha" coincide con el patrón, el formato será correcto, aunque eso no quiere decir que la fecha también lo sea.
		valido = fecha.matches(patronFecha);
		
		return valido;
	}
	
	// Método que valida la fecha.
	public static boolean validarFecha(String fecha) {
		// Inicialización de las variables.
		boolean valido; // Será devuelto como resultado del método.
		int dia, mes, anyo; // Dia, mes y año.
		int[] mesDias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // Lista con los días de los meses del año, el índice + 1 corresponde al mes.
		
		// Se obtienen los valores de dia, mes y año. Se realiza la conversión a "int".
		dia = Integer.parseInt(fecha.substring(0, 2));
		mes = Integer.parseInt(fecha.substring(3, 5));
		anyo = Integer.parseInt(fecha.substring(6, 10));
		
		/* Si se cumple una de las dos condiciones, el año es bisiesto:
		 * - Sea divisible entre cuatro e indivisible entre cien.
		 * - Sea divisible entre cuatrocientos. */
		mesDias[1] = anyo % 4 == 0 && anyo % 100 != 0 || anyo % 400 == 0 ? 29 : 28; // Si es bisiesto se asignan 29 días a febrero, en caso contrario 28 días.
		
		/* Condiciones para que una fecha sea válida:
		 * - 0 < mes <= 12.
		 * - 0 < dia <= dia máximo del mes correspondiente.
		 * - 0 < año. */
		valido = mes > 0 && mes <= 12 && dia > 0 && dia <= mesDias[mes - 1] && anyo > 0;
		
		return valido;	
	}
}