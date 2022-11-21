package ejercicioPago;

import java.util.Random;
import java.util.Scanner;

public class ValidarPago {

    /* Método principal, pide datos al usuario y llama a otros métodos dependiendo de las entradas.
       Comprueba y valida si se paga en efectivo o tarjeta, la cantidad en efectivo o el número de tarjeta.
       NOTA:
       - La cuenta del restaurante es un número aleatorio entre 30€ y 100€ (con dos decimales).
       - La cantidad máxima que se puede entregar en efectivo es de 1000€.
       - Se realizan los cálculos con enteros, en céntimos.
       TANTO LA CUENTA COMO EL LÍMITE DE EFECTIVO SE PUEDEN CAMBIAR PORQUE SON ARBITRARIOS.
     */
    public static void main(String[] args) {
        // Inicialización de las variables.
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        String metodoDePago, pago;
        int importe = rand.nextInt(3000, 10000);

        System.out.println("Son " + importe / 100f + "€, efectivo o tarjeta?");
        metodoDePago = input.nextLine();

        if (metodoDePago.equals("efectivo")) {
            System.out.println("Introduce el efectivo: ");
            pago = input.nextLine();
            if (pago.matches("^\\d{1,3}(\\.\\d{1,2})?$|^1000$")) {
                pagoEfectivo(importe, (int) (Double.parseDouble(pago) * 100));
            } else
                System.out.println("Introduce una cantidad en el formato x, x.x o x.xx igual o inferior a 1000.");
        } else if (metodoDePago.equals("tarjeta")) {
            System.out.println("Introduce el número de tarjeta: ");
            pago = input.nextLine();
            if (pago.matches("^(4|5)\\d{3}(\\s\\d{4}){3}$|^3\\d{3}\\s\\d{6}\\s\\d{5}$")) {
                pagoTarjeta(pago);
            } else
                System.out.println("Solo se aceptan VISA, MasterCard y American Express.");
        } else
            System.out.println("Introduce efectivo o tarjeta.");
        input.close();
    }

    // Calcula las cantidades de billetes y monedas que se tienen que devolver, hay que pasar de parametros el importe y el dinero que se entrega.
    public static void pagoEfectivo(int importe, int entregado) {
        int i; // Variable contador.
        int cambio = entregado - importe; // Cantidad a devolver.
        int[] dinero = {50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1}; // Lista de los billetes y monedas.
        int[] dineroCantidad = new int[15]; // Almacenará la cantidad de cada billete y moneda.

        if(cambio > 0) {
            System.out.println("Te devuelvo " + cambio / 100f + "€.");
            for(i = 0; i < 15; i++) {
                if(cambio >= dinero[i]) {
                    dineroCantidad[i] = cambio / dinero[i];
                    cambio = cambio - dineroCantidad[i] * dinero[i];
                    if(i < 7)
                        System.out.println(dineroCantidad[i] + " billete/s de " + dinero[i] / 100 + "€.");
                    else if(i < 9)
                        System.out.println(dineroCantidad[i] + " moneda/s de " + dinero[i] / 100 + "€.");
                    else
                        System.out.println(dineroCantidad[i] + " moneda/s de " + dinero[i] + " céntimos.");
                }
            }
        } else if(cambio == 0)
            System.out.println("Está justo, gracias.");
        else
            System.out.println("Te faltan " + -cambio / 100f + "€.");
    }

    // Muestra por pantalla con qué tipo de tarjeta se ha realizado el pago y los 4 últimos dígitos.
    public static void pagoTarjeta(String numeroTarjeta) {
        if(numeroTarjeta.startsWith("3"))
            System.out.println("La tarjeta American Express acabada en **** ****** " + numeroTarjeta.substring(12, 17) + " ha sido aceptada.");
        else if(numeroTarjeta.startsWith("4"))
            System.out.println("La tarjeta VISA acabada en **** **** **** " + numeroTarjeta.substring(15, 19) + " ha sido aceptada.");
        else
            System.out.println("La tarjeta MasterCard acabada en **** **** **** " + numeroTarjeta.substring(15, 19) + " ha sido aceptada.");
    }
}