//package project2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Registers {
	static File file = null;
	static Scanner sc = new Scanner(System.in);
	static ArrayList<String> dataType = new ArrayList<String>();
	static ArrayList<String> column = new ArrayList<String>();
	
    public static boolean subMenu(String db, String directory) {
    	System.out.println("1 - Estructurar Base de Datos");
    	System.out.println("2 - Describir Base de Datos");
    	System.out.println("3 - Insertar registros a Base de Datos");
    	System.out.println("4 - Mostrar registros de Base de Datos");
    	System.out.println("5 - Regresar al menu anterior");
    	String optRegisters = sc.nextLine();
    	//String optRegisters = "4";
	    switch (optRegisters) {
	    case "1":
	    	file = new File(db);
	    	if (file.length() == 0) {
	    		do {
		    	} while (estruct(db, directory));
	    	} else {
	    		System.out.println("El archivo ya cuenta con datos almacenados, no es posible modificar la estructura");
	    	}
	    	break;
	    case "2":
	    	Input.call(directory, db, optRegisters);
	    	break;
	    case "3":
	    	Input.call(directory, db, optRegisters);
	    	break;
	    case "4":
	    	Input.call(directory, db, optRegisters);
	    	break;
	    case "5":
	    	//FirstStepsDB.menu();
	    	break;
	    default:
	    	System.out.println("La Option ingresado no es valida");
	    	break;
	    }
	    if (optRegisters.equals("5"))
	    	return false;
	    else
	    	return true;
    }
    
    public static boolean estruct(String db, String directory) {
    	System.out.println(db.substring(10));
    	//file = new File(db);
    	System.out.print("Nombre de columna: ");
		column.add(sc.nextLine());
    	System.out.println("=== TIPOS DE DATOS ===");
		System.out.println("1 - boolean");
		System.out.println("2 - char");
		System.out.println("3 - String");
		System.out.println("4 - byte");
		System.out.println("5 - short");
		System.out.println("6 - int");
		System.out.println("7 - long");
		System.out.println("8 - float");
		System.out.println("9 - double");
		String type = sc.nextLine();
		switch(type) {
		case "1":
			dataType.add("1");
			break;
		case "2":
			dataType.add("2");
			break;
		case "3":
			dataType.add("3");
			break;
		case "4":
			dataType.add("4");
			break;
		case "5":
			dataType.add("5");
			break;
		case "6":
			dataType.add("6");
			break;
		case "7":
			dataType.add("7");
			break;
		case "8":
			dataType.add("8");
			break;
		case "9":
			dataType.add("9");
			break;
		default:
			System.out.println("El valor ingesado no es valido");
			column.remove(column.size()-1);
		} 
		String exit;
		do {
    		System.out.print("Agregar otro registro [y/n] ");
    		exit = sc.nextLine();
    		if(exit.equalsIgnoreCase("y"))
    			return true;
    		else if(exit.equalsIgnoreCase("n")) {
    			index(column, dataType, directory, db);
    			//****
    			//
    			//***
    			return false;
    		}
    		else
    			System.out.println("El valor ingresado no es valido");
    	} while(!exit.equalsIgnoreCase("y") && !exit.equalsIgnoreCase("n"));
		
    	return false;
    }
    
    public static void index(ArrayList<String> column, ArrayList<String> dataType, String directory, String db) {
    	File index1 = new File(directory+"."+db.substring(10));
    	RandomAccessFile raf = null;
    	try {
			raf = new RandomAccessFile(index1, "rw");
			raf.seek(0);
			for (short i = 0; i < column.size(); i++) {
				//System.out.println(dataType.get(i) + " " + column.get(i));
    			raf.writeByte(Byte.parseByte(dataType.get(i)));
    			raf.writeUTF(column.get(i));
    		} raf.close();
    	} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
}
