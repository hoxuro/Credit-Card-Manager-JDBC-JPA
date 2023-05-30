/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import java.util.regex.Pattern;

/**
 * Clase con diferentes métodos que nos facilitan la validacion de datos para la
 * creacion de tarjetas de credito.
 *
 * @author Heriberto Amezcua
 * @version 3.0
 * @since JDK 19
 */
public class ValidarDatos {

    /**
     * Permite validar el titular de una tarjeta de credito
     *
     * @param nombre el nombre del titular de la tarjeta de credito
     * @return true si nombre es una cadena de caracteres entre 5 y 80
     * caracteres y esta compuesto de caracteres alfanumericos.
     */
    public static boolean validarTitular(String nombre) {
        return Pattern.matches("[ A-Za-záéíóúÁÉÍÓÚñÑ]{5,80}", nombre);
    }

    /**
     * Permite validar el pin de una tarjeta de credito.
     *
     * @param pin el pin de la tarjeta
     * @return true si el pin tien 4 caracteres de longitud minimo y solo esta
     * compuesto de digitos
     */
    public static boolean validarPin(String pin) {
        return Pattern.matches("^\\d{4,}", pin);
    }

    /**
     * Permite validar el limite de gasto de una tarjeta de credito.
     *
     * @param limite el limite de gasto de la tarjeta
     * @return true si el limite se encuentra entre 500 y 5000
     */
    public static boolean validarLimite(int limite) {
        return (limite >= 500 && limite <= 5000);
    }

    /**
     * Metodo estatico que comprueba si el NIF, CIE, NIE es valido.
     *
     * @param documento la identificacion introducida por el usuario.
     * @return true si es un NIF, CIF, NIE
     */
    public static boolean validarDocumento(String documento) {
        boolean esValido = true;

        //compruebo si cumple con la longitud
        if (documento.length() != 9) {
            esValido = false;
        }

        if (esValido) {
            //dependiendo del tipo de documento compruebo si es valido
            switch (tipoIdentificacion(documento)) {
                case "nif" ->
                    esValido = nifEsValido(documento);
                case "nie" ->
                    esValido = nieEsValido(documento);
                case "cif" ->
                    esValido = cifEsValido(documento);
            }
        }

        return esValido;
    }

    /**
     * Metodo para validar un numero de tarjeta de credito introducido por el
     * usuario. Realizara el algoritmo de Luhn.
     *
     * @param numero el numero de tarjeta de credito, digito de control
     * incluido.
     * @return true si el numero de tarjeta es valido.
     */
    public static boolean comprobarNumeroTarjeta(String numero) {
        boolean esValida = true;
        //primero valido que el numero solo se componga de digitos
        if (!Pattern.matches("^\\d+{16}", numero)) {
            esValida = false;
        }

        //realizo el algoritmo de Luhn para verificar el numero de tarjeta el cual
        //se encuentra dividido en los metodos calcSumaPorNueve y obtenerDigitoControl
        //si el digito de control de los primeros 15 digitos concuerda con el introducido por el usuario, la tarjeta es valida
        if (esValida) {
            if (!obtenerDigitoControl(numero.substring(0, numero.length() - 1)).equals(Character.toString(numero.charAt(numero.length() - 1)))) {
                esValida = false;
            }
        }
        return esValida;
    }

    /**
     * Metodo que calcula el digito de control de una tarjeta de credito a
     * partir del numero. Para que quede mas claro, de los 16 digitos de que
     * tiene el numero de la tarjeta de credito en total se obtiene el digito de
     * control (el digito 16) a partir de los primeros 15 digitos.
     *
     * @param numero el numero de la tarjeta de credito sin el digito de
     * control.
     * @return el digito de control de la tarjeta de credito.
     */
    public static String obtenerDigitoControl(String numero) {

        return "" + (calcSumaPorNueve(numero) % 10);
    }

    /**
     * Metodo estatico que calcula una parte del algoritmo de Luhn.
     *
     * @param num el numero de tarjeta de credito.
     * @return la suma parcial multiplicada por nueve del algoritmo de Luhn.
     */
    private static int calcSumaPorNueve(String num) {
        int sumaPares = 0, sumaImpares = 0, sumaParcial;

        //sumo las cifras pares
        for (int i = num.length() - 1; i >= 0; i--) {
            if ((i + 1) % 2 == 0) {
                int numeroPar, parPorDos;
                //extraigo el numero par
                numeroPar = Integer.parseInt(Character.toString(num.charAt(i)));
                //multiplico x2 el numero par
                parPorDos = numeroPar * 2;
                //sumo sus cifras y las aniado a la suma de los numero pares
                sumaPares += (parPorDos % 10 == parPorDos) ? parPorDos : (parPorDos / 10 + parPorDos % 10);

            }
        }

        //sumo las cifras impares
        for (int i = num.length() - 1; i >= 0; i--) {
            if ((i + 1) % 2 != 0) {
                sumaImpares += Integer.parseInt(Character.toString(num.charAt(i)));

            }
        }

        //sumo pares e impares y multiplico por nueve
        sumaParcial = sumaPares + sumaImpares;

        //devuelvo la suma parcial multiplicada por nueve para seguir realizando el algoritmo de Luhn
        return sumaParcial * 9;

    }

