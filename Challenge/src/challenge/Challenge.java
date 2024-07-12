/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package challenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author manci
 */
public class Challenge {

    /**
     * @param args the command line arguments
     */
    public static void getCurrency(String currency, String currency2, double numberIn) {
        try {
            URL url = new URL("https://v6.exchangerate-api.com/v6/8168857d36ab125bc6b5bbbd/latest/" + currency2);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configuración de la conexión
            connection.setRequestMethod("GET"); // o "POST", "PUT", etc.
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Leer la respuesta
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    String isCurrency = "^\"" + currency + "\"";
                    Pattern pattern = Pattern.compile(isCurrency);
                    Matcher matcher = pattern.matcher(responseLine.trim());
                    if (matcher.find()) {
                        String text = responseLine.trim(); // Puedes cambiar este valor para probar diferentes casos
                        String regex = "\\d+(\\.\\d+)?"; // Expresión regular para encontrar números decimales o enteros

                        Pattern pattern2 = Pattern.compile(regex);
                        Matcher matcher2 = pattern2.matcher(text);

                        while (matcher2.find()) {
                            System.out.println(currency+ ": " + numberIn);
                            System.out.println(responseLine.trim());
                            String number = matcher2.group();
                            System.out.println("Total: " + ( Double.parseDouble(number) * numberIn));
                        }
                    }
                }

            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static String menu2() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa la opcion deseada par el cambio:");
        System.out.println("1. Moneda mexicana (MXN)");
        System.out.println("2. Dólar estadounidense (USD)");
        System.out.println("3. Euro (EUR)");
        System.out.println("4. Yen japonés (JPY)");
        System.out.println("5. Libra esterlina (GBP)");
        System.out.println("6. Dólar australiano (AUD)");

        try {
            int opc = sc.nextInt();
            switch (opc) {
                case 1:
                    return "MXN";
                case 2:
                    return "USD";
                case 3:
                    return "EUR";
                case 4:
                    return "JPY";
                case 5:
                    return "GBP";
                case 6:
                    return "AUD";
                default:
                    System.err.println("Ingrese solo numeros correctos");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Ingresa solo numeros por favor");
            //e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        boolean start = true;
        while (start) {

            Scanner sc = new Scanner(System.in);

            System.out.println("Ingresa la opcion deseada:");
            System.out.println("1. Moneda mexicana (MXN)");
            System.out.println("2. Dólar estadounidense (USD)");
            System.out.println("3. Euro (EUR)");
            System.out.println("4. Yen japonés (JPY)");
            System.out.println("5. Libra esterlina (GBP)");
            System.out.println("6. Dólar australiano (AUD)");
            System.out.println("Ingresa 0 para salir");
            try {
                int opc = sc.nextInt();
                String currency2;
                System.out.println("ingresa el valor a cambiar");
                double number = sc.nextDouble();
                switch (opc) {
                    case 0:
                        System.out.println("Adios");
                        start = false;
                        break;
                    case 1:
                        currency2 = menu2();
                        getCurrency("MXN", currency2, number);
                        break;
                    case 2:
                        currency2 = menu2();
                        getCurrency("USD", currency2, number);
                        break;
                    case 3:
                        currency2 = menu2();
                        getCurrency("EUR", currency2, number);
                        break;
                    case 4:
                        currency2 = menu2();
                        getCurrency("JPY", currency2, number);
                        break;
                    case 5:
                        currency2 = menu2();
                        getCurrency("GBP", currency2, number);
                        break;
                    case 6:
                        currency2 = menu2();
                        getCurrency("AUD", currency2, number);
                        break;
                    default:
                        System.err.println("Ingrese solo numeros correctos");
                        break;
                }
            } catch (Exception e) {
                System.err.println("Ingresa solo numeros por favor");
                //e.printStackTrace();
            }
        }
    }

}
