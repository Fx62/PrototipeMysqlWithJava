
package project2;

import java.io.File;
import java.util.Scanner;

public class FirstStepsDB {

	static File file = null;
	static File[] listOfFiles = null;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean mn;
		do {
			 mn = menu();
		} while(!mn);

	}
	
	public static boolean menu() {
		boolean control;
		System.out.println("1 - SHOW DATABASE");
		System.out.println("2 - USE DATABASE");
		System.out.println("3 - CREATE DATABASE");
		System.out.println("4 - DROP DATABASE");
		try {
			String input = sc.nextLine();
			switch (input) {
			case "1":
				control = showDatabases();
				System.out.println(control ? "\n\n" : "Actualmente no existen bases de datos");
				break;
			case "2":
				break;
			case "3":
				control = createDatabase();
				System.out.println(control ? "La nueva base de datos fue creada exitosamente" :
					"");
				break;
			case "4":
				control = dropDatabase();
				System.out.println(control ? "La base de datos ha sido borrada"
						: "La base de datos indicada no existe");
				break;
			default:
				System.out.println("Unicamente son validos los valores del 1 al 4");
		}
		} catch (Exception e) {
			System.err.println("Unicamente son validos los valores del 1 al 4");
		}
		String repeat;
		System.out.println("Salir [y/N]");
		repeat = sc.nextLine();
		if(repeat.equalsIgnoreCase("y")) {
			return true;
		} else if(repeat.equalsIgnoreCase("n")){
			System.out.println("\n\n\n\n");
			return false;			
		} else {
			System.out.println("El valor ingresado no es valido\n\n");
			return false;
		}
	}

	public static boolean showDatabases(){
		file = new File("Databases");
		if(!file.isDirectory()) {
			file.mkdirs();
			return false;
		} else if (file.isDirectory()){
			listOfFiles = file.listFiles();
			for (File t: listOfFiles) {
				System.out.println(String.valueOf(t).substring(10));
			}
			if (listOfFiles.length == 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public static boolean createDatabase() {
		String db = "Databases/";
		System.out.println("Ingrese el nombre de la nueva base de datos");
		db += sc.nextLine();
		file = new File(db);
		if (file.exists()) {
			System.out.println("El archivo ya existe");
			return false;
		} else {
			try {
				file.createNewFile();
				return true;
			} catch (Exception e) {
				System.out.println("El nombre ingresado no es valido");
				return false;
			}
		}
	}
	
	public static boolean dropDatabase() {
		int i = 0;
		System.out.println(showDatabases() ? "Ingrese el nombre de la base de datos a borrar"
				: "No existen bases de datos para ser borradas");
		String delete;
		delete = sc.nextLine();
		for (File t: listOfFiles) {
			if (String.valueOf(t).substring(10).equals(delete)){
				System.out.println(t); 
				t.delete();
				break;
			} else {
				i++;
			}
		}
		if (i == listOfFiles.length) {
			return false;
		} else {
			return true;
		}
	}
}