    /**
     * Metodo estatico que comprueba que tipo de identificacion que nos ha
     * introducido el usuario pudiendo ser NIF, NIE o CIF.
     *
     * @param identificacion la identificacion introducida por el usuario.
     * @return el tipo de identificacion, si esta mal devolvera nif
     */
    private static String tipoIdentificacion(String identificacion) {
        String tipoId;

        //comprueba que tipo de identificacion es, si es NIF o ninguna dara nif
        if (Pattern.matches("[x-zX-Z]", Character.toString(identificacion.charAt(0)))) {
            tipoId = "nie";
        } else if (Pattern.matches("[a-wA-W]", Character.toString(identificacion.charAt(0)))) {
            tipoId = "cif";
        } else {
            tipoId = "nif";
        }

        return tipoId;
    }

    /**
     * Metodo estatico que comprueba si el NIF introducido es valido.
     *
     * @param nif el NIF introducido por el usuario.
     * @return true si el NIF es valido.
     */
    private static boolean nifEsValido(String nif) {
        boolean esValido = true;

        //valido que el numero solo se componga de digitos
        if (!Pattern.matches("^\\d+$", nif.substring(0, 8))) {
            esValido = false;
        }

        //valido que el digito de control introducido concuerde con el numero
        if (esValido) {
            if (!generarDigitoControl(Integer.parseInt(nif.substring(0, 8))).equals(Character.toString(nif.charAt(8)).toUpperCase())) {
                esValido = false;
            }
        }

        return esValido;
    }

    /**
     * Metodo estatico que comprueba si el NIE introducido es valido.
     *
     * @param nie el NIE introducido por el usuario.
     * @return true si el NIE es valido.
     */
    private static boolean nieEsValido(String nie) {
        boolean esValido = true;

        //valido que el numero solo se componga de digitos
        if (!Pattern.matches("^\\d+$", nie.substring(1, 8))) {
            esValido = false;
        }

        if (esValido) {
            String letra = Character.toString(nie.charAt(0)).toUpperCase();
            int numero;

            //Dependiendo del tipo de letra aplicare una u otra forma para calcular el numero equivalente en NIF
            numero = switch (letra) {
                case "Y" ->
                    (int) Math.pow(10, 7) + Integer.parseInt(nie.substring(1, 8));
                case "Z" ->
                    (int) Math.pow(10, 7) * 2 + Integer.parseInt(nie.substring(1, 8));
                default ->
                    Integer.parseInt(nie.substring(1, 8));
            };

            String digitoControl = Character.toString(nie.charAt(8)).toUpperCase();

            //valido que el digito de control introducido concuerde con el numero
            if (!generarDigitoControl(numero).equals(digitoControl)) {
                esValido = false;
            }
        }

        return esValido;
    }

    /**
     * Metodo estatico que comprueba si el CIF introducido es valido.
     *
     * @param cif el CIF introducido por el usuario.
     * @return true si el CIF es valido.
     */
    private static boolean cifEsValido(String cif) {
        boolean esValido = true;

        //valido que la letra del tipo de organizacion sea correcta
        if (!Pattern.matches("[[a-wA-W]&&[^iIoOtT]]", Character.toString(cif.charAt(0)))) {
            esValido = false;
        }

        //valido que la letra del tipo de organizacion no sea de las que han quedado obsoletas
        if (!Pattern.matches("[^kKlLmM]", Character.toString(cif.charAt(0)))) {
            esValido = false;
        }

        //valido que el numero solo se componga de digitos
        if (!Pattern.matches("^\\d+$", cif.substring(1, 8))) {
            esValido = false;
        }

        if (esValido) {
            //valido que el codigo provincial exista
            int codigoProvincial = Integer.parseInt(cif.substring(1, 3));
            if ((codigoProvincial >= 65 && codigoProvincial <= 69) || (codigoProvincial >= 86 && codigoProvincial <= 90)) {
                esValido = false;
            }

            String digitoControl = Character.toString(cif.charAt(8)).toUpperCase();
            String digitosCentrales = cif.substring(1, 8);
            String tipoOrg = Character.toString(cif.charAt(0)).toUpperCase();

            //compruebo que el digito de control introducido se corresponda con los datos introducidos
            if (!generarDigitoControlCIF(tipoOrg, digitosCentrales).equals(digitoControl)) {
                esValido = false;
            }
        }

        return esValido;
    }

