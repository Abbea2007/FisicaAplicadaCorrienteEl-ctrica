import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el tipo de circuito:");
        System.out.println("1. Serie");
        System.out.println("2. Paralelo");
        int op = sc.nextInt();
        if(op==1){
            resistenciaSerie(sc);
        }else if(op==2){
            resistenciaParalelo(sc);
        }else{
            System.out.println("Opción inválida");
        }
        sc.close();
    }

    public static void resistenciaSerie(Scanner sc) {
        System.out.println("Ingresa el número de resistencias:");
        int n = sc.nextInt();
        ArrayList<Double> resistencias = new ArrayList<>();
        double res = 0;
        for(int i = 0; i < n; i++){
            System.out.println("Ingresa R" + (i+1) + ":");
            double r = sc.nextDouble();
            resistencias.add(r);
            res += r;
        }
        System.out.println("Ingresa el voltaje total V:");
        double V = sc.nextDouble();
        double I_total = V / res;
        System.out.println("Resistencia equivalente en serie: " + res + " Ω");
        System.out.println("Corriente total: " + I_total + " A");
        for(int i = 0; i < n; i++){
            double r = resistencias.get(i);
            double Vi = I_total * r;
            double Ii = I_total;
            System.out.println("Resistencia " + (i+1) + ": R=" + r + " Ω, V=" + Vi + " V, I=" + Ii + " A");
        }
    }

    public static void resistenciaParalelo(Scanner sc) {
        System.out.println("Ingresa el número de resistencias:");
        int n = sc.nextInt();
        ArrayList<Double> resistencias = new ArrayList<>();
        double sum = 0;
        for(int i = 0; i < n; i++){
            System.out.println("Ingresa R" + (i+1) + ":");
            double r = sc.nextDouble();
            if(r == 0){
                System.out.println("Resistencia cero no permitida en paralelo");
                return;
            }
            resistencias.add(r);
            sum += 1 / r;
        }
        double req = 1 / sum;
        System.out.println("Ingresa el voltaje total V:");
        double V = sc.nextDouble();
        double I_total = V / req;
        System.out.println("Resistencia equivalente en paralelo: " + req + " Ω");
        System.out.println("Corriente total: " + I_total + " A");
        for(int i = 0; i < n; i++){
            double r = resistencias.get(i);
            double Vi = V;
            double Ii = V / r;
            System.out.println("Resistencia " + (i+1) + ": R=" + r + " Ω, V=" + Vi + " V, I=" + Ii + " A");
        }
    }
}