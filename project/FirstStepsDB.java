import java.io.File;
import java.util.Scanner;

public class FirstStepsDB {

	static File file = null;
	static Scanner sc = new Scanner(System.in);
	final private static String directory = "Databases/";
	private static String dataBase;

	/* El metodo main llama al menu principal */
	public static void main(String[] args) {
		do {
		}while (menu());	
	}
	
	/* Este metodo muestra las funciones principales de crear, mostrar, utilizar y eliminar los archivos que seran utilizados como dbs */
	public static boolean menu() {
		boolean control;
		System.out.println("1 - Crear nueva Base de Datos");
		System.out.println("2 - Mostrar Bases de Datos");
		System.out.println("3 - Utilizar Base de Datos");
		System.out.println("4 - Eliminar Base de Datos");
		System.out.println("5 - Salir");
		String input = sc.nextLine();
		//String input = "2";
		switch (input) {
		case "1":
			createDatabase();
			break;
		case "2":
			control = showDatabases();
			System.out.println(control ? "\n" : "Actualmente no existen bases de datos\n\n");
			break;
		case "3":
			control = useDatabase();
			break;
		case "4":
			control = dropDatabase();
			System.out.println(control ? "La base de datos ha sido borrada\n\n"
					: "");
			break;
		case "5":
			break;
		default:
			System.out.println("Unicamente son validos los valores del 1 al 5\n\n");
		}
		if(input.equals("5"))
			return false;
		else
			return true;
	}

	public static boolean dir() {
		File dr = new File(directory);
		if(dr.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	/* Este metodo lista todos los archivos existentes que su nombre no empiece con punto en el directorio llamado Databases*/
	public static boolean showDatabases(){
		if (dir()) {
			File listDB = new File(directory);
			File[] dbs = listDB.listFiles();
			if (dbs.length == 0) {
				return false;
			} else {
				for(File t: dbs) {
					if (String.valueOf(t).charAt(10) != '.') {
						System.out.println(String.valueOf(t).substring(10));
					}
				}
				return true;
			}
		} else {
			return false;
		}
	}
	
	/* Este metodo crea el archivo vacio dentro del directorio Databases */
	public static void createDatabase() {
		file = new File(directory);
		if(!dir()) {
			file.mkdirs();
		}
		if (dir()) {
			String temp = "";
			String db = directory;
			
			do {
				System.out.println("Ingrese el nombre de la nueva base de datos");
				temp = sc.nextLine();
				if (temp.charAt(0) != '.') {
					db += temp;
					file = new File(db);
					if (file.exists()) {
						System.out.println("El archivo ya existe");
					} else {
						try {
							file.createNewFile();
							System.out.println("La base de datos fue creada exitosamente\n\n");
						} catch (Exception e) {
							System.out.println("Favor de validar los permisos del directorio Databases\n\n");
						}
					}
				} else {
					System.out.println("El nombre de la base de datos no puede iniciar con el signo .\n");
				}
			} while (temp.charAt(0) == '.');
		}
	}
	
	/* Es para verificar si el archivo exite o no, este metodo es utilizado para la opcion de utilizar y la opcion de eliminar */
	public static boolean existens() {
		boolean check = showDatabases();
		System.out.println(check ? "\nIngrese el nombre de la base de datos"
				: "No existen bases de datos actualmente\n\n");
		if(check) {
			String db = directory; 
			db += sc.nextLine();
			//db += "github";
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
	
	/*Aqui se manda a llamar el submenu para poder utilizar la db*/
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
	
	/* Aqui se borra la db y los archivos ocultos para el control del tipo de datos*/
	public static boolean dropDatabase() {
		if(existens()) {
			file = new File(dataBase);
			file.delete();
			File hided = new File(directory + "." +dataBase.substring(10));
			File index = new File(directory + "." +dataBase.substring(10) + "_index");
			File deleted = new File(directory + "." +dataBase.substring(10) + "_deleted");
			if(hided.exists()) {
				hided.delete();
			}
			if(index.exists()) {
				index.delete();
			}
			if(deleted.exists()) {
				deleted.delete();
}
			return true;
		} else {
			return false;
		}
	}
}