    /**
     * Metodo estatico que genera el digito de control correspondiente al numero
     * introducido
     *
     * @param n el numero del que queremos obtener el digito de control
     * @return el digito de control
     */
    private static String generarDigitoControl(int n) {
        int resto = n % 23;
        String digitoControl = "";

        switch (resto) {
            case 0 ->
                digitoControl = "T";
            case 1 ->
                digitoControl = "R";
            case 2 ->
                digitoControl = "W";
            case 3 ->
                digitoControl = "A";
            case 4 ->
                digitoControl = "G";
            case 5 ->
                digitoControl = "M";
            case 6 ->
                digitoControl = "Y";
            case 7 ->
                digitoControl = "F";
            case 8 ->
                digitoControl = "P";
            case 9 ->
                digitoControl = "D";
            case 10 ->
                digitoControl = "X";
            case 11 ->
                digitoControl = "B";
            case 12 ->
                digitoControl = "N";
            case 13 ->
                digitoControl = "J";
            case 14 ->
                digitoControl = "Z";
            case 15 ->
                digitoControl = "S";
            case 16 ->
                digitoControl = "Q";
            case 17 ->
                digitoControl = "V";
            case 18 ->
                digitoControl = "H";
            case 19 ->
                digitoControl = "L";
            case 20 ->
                digitoControl = "C";
            case 21 ->
                digitoControl = "K";
            case 22 ->
                digitoControl = "E";
        }

        return digitoControl;
    }

    /**
     * Metodo estatico que genera el digito de control del CIF a partir del tipo
     * de organizacion y de los digitos del documento.
     *
     * @param tipoOrg letra que identifica el tipo de organizacion.
     * @param digitos los digitos centrales del documento.
     * @return el digito de control del CIF
     */
    private static String generarDigitoControlCIF(String tipoOrg, String digitos) {
        int sumaPares = 0, sumaImpares = 0, sumaParcial = 0, digito;
        String digitoControl = "";

        //calculo la suma de los digitos pares
        for (int i = 0; i < digitos.length(); i++) {
            if ((i + 1) % 2 == 0) {
                digito = Integer.parseInt(Character.toString(digitos.charAt(i)));
                sumaPares += digito;
            }
        }

        //calculo la suma de los digitos impares
        for (int i = 0; i < digitos.length(); i++) {
            if ((i + 1) % 2 != 0) {
                digito = Integer.parseInt(Character.toString(digitos.charAt(i)));

                String digitoMultiplicado = "" + (digito * 2);

                sumaImpares += (digitoMultiplicado.length() == 1) ? Integer.parseInt(Character.toString(digitoMultiplicado.charAt(0)))
                        : Integer.parseInt(Character.toString(digitoMultiplicado.charAt(0)))
                        + Integer.parseInt(Character.toString(digitoMultiplicado.charAt(1)));
            }
        }

        sumaParcial = sumaPares + sumaImpares;

        //extraigo las unidades de la suma parcial
        int unidadesSumaParcial = sumaParcial % 10;

        //Obtengo el digito de control dependiendo de la letra del tipo de organizacion
        switch (tipoOrg) {
            case "A", "B", "C", "D", "E", "F", "G", "H", "J", "U", "V" ->
                digitoControl = "" + (10 - unidadesSumaParcial);
            case "N", "P", "Q", "R", "S", "W" ->
                digitoControl = letraControl(10 - unidadesSumaParcial);
        }

        return digitoControl;
    }

    /**
     * Metodo estatico que calcula el digito de control cuando es una letra de
     * un CIF.
     *
     * @param n el numero resultante de restar 10 menos las unidades de la suma
     * parcial del algoritmo de Luhn para calcular el CIF.
     * @return el digito de control del CIF cuando es una letra.
     */
    private static String letraControl(int n) {
        String letraControl = "";
        switch (n) {
            case 0 ->
                letraControl = "J";
            case 1 ->
                letraControl = "A";
            case 2 ->
                letraControl = "B";
            case 3 ->
                letraControl = "C";
            case 4 ->
                letraControl = "D";
            case 5 ->
                letraControl = "E";
            case 6 ->
                letraControl = "F";
            case 7 ->
                letraControl = "G";
            case 8 ->
                letraControl = "H";
            case 9 ->
                letraControl = "I";
            case 10 ->
                letraControl = "J";
        }

        return letraControl;
    }

}
