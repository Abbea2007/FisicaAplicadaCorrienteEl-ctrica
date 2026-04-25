import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("   PROGRAMA DE FISICA APLICADA");
        System.out.println("==================================");

        System.out.println("\nEste programa resuelve circuitos simples con:");
        System.out.println("- Resistencias");
        System.out.println("- Capacitores");
        System.out.println("- Conexión en serie o paralelo");

        System.out.println("\n¿Cómo quieres ingresar los datos?");
        System.out.println("1. Escribirlos manualmente");
        System.out.println("2. Leerlos desde un archivo de texto");
        System.out.print("Elige una opción: ");

        int fuente = leerEntero(sc);

        if (fuente == 1) {
            menuConsola(sc);
        } else if (fuente == 2) {
            leerArchivo(sc);
        } else {
            System.out.println("Opción inválida.");
        }

        sc.close();
    }

    public static void menuConsola(Scanner sc) {
        System.out.println("\n¿Qué tipo de circuito quieres resolver?");
        System.out.println("1. Circuito con resistencias");
        System.out.println("2. Circuito con capacitores");
        System.out.print("Elige una opción: ");
        int elemento = leerEntero(sc);

        System.out.println("\n¿Cómo están conectados?");
        System.out.println("1. En serie");
        System.out.println("2. En paralelo");
        System.out.print("Elige una opción: ");
        int conexion = leerEntero(sc);

        String nombreElemento = elemento == 1 ? "resistencias" : "capacitores";

        System.out.println("\n¿Cuántas " + nombreElemento + " tiene el circuito?");
        System.out.println("Ejemplo: si hay R1, R2 y R3, entonces escribe 3.");
        System.out.print("Cantidad: ");
        int n = leerEntero(sc);

        ArrayList<Double> valores = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (elemento == 1) {
                System.out.println("\nIngresa el valor de la resistencia R" + (i + 1) + ":");
                System.out.println("Puedes escribir ejemplos como: 220Ω, 1kΩ, 10 kΩ, 2MΩ");
            } else {
                System.out.println("\nIngresa el valor del capacitor C" + (i + 1) + ":");
                System.out.println("Puedes escribir ejemplos como: 10uF, 100 nF, 33pF");
            }

            System.out.print("Valor: ");
            String entrada = sc.nextLine();
            valores.add(convertirPrefijo(entrada));
        }

        System.out.println("\nIngresa el voltaje de la batería:");
        System.out.println("Puedes escribir ejemplos como: 12V, 5 V, 53mV");
        System.out.print("Voltaje: ");
        String voltajeTexto = sc.nextLine();
        double V = convertirPrefijo(voltajeTexto);

        if (elemento == 1 && conexion == 1) {
            resistoresSerie(valores, V);
        } else if (elemento == 1 && conexion == 2) {
            resistoresParalelo(valores, V);
        } else if (elemento == 2 && conexion == 1) {
            capacitoresSerie(valores, V);
        } else if (elemento == 2 && conexion == 2) {
            capacitoresParalelo(valores, V);
        } else {
            System.out.println("Opción inválida.");
        }
    }

    public static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, escribe un número válido.");
            sc.next();
        }

        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }

    public static void leerArchivo(Scanner sc) {
        System.out.println("\nIngresa el nombre del archivo:");
        System.out.println("Ejemplo: datos.txt");
        System.out.print("Archivo: ");
        String nombreArchivo = sc.nextLine();

        try {
            Scanner archivo = new Scanner(new File(nombreArchivo));

            String elemento = archivo.next();
            String conexion = archivo.next();
            double V = convertirPrefijo(archivo.next());
            int n = archivo.nextInt();

            ArrayList<Double> valores = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                valores.add(convertirPrefijo(archivo.next()));
            }

            archivo.close();

            if (elemento.equalsIgnoreCase("resistor") && conexion.equalsIgnoreCase("serie")) {
                resistoresSerie(valores, V);
            } else if (elemento.equalsIgnoreCase("resistor") && conexion.equalsIgnoreCase("paralelo")) {
                resistoresParalelo(valores, V);
            } else if (elemento.equalsIgnoreCase("capacitor") && conexion.equalsIgnoreCase("serie")) {
                capacitoresSerie(valores, V);
            } else if (elemento.equalsIgnoreCase("capacitor") && conexion.equalsIgnoreCase("paralelo")) {
                capacitoresParalelo(valores, V);
            } else {
                System.out.println("El archivo tiene datos inválidos.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo.");
        }
    }

    public static double convertirPrefijo(String texto) {
        texto = texto.trim();

        texto = texto.replace("Ω", "");
        texto = texto.replace("ohm", "");
        texto = texto.replace("Ohm", "");
        texto = texto.replace("F", "");
        texto = texto.replace("V", "");
        texto = texto.replace(" ", "");

        double factor = 1;

        if (texto.endsWith("p")) {
            factor = 1e-12;
            texto = texto.substring(0, texto.length() - 1);
        } else if (texto.endsWith("n")) {
            factor = 1e-9;
            texto = texto.substring(0, texto.length() - 1);
        } else if (texto.endsWith("u") || texto.endsWith("µ")) {
            factor = 1e-6;
            texto = texto.substring(0, texto.length() - 1);
        } else if (texto.endsWith("m")) {
            factor = 1e-3;
            texto = texto.substring(0, texto.length() - 1);
        } else if (texto.endsWith("k") || texto.endsWith("K")) {
            factor = 1e3;
            texto = texto.substring(0, texto.length() - 1);
        } else if (texto.endsWith("M")) {
            factor = 1e6;
            texto = texto.substring(0, texto.length() - 1);
        } else if (texto.endsWith("G")) {
            factor = 1e9;
            texto = texto.substring(0, texto.length() - 1);
        }

        return Double.parseDouble(texto) * factor;
    }

    public static void resistoresSerie(ArrayList<Double> R, double V) {
        double req = 0;

        for (double r : R) {
            req += r;
        }

        double I = V / req;

        System.out.println("\n========== RESULTADOS ==========");
        System.out.println("Circuito: Resistencias en serie");
        System.out.println("Resistencia equivalente: " + req + " Ω");
        System.out.println("Corriente por la batería: " + I + " A");

        for (int i = 0; i < R.size(); i++) {
            double voltaje = I * R.get(i);
            System.out.println("R" + (i + 1) + ": " + R.get(i) + " Ω | Voltaje: " + voltaje + " V");
        }
    }

    public static void resistoresParalelo(ArrayList<Double> R, double V) {
        double suma = 0;

        for (double r : R) {
            if (r == 0) {
                System.out.println("No se permite una resistencia de 0 Ω.");
                return;
            }

            suma += 1 / r;
        }

        double req = 1 / suma;
        double ITotal = V / req;

        System.out.println("\n========== RESULTADOS ==========");
        System.out.println("Circuito: Resistencias en paralelo");
        System.out.println("Resistencia equivalente: " + req + " Ω");
        System.out.println("Corriente por la batería: " + ITotal + " A");

        for (int i = 0; i < R.size(); i++) {
            double corriente = V / R.get(i);
            System.out.println("R" + (i + 1) + ": " + R.get(i) + " Ω | Corriente: " + corriente + " A");
        }
    }

    public static void capacitoresSerie(ArrayList<Double> C, double V) {
        double suma = 0;

        for (double c : C) {
            if (c == 0) {
                System.out.println("No se permite una capacitancia de 0 F.");
                return;
            }

            suma += 1 / c;
        }

        double ceq = 1 / suma;
        double carga = ceq * V;

        System.out.println("\n========== RESULTADOS ==========");
        System.out.println("Circuito: Capacitores en serie");
        System.out.println("Capacitancia equivalente: " + ceq + " F");
        System.out.println("Carga en cada capacitor: " + carga + " C");

        for (int i = 0; i < C.size(); i++) {
            double voltaje = carga / C.get(i);
            System.out.println("C" + (i + 1) + ": " + C.get(i) + " F | Carga: " + carga + " C | Voltaje: " + voltaje + " V");
        }
    }

    public static void capacitoresParalelo(ArrayList<Double> C, double V) {
        double ceq = 0;

        for (double c : C) {
            ceq += c;
        }

        double cargaTotal = ceq * V;

        System.out.println("\n========== RESULTADOS ==========");
        System.out.println("Circuito: Capacitores en paralelo");
        System.out.println("Capacitancia equivalente: " + ceq + " F");
        System.out.println("Carga total: " + cargaTotal + " C");

        for (int i = 0; i < C.size(); i++) {
            double carga = C.get(i) * V;
            System.out.println("C" + (i + 1) + ": " + C.get(i) + " F | Carga: " + carga + " C");
        }
    }
}