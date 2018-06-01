import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Modify {

    static Scanner sc = new Scanner(System.in);

    public static void modify(String directory, String db, ArrayList<String> column, ArrayList<String> dataType, ArrayList<Integer> size, long num) {
        File current = new File(db);
        try {
            boolean repeat;
            String temp, dateTemp, newDate;
            long start;
            String[] actualDate = new String[3];
            RandomAccessFile raf = new RandomAccessFile(current, "rw");
            raf.seek(num);
            raf.readBoolean();
            for (int i = 0; i < dataType.size(); i++) {
                System.out.println(dataType.get(i));
                switch (dataType.get(i)) {
                    //SI ES DATE TIPO A
                    case "a":
                        start = raf.getFilePointer();
                        //System.out.println(start + "*");
                        dateTemp = raf.readUTF();
                        actualDate = dateTemp.split("/");
                        newDate = validateDate("1", actualDate);
                        while (newDate.length() != 8) {
                            newDate += " ";
                        }
                        raf.seek(start);
                        raf.writeUTF(newDate);
                        //System.out.println(raf.getFilePointer()+"*");
                        break;
                    //SI ES DATE TIPO B
                    case "b":
                        start = raf.getFilePointer();
                        //System.out.println(start + "*");
                        dateTemp = raf.readUTF();
                        actualDate = dateTemp.split("/");
                        newDate = validateDate("2", actualDate);
                        while (newDate.length() != 10) {
                            newDate += " ";
                        }
                        raf.seek(start);
                        raf.writeUTF(newDate);
                        //System.out.println(raf.getFilePointer()+"*");
                        break;
                    //ESTE ES PARA EL TIPO DE BOOLEAN
                    case "1":
                        repeat = false;
                        boolean inBoolean;
                        System.out.println("Insertar booleano o presionar enter en caso de no querer modificar este campo");
                        do {
                            System.out.println(column.get(i) + ": ");
                            System.out.println("\t1 - true");
                            System.out.println("\t2 - false");
                            temp = sc.nextLine();
                            if (temp.equals("1")) {
                                inBoolean = true;
                                repeat = false;
                                raf.writeBoolean(inBoolean);
                            } else if (temp.equals("2")) {
                                inBoolean = false;
                                repeat = false;
                                raf.writeBoolean(inBoolean);
                            } else if (temp.length() == 0) {
                                raf.readBoolean();
                                repeat = false;
                            } else {
                                repeat = true;
                            }

                        } while (repeat);
                        break;
                    //ESTE ES DE TIPO DE CHAR SOLO UN BYTE
                    case "2":
                        repeat = true;
                        char inChar;
                        System.out.println("Insertar char o presionar enter para no modificar este campo");
                        do {
                            System.out.print(column.get(i) + ": ");
                            temp = sc.nextLine();
                            if (temp.length() == 0) {
                                raf.readChar();
                                repeat = false;
                            } else {
                                inChar = temp.charAt(0);
                                raf.writeChar(inChar);
                                repeat = false;
                            }
                        } while (repeat);
                        break;
                    //PARA EL STRING COMPRUEBA EL TAMANIO QUE CONCUERDE CON EL DE SIZE
                    case "3":
                        String inString;
                        // SE INDICA AL USUARIO EL MAXIMO DE CARACTERES ADMITIDO
                        System.out.println("Insertar String o presionar enter para no modificar este campo: MAX(" + size.get(i) + ")");
                        do {
                            //SE IMPRIME EL NOMBRE DE LA COLUMNA
                            System.out.print(column.get(i) + ": ");
                            //SE LEE LA ENTRADA
                            inString = sc.nextLine();
                            if (inString.length() == 0) {
                                raf.readUTF();
                                //SALE DEL CICLO PARA NO CONTINUAR EN CASO DE QUE ESTE VACIOss
                                break;
                            }
                            //AQUI SE CONSULTA SI EL NUMERO DE CARACTERES CORRESPONDE CON EL TAMANIO
                            //DE CADA CAMPO
                            if (inString.length() <= size.get(i)) {
                                //EN CASO DE QUE EL STRING SEA MENOR DEL TAMANIO ESTIPULADO
                                //SE LLENAN CON ESPACIOS EN BLANCO
                                while (inString.length() < size.get(i)) {
                                    inString += " ";
                                }
                                //SE MANDA A ESCRIBIR
                                raf.writeUTF(inString);
                                //SALE DEL CICLO
                                break;
                            }
                            //SI LA ENTRADA POR EL CONTRARIO NO CUMPLE CON EL RANGO SE MUESTRA EL ERROR Y VUELVE A INICIAR EL CICLO
                            System.err.println("La entrada solo admite: " + size.get(i) + " caracteres, el numero de caracteres ingresados es: " + inString.length());
                        } while (true);

                        break;
                    // PARA LOS BYTES
                    case "4":
                        byte inByte;
                        // SE INDICA AL USUARIO CUAL SERIA EL TAMANIO QUE ESTA ESTABLECIDO...
                        System.out.println("Insertar byte o presionar enter para no modificar este campo: MAX(" + size.get(i) + ")");
                        //
                        do {
                            // SE IMPRIME EL NOMBRE DE LA COLUMNA:
                            System.out.print(column.get(i) + ": ");
                            // SE LEE LA ENTRADA DEL SISTEMA
                            temp = sc.nextLine();
                            // SI SE DEJA VACIO...
                            if (temp.length() == 0) {
                                //SE ESCRIBE Y LUEGO SE SALE DEL CICLO
                                raf.readByte();
                                break;
                            }
                            // DE LO CONTRARIO...
                            try {
                                //EN CASO DE QUE LA ENTRADA INICIE CON UN SIGNO MENOS...
                                int tamanio = temp.length();
                                if (temp.startsWith("-")) {
                                    //SOLO PARA NO TOMAR EN CUENTA EL SI TIENE UN SIGNO MENOS SE LE RESTA UNO AL TAMANIO
                                    tamanio--;
                                }
                                //COMPRUEBA EL TAMANIO DE LA ENTRADA...
                                if (tamanio <= size.get(i)) {
                                    //SI CUMPLE...
                                    inByte = Byte.parseByte(temp);
                                    raf.writeByte(inByte);
                                    break;
                                }
                                //SI NO...
                                System.err.println("La entrada solo admite: " + size.get(i) + " caracteres, el numero de caracteres ingresados es: " + temp.length());
                            } catch (Exception e) {
                                System.err.println("entrada invalida" );
                            }
                        } while (true);
                        break;
                    // PARA LOS SHORT
                    case "5":
                        short inShort;
                        System.out.println("Insertar short o presionar enter para no modificar este campo: MAX(" + size.get(i) + ")");
                        do {
                            // SE INFORMA DE LA COLUMNA
                            System.out.print(column.get(i) + ": ");
                            // SE LEE LA ENTRADA
                            temp = sc.nextLine();
                            //SI ESTA VACIO
                            if (temp.length() == 0) {
                                //SE ESCRIBE Y LUEGO SE SALE DEL CICLO
                                raf.readShort();
                                break;
                            }
                            // DE LO CONTRARIO
                            try {
                                //EN CASO DE QUE LA ENTRADA INICIE CON UN SIGNO MENOS...
                                int tamanio = temp.length();
                                if (temp.startsWith("-")) {
                                    //SOLO PARA NO TOMAR EN CUENTA EL SI TIENE UN SIGNO MENOS SE LE RESTA UNO AL TAMANIO
                                    tamanio--;
                                }
                                //COMPRUEBA EL TAMANIO DE LA ENTRADA...
                                if (tamanio <= size.get(i)) {
                                    //SI CUMPLE...
                                    inShort = Short.parseShort(temp);
                                    raf.writeShort(inShort);
                                    break;
                                }
                                //SI NO...
                                System.err.println("La entrada solo admite: " + size.get(i) + " caracteres, el numero de caracteres ingresados es: " + temp.length());
                            } catch (Exception e) {
                                System.err.println("entrada invalida");
                            }
                        } while (true);
                        break;
                    //PARA LOS ENTEROS
                    case "6":
                        int inInt;
                        System.out.println("Insertar int o presionar enter para no modificar este campo: MAX(" + size.get(i) + ")");
                        do {                            
                             // SE INFORMA DE LA COLUMNA
                            System.out.print(column.get(i) + ": ");
                            // SE LEE LA ENTRADA
                            temp = sc.nextLine();
                            //SI ESTA VACIO
                            if (temp.length() == 0) {
                                //SE ESCRIBE Y LUEGO SE SALE DEL CICLO
                                raf.readInt();
                                break;
                            }
                            // DE LO CONTRARIO
                            try {
                                //EN CASO DE QUE LA ENTRADA INICIE CON UN SIGNO MENOS...
                                int tamanio = temp.length();
                                if (temp.startsWith("-")) {
                                    //SOLO PARA NO TOMAR EN CUENTA EL SI TIENE UN SIGNO MENOS SE LE RESTA UNO AL TAMANIO
                                    tamanio--;
                                }
                                //COMPRUEBA EL TAMANIO DE LA ENTRADA...
                                if (tamanio <= size.get(i)) {
                                    //SI CUMPLE...
                                    inInt = Integer.parseInt(temp);
                                    raf.writeInt(inInt);
                                    break;
                                }
                                //SI NO...
                                System.err.println("La entrada solo admite: " + size.get(i) + " caracteres, el numero de caracteres ingresados es: " + temp.length());
                            } catch (Exception e) {
                                System.err.println("entrada invalida");
                            }
                        } while (true);
                        break;
                    case "7":
                        long inLong;
                        System.out.println("Insertar long o presionar enter para no modificar este campo: MAX(" + size.get(i) + ")");
                        do {                            
                             // SE INFORMA DE LA COLUMNA
                            System.out.print(column.get(i) + ": ");
                            // SE LEE LA ENTRADA
                            temp = sc.nextLine();
                            //SI ESTA VACIO
                            if (temp.length() == 0) {
                                //SE ESCRIBE Y LUEGO SE SALE DEL CICLO
                                raf.readLong();
                                break;
                            }
                            // DE LO CONTRARIO
                            try {
                                //EN CASO DE QUE LA ENTRADA INICIE CON UN SIGNO MENOS...
                                int tamanio = temp.length();
                                if (temp.startsWith("-")) {
                                    //SOLO PARA NO TOMAR EN CUENTA EL SI TIENE UN SIGNO MENOS SE LE RESTA UNO AL TAMANIO
                                    tamanio--;
                                }
                                //COMPRUEBA EL TAMANIO DE LA ENTRADA...
                                if (tamanio <= size.get(i)) {
                                    //SI CUMPLE...
                                    inLong = Long.parseLong(temp);
                                    raf.writeLong(inLong);
                                    break;
                                }
                                //SI NO...
                                System.err.println("La entrada solo admite: " + size.get(i) + " caracteres, el numero de caracteres ingresados es: " + temp.length());
                            } catch (Exception e) {
                                System.err.println("entrada invalida");
                            }
                        } while (true);
                        break;
                    case "8":
                    	 float inFloat;
                        System.out.println("Insertar float o presionar enter para no modificar este campo: MAX(" + size.get(i) + ")"); 
                        do {                            
                             // SE INFORMA DE LA COLUMNA
                            System.out.print(column.get(i) + ": ");
                            // SE LEE LA ENTRADA
                            temp = sc.nextLine();
                            //SI ESTA VACIO
                            if (temp.length() == 0) {
                                //SE ESCRIBE Y LUEGO SE SALE DEL CICLO
                                raf.readFloat();
                                break;
                            }
                            // DE LO CONTRARIO
                            try {
                                //EN CASO DE QUE LA ENTRADA INICIE CON UN SIGNO MENOS...
                                int tamanio = temp.length();
                                if (temp.startsWith("-")) {
                                    //SOLO PARA NO TOMAR EN CUENTA EL SI TIENE UN SIGNO MENOS SE LE RESTA UNO AL TAMANIO
                                    tamanio--;
                                }
                                //COMPRUEBA EL TAMANIO DE LA ENTRADA...
                                if (tamanio <= size.get(i)) {
                                    //SI CUMPLE...
                                    inFloat = Float.parseFloat(temp);
                                    raf.writeFloat(inFloat);
                                    break;
                                }
                                //SI NO...
                                System.err.println("La entrada solo admite: " + size.get(i) + " caracteres, el numero de caracteres ingresados es: " + temp.length());
                            } catch (Exception e) {
                                System.err.println("entrada invalida");
                            }
                        } while (true);
                        repeat = true;
                        break;
                    case "9":
                        repeat = true;
                        double inDouble;
              
                        System.out.println("Insertar Double o presionar enter para no modificar este campo: MAX(" + size.get(i) + ")"); 
                        do {                            
                             // SE INFORMA DE LA COLUMNA
                            System.out.print(column.get(i) + ": ");
                            // SE LEE LA ENTRADA
                            temp = sc.nextLine();
                            //SI ESTA VACIO
                            if (temp.length() == 0) {
                                //SE ESCRIBE Y LUEGO SE SALE DEL CICLO
                                raf.readDouble();
                                break;
                            }
                            // DE LO CONTRARIO
                            try {
                                //EN CASO DE QUE LA ENTRADA INICIE CON UN SIGNO MENOS...
                                int tamanio = temp.length();
                                if (temp.startsWith("-")) {
                                    //SOLO PARA NO TOMAR EN CUENTA EL SI TIENE UN SIGNO MENOS SE LE RESTA UNO AL TAMANIO
                                    tamanio--;
                                }
                                //COMPRUEBA EL TAMANIO DE LA ENTRADA...
                                if (tamanio <= size.get(i)) {
                                    //SI CUMPLE...
                                    inDouble = Double.parseDouble(temp);
                                    raf.writeDouble(inDouble);
                                    break;
                                }
                                //SI NO...
                                System.err.println("La entrada solo admite: " + size.get(i) + " caracteres, el numero de caracteres ingresados es: " + temp.length());
                            } catch (Exception e) {
                                System.err.println("entrada invalida");
                            }
                        } while (true);
                        repeat = true;
                        break;

                }
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String validateDate(String style, String[] actualDate) {
        boolean leap = false;
        boolean repeat = true;
        byte exit = 0;
        String temp;
        int year = 0;
        byte month = 0, day = 0;
        System.out.print((style.equals("1") ? "Ingrese año en formato [YY]" : "Ingrese año en formato [YYYY]")
                + " o presionar enter en caso de no querer modificar este campo ");
        temp = sc.nextLine();
        if (temp.length() == 0) {
            year = Integer.parseInt(actualDate[2]);
            repeat = false;
        } else {
            exit = 1;
        }
        while (repeat) {
            if (exit != 1) {
                System.out.print(style.equals("1") ? "\nIngrese año en formato [YY] " : "\nIngrese año en formato [YYYY] ");
                temp = sc.nextLine();
            }
            exit = 0;
            try {
                year = Integer.parseInt(temp);
                if (style.equals("1") && temp.length() == 2) {
                    if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
                        leap = true;
                    } else {
                        leap = false;
                    }
                    repeat = false;
                } else if (style.equals("2") && temp.length() == 4) {
                    if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
                        leap = true;
                    } else {
                        leap = false;
                    }
                    repeat = false;
                } else {
                    System.out.println("El año ingresado no es valido segun el formato establecido");
                }
            } catch (Exception e) {
                System.out.println("El año ingresado no es correcto");
            }
        }
        repeat = true;
        System.out.print("\nIngrese mes o presionar enter en caso de no querer modificar este campo ");
        temp = sc.nextLine();
        if (temp.length() == 0) {
            month = Byte.parseByte(actualDate[1]);
            repeat = false;
        } else {
            exit = 1;
        }
        while (repeat) {
            if (exit != 1) {
                System.out.print("\nIngrese mes ");
                temp = sc.nextLine();
            }
            exit = 0;
            try {
                month = Byte.parseByte(temp);
                if (!(month > 0 && month < 13)) {
                    System.out.println("El mes ingresado no es valido");
                } else {
                    repeat = false;
                }
            } catch (Exception e) {
                System.out.println("El mes ingresado no es correcto");
            }
        }
        repeat = true;
        System.out.print("\nIngrese dia o presionar enter en caso de no querer modificar este campo ");
        temp = sc.nextLine();
        if (temp.length() == 0) {
            day = Byte.parseByte(actualDate[0]);
            repeat = false;
        } else {
            exit = 1;
        }
        while (repeat) {
            if (exit != 1) {
                System.out.print("\nIngrese dia ");
                temp = sc.nextLine();
            }
            exit = 0;
            try {
                day = Byte.parseByte(temp);
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (day < 1 || day > 31) {
                        System.out.println("El dia ingresado no es valido");
                    } else {
                        repeat = false;
                    }
                } else if (month == 2) {
                    if (leap) {
                        if (day < 1 || day > 29) {
                            System.out.println("El dia ingresado no es valido");
                        } else {
                            repeat = false;
                        }
                    } else {
                        if (day < 1 || day > 28) {
                            System.out.println("El dia ingresado no es valido");
                        } else {
                            repeat = false;
                        }
                    }
                } else {
                    if (day < 1 || day > 30) {
                        System.out.println("El dia ingresado no es valido");
                    } else {
                        repeat = false;
                    }
                }
            } catch (Exception e) {
                System.out.println("El dia ingresado no es correcto");
            }
        }
        return (day + "/" + month + "/" + year);
    }
}
