import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**/
public class Registers {
	static File file = null;
	static Scanner sc = new Scanner(System.in);
	static ArrayList<String> dataType = new ArrayList<String>();
	static ArrayList<String> column = new ArrayList<String>();
	static ArrayList<Integer> size = new ArrayList<Integer>();
	
	/* Aqui se muestra lo que el usuario puede realizad con la db para llamar al respectivo metodo*/
    public static boolean subMenu(String db, String directory) {
    	System.out.println("\t1 - Estructurar Base de Datos");
    	System.out.println("\t2 - Describir Base de Datos");
    	System.out.println("\t3 - Insertar registros a Base de Datos");
    	System.out.println("\t4 - Mostrar registros de Base de Datos");
    	System.out.println("\t5 - Buscar registros de Base de Datos");
    	System.out.println("\t6 - Modificar registros de Base de Datos");
    	System.out.println("\t7 - Eliminar registros de Base de Datos");
    	System.out.println("\t8 - Regresar al menu anterior");
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
	    	Input.show(directory, db);
	    	break;
	    case "5":
	    	value(directory, db, optRegisters);
	    	break;
	    case "6":
	    	value(directory, db, optRegisters);
	    	break;
	    case "7":
	    	value(directory, db, optRegisters);
	    	break;
	    case "8":
	    	//FirstStepsDB.menu();
	    	break;
	    default:
	    	System.out.println("La Option ingresado no es valida");
	    	break;
	    }
	    if (optRegisters.equals("8"))
	    	return false;
	    else
	    	return true;
    }
    
    /* Aqui se solicita los tipos de datos que el usuario necesita para crear las respectivas columnas de la db a estructurar */
    public static boolean estruct(String db, String directory) {
    	//System.out.println(db.substring(10));
    	String temp;
    	boolean repeat = true;
    	int sizeTemp = 0;
    	//file = new File(db);
    	do {
    		System.out.print("\n\nNombre de columna: ");
    		temp = sc.nextLine();
    		if (temp.equals("*CORRELATIVO*")) {
    			System.out.println("El nombre de columna no puede ser utilizado, ya que el nombre:\n"
    					+ "*CORRELATIVO* se encuentra reservado\n");
    		} else {
    			column.add(temp);
    		}
    	} while(temp.equals("*CORRELATIVO*"));
    	System.out.println("=== TIPOS DE DATOS ===");
    	System.out.println("0 - Date");
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
	    case "0":
	    	String style;
			repeat = true;
			do {
				System.out.println("\n1 - DD/MM/YY");
				System.out.println("2 - DD/MM/YYYY");
				style = sc.nextLine();
				if(style.equals("1") || style.equals("2"))
					repeat = false;
				else {
					System.out.println("La opcion ingresada no es valida\n");
				}
			} while (repeat);
	    	if (style.equals("1")) {
	    		dataType.add("a");
	    		size.add(8);
	    	} else {
	    		dataType.add("b");
	    		size.add(10);
	    	}
	    	break;
		case "1":
			dataType.add("1");
			size.add(1);
			break;
		case "2":
			dataType.add("2");
			size.add(1);
			break;
		case "3":
			repeat = true;
			do {
				System.out.print("\nIngrese la cantidad de caracteres que pueden ser utilizados: ");
				temp = sc.nextLine();
				if (temp.length() > 0 && temp.length() < 11) {
					try {
						sizeTemp = Integer.parseInt(temp);
						if (sizeTemp > 0) {
							repeat = false;
						} else {
							System.out.println("El tamaño ingresado no es valido");
						}
					} catch(Exception e) {
						System.out.println("El valor ingresado no es valido");
					}
				} else {
					System.out.println("El tamaño ingresado no es valido");
				}
			} while(repeat);
			dataType.add("3");
			size.add(sizeTemp);
			break;
		case "4":
			dataType.add("4");
			size.add(validateSizeNumbers(4));
			break;
		case "5":
			dataType.add("5");
			size.add(validateSizeNumbers(6));
			break;
		case "6":
			dataType.add("6");
			size.add(validateSizeNumbers(11));
			break;
		case "7":
			dataType.add("7");
			size.add(validateSizeNumbers(20));
			break;
		case "8":
			dataType.add("8");
			size.add(validateSizeNumbers(11));
			break;
		case "9":
			dataType.add("9");
			size.add(validateSizeNumbers(20));
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
    			index(column, dataType, size, directory, db);
    			return false;
    		}
    		else
    			System.out.println("\nEl valor ingresado no es valido");
    	} while(!exit.equalsIgnoreCase("y") && !exit.equalsIgnoreCase("n"));
		
    	return false;
    }
    
    public static int validateSizeNumbers(int size) {
    	boolean repeat = true;
    	String temp;
    	int sizeTemp = 0;
		do {
			System.out.println("\nIngrese la cantidad de caracteres que pueden ser utilizados: ");
			temp = sc.nextLine();
			if (temp.length() > 0 && temp.length() < 11) {
				try {
					sizeTemp = Integer.parseInt(temp);
					if(sizeTemp > 0 && sizeTemp < size) {
						repeat = false;
					} else {
						System.out.println("El valor ingresado no es valido para el tipo de dato seleccionado");
					}
				} catch(Exception e) {
					System.out.println("El valor ingresado no es valido");
				}
			} else {
				System.out.println("El tamaño ingresado no es valido");
			}
		} while(repeat);
		return sizeTemp;
    }
    
    /* Aqui se genera un archivo llamado "." + "nombre db" donde se almacena el nombre de las columnas y el tipo de dato que almacenara cada columna */
    public static void index(ArrayList<String> column, ArrayList<String> dataType, ArrayList<Integer> size, String directory, String db) {
    	for (int t: size) {
    		System.out.println(t);
    	}
    	File index1 = new File(directory+"."+db.substring(10));
    	RandomAccessFile raf = null;
    	try {
			raf = new RandomAccessFile(index1, "rw");
			raf.seek(0);
			for (short i = 0; i < column.size(); i++) {
				//System.out.println(dataType.get(i) + " " + column.get(i));
    			raf.writeUTF(dataType.get(i));
    			raf.writeUTF(column.get(i));
    			raf.writeInt(size.get(i));
    		} raf.close();
    	} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
    
    /* Aqui se mide el tamano de la db respecto al tamano que ocupa cada registro para poder calcular si es posible utilizar las opciones de 
     * buscar, modificar y eliminar*/
    public static boolean value(String directory, String db, String optRegister) {
    	int find = 0;
    	boolean control = true;
    	boolean exists = true;
    	//System.out.println(Input.spot(directory, db));
    	if (Input.spot(directory, db) > 0) {
    		do {
	    		System.out.print("Ingrese el numero de CORRELATIVO: ");
	    		try {
	    			find = Integer.parseInt(sc.nextLine());
	    			find--;
	    			if (find >= 0) {
	    				control = false;
	    			} else {
	    				System.out.println("El numero ingresado no es valido\n");
	    			}
	    		} catch(Exception e) {
	    			System.out.println("El numero ingresado no es valido**\n");
	    		}
	    	} while(control);
			long num = Input.spot(directory, db);
			num *= (find);
			if(!optRegister.equals("5")) {
				File index1 = new File(directory+"."+db.substring(10));
				if (index1.exists()) {
			    	RandomAccessFile guide = null;
			    	ArrayList<String> column = new ArrayList<String>();
			    	ArrayList<String> opt = new ArrayList<String>();
			    	ArrayList<Integer> size = new ArrayList<Integer>();
			    	try {
			    		guide = new RandomAccessFile(index1, "r");
			    		guide.seek(0);
			    		while(true) {
			    			opt.add(guide.readUTF());
			    			//System.out.println(guide.readUTF());
			    			column.add(guide.readUTF());
			    			//System.out.println(guide.readUTF());
			    			size.add(guide.readInt());
			    		}
			    	} catch (EOFException e) {
			    	} catch (IOException e) {
			    		System.out.println(e.getMessage());
			    	}
					if (optRegister.equals("6")){
						if (Input.find(directory, db, num)) {
							Modify.modify(directory, db, column, opt, size, num);
							exists = true;
						} else {
							exists = false;
						}
					} else {
						if (Input.find(directory, db, num)) {
							Delete.delete(directory, db, column, opt, size, num);
							exists = true;
						} else {
							exists = false;
						}
					}
				}
			}
			if (optRegister.equals("5")) {
				if (Input.find(directory, db, num)) {
					exists = true;
				} else {
					exists = false;
				}
			} 
    	} else {
    		System.out.println("...");
    	}
    	return exists;
    }
}
