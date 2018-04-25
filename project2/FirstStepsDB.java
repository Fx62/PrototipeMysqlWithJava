//package project2;

import java.io.File;
import java.util.Scanner;

public class FirstStepsDB {

	static File file = null;
	static Scanner sc = new Scanner(System.in);
	final private static String directory = "Databases/";
	private static String dataBase;
	
	public String getDb() {
		return dataBase;
	}

	public void setDb(String dataBase) {
		FirstStepsDB.dataBase = dataBase;
	}
	
	public String getDirectory() {
		return directory;
	}

	public static void main(String[] args) {
		do {
		}while (menu());	
	}
	
	public static boolean menu() {
		boolean control;
		System.out.println("1 - Mostrar Bases de Datos");
		System.out.println("2 - Utilizar Base de Datos");
		System.out.println("3 - Crear nueva Base de Datos");
		System.out.println("4 - Eliminar Base de Datos");
		System.out.println("5 - Salir");
		String input = sc.nextLine();
		//String input = "2";
		switch (input) {
		case "1":
			control = showDatabases();
			System.out.println(control ? "\n\n" : "Actualmente no existen bases de datos");
			break;
		case "2":
			control = useDatabase();
			break;
		case "3":
			control = createDatabase();
			System.out.println(control ? "La nueva base de datos fue creada exitosamente" :
				"");
			break;
		case "4":
			control = dropDatabase();
			System.out.println(control ? "La base de datos ha sido borrada"
					: "");
			break;
		case "5":
			break;
		default:
			System.out.println("Unicamente son validos los valores del 1 al 5");
		}
		if(input.equals("5"))
			return false;
		else
			return true;
	}

	public static boolean showDatabases(){
		file = new File(directory);
		if(!file.isDirectory()) {
			file.mkdirs();
			return false;
		} else if (file.isDirectory()){
			File[] listOfFiles = file.listFiles();
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
		String db = directory;
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
	
	public static boolean existens() {
		boolean check = showDatabases();
		System.out.println(check ? "Ingrese el nombre de la base de datos"
				: "No existen bases de datos actualmente");
		if(check) {
			String db = directory; 
			db += sc.nextLine();
			//db += "prueba01#";
			file = new File(db);
			if(file.exists()) {
				dataBase = db;
				return true;
			} else {
				System.out.println("La base de datos indicada no existe");
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean useDatabase() {
		if(existens()) {
			file = new File(dataBase);
			do {
			} while (Registers.subMenu(dataBase, directory));
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean dropDatabase() {
		if(existens()) {
			file = new File(dataBase);
			file.delete();
			return true;
		} else {
			return false;
		}
	}
	
	
}
