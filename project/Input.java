import java.io.EOFException;
//import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;;

/* Este metodo es para insertar datos a la db por medio de los tipo de datos solicitados y tambien es para describir que tipo de datos almacena cada columna */
public class Input {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void call(String directory, String db, String opt) {
		File index1 = new File(directory+"."+db.substring(10));
		if (index1.exists()) {
			File current = new File(db);
	    	RandomAccessFile guide = null;
	    	RandomAccessFile raf = null;
	    	long scored = 0;
			boolean isEmpty = false;
	    	try{
	    		guide = new RandomAccessFile(index1, "r");
	    		raf = new RandomAccessFile(current, "rw");
	    		String type;
	    		String column;
	    		int size;
	    		String temp;
	    		boolean repeat;
	    		guide.seek(0);
	    		raf.seek(raf.length());
  			if (opt.equals("3")) {
  				System.out.println("*******************************");
    			if (raf.length() == 0) {
    				isEmpty = true;
    			} else {
    				isEmpty = false;
    			}
	    		raf.writeBoolean(true);
  			}	
			while(true) {
    			type =guide.readUTF();
    			column = guide.readUTF();
    			size = guide.readInt();
    			//System.out.println(type + " % " + column);
    			switch(type) {
    			// Solicita booleano
    		    case "a":
    		    	if (opt.equals("2")) {
    					System.out.println(column + " - " + "Date DD/MM/YY" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
    					System.out.println("\n" + column + ": ");
						String inDate = validateDate("1");
    					while(inDate.length() != size) {
								inDate += " ";
						}
    					raf.writeUTF(inDate);
    					//System.out.println(inDate.length());
					}
    		    	break;
    		    case "b":
    		    	if (opt.equals("2")) {
    					System.out.println(column + " - " + "Date DD/MM/YYYY" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
    					System.out.println("\n" + column + ": ");
						String inDate = validateDate("2");
    					while(inDate.length() != size) {
								inDate += " ";
						}
    					raf.writeUTF(inDate);
    					//System.out.println(inDate.length());
					}
    		    	break;
    			case "1":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "boolean" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
    					repeat = false;
    					boolean inBoolean;
    					System.out.println("Insertar boolean");
    					//boolean inBoolean = true;
    					do {
    						System.out.println("\n"+column + ": ");
    						System.out.println("\t1 - true");
    						System.out.println("\t2 - false");
    						temp = sc.nextLine();
    						if (temp.equals("1")) {
    							inBoolean = true;
    							repeat = false;
    						} else if (temp.equals("2")) {
    							inBoolean = false;
    							repeat = false;
    						} else {
    							System.out.println("El valor ingresado no es valido");
    							repeat = true;
    							inBoolean = true;
    						}
    					} while(repeat);
    					raf.writeBoolean(inBoolean);
    				} 
    				break;
    			// Solicita char
    			case "2":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "char" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
        				repeat = true;
        				char inChar = '*';
    					System.out.println("Insertar char");
        				//char inChar = '*';
    					do {
        					try {
	    						System.out.print(column + ": ");
	    						temp = sc.nextLine();
	    						if (temp.length() == 1) {
	    							inChar = temp.charAt(0);
		    						repeat = false;
	    						} else if(temp.length() == 0){
	    							System.out.println("El campo " + column + " no puede estar vacio\n");
	    						} else {
	    							System.out.println("El valor ingresado excede la cantidad de caracteres permitido\n");
	    						}
        					} catch (Exception e) {
        						System.out.println("El campo " + column + " no puede estar vacio\n");
        						inChar = '*';
        					}
    					}  while(repeat);
    					raf.writeChar(inChar);
    				}
    				break;
    			// String maximo de 50 caracteres, si es menos de 50 concatena espacios si es mas de 50 vuelve a solicitar
    			case "3":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "String" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
    					String inString;
    					System.out.println("\nInsertar String (Max "+ size +")");
    					do {
        					System.out.print(column + ": ");
    						inString = sc.nextLine();
    						if(inString.length() == 0) {
    							System.out.println("El campo " + column + " no puede estar vacio\n");
    						} else if(inString.length() > size) {
    							System.out.println("El valor ingresado excede el tamaño permitido\n");
    						}
    					} while (inString.length() == 0 || inString.length() > size);
    					while (inString.length() < size) {
    						inString +=" ";
    					}
    					raf.writeUTF(inString);
    				}
    				break;
    			// solicita byte
    			case "4":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "byte" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
        				repeat = true;
        				byte inByte = 0;
    					System.out.println("Insertar byte");
    					do {
    						System.out.print(column + ": ");
    						temp = sc.nextLine();
    						if (temp.length() != 0) {
    							if (temp.charAt(0) == '-' && temp.length() <= (size + 1)) {
        							try {
        								inByte = Byte.parseByte(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inByte = 0;
                					}
        						} else if (temp.length() <= size) {
        							try {
        								inByte = Byte.parseByte(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inByte = 0;
                					}
        						} else {
        							System.out.println("El valor ingresado excede la cantidad de digitos permitida\n");
        						}
    						} else {
    							System.out.println("El campo " + column + " no puede estar vacio");
    						}
    					} while(repeat);
    					raf.writeByte(inByte);
    				}
    				break;
    			// solicta short
    			case "5":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "short" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
    					repeat = true;
        				short inShort = 0;
    					System.out.println("Insertar short");
    					do{
    						System.out.print(column + ": ");
    						temp = sc.nextLine();
    						if (temp.length() != 0) {
    							if (temp.charAt(0) == '-' && temp.length() <= (size + 1)) {
        							try {
        								inShort = Short.parseShort(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inShort = 0;
                					}
        						} else if (temp.length() <= size) {
        							try {
        								inShort = Short.parseShort(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inShort = 0;
                					}
        						} else {
        							System.out.println("El valor ingresado excede la cantidad de digitos permitida\n");
        						}
    						} else {
    							System.out.println("El campo " + column + " no puede estar vacio");
    						}
    					} while(repeat);
    					raf.writeShort(inShort);
    				}
    				break;
    			// soliicta int
    			case "6":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "int" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
    					repeat = true;
        				int inInt = 0;
    					System.out.println("Insertar int");
    					do {
    						System.out.print(column + ": ");
    						temp = sc.nextLine();
    						if (temp.length() != 0) {
    							if (temp.charAt(0) == '-' && temp.length() <= (size + 1)) {
        							try {
        								inInt = Integer.parseInt(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inInt = 0;
                					}
        						} else if (temp.length() <= size) {
        							try {
        								inInt = Integer.parseInt(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inInt = 0;
                					}
        						} else {
        							System.out.println("El valor ingresado excede la cantidad de digitos permitida\n");
        						}
    						} else {
    							System.out.println("El campo " + column + " no puede estar vacio");
    						}
    					} while (repeat);
    					raf.writeInt(inInt);
    				}
    				break;
    			// solicita long
    			case "7":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "long" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
    					repeat = true;
        				long inLong = 0;
    					System.out.println("Insertar long");
    					do {
    						System.out.print(column + ": ");
    						temp = sc.nextLine();
    						if (temp.length() != 0) {
    							if (temp.charAt(0) == '-' && temp.length() <= (size + 1)) {
        							try {
        								inLong = Long.parseLong(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inLong = 0;
                					}
        						} else if (temp.length() <= size) {
        							try {
        								inLong = Long.parseLong(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inLong = 0;
                					}
        						} else {
        							System.out.println("El valor ingresado excede la cantidad de digitos permitida\n");
        						}
    						} else {
    							System.out.println("El campo " + column + " no puede estar vacio");
    						}
    					} while (repeat);
    					raf.writeLong(inLong);
    				}
    				break;
    			// solicita float
    			case "8":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "float" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
    					repeat = true;
        				float inFloat = 0;
    					System.out.println("Insertar float");
    					do {
    						System.out.print(column + ": ");
    						temp = sc.nextLine();
    						if (temp.length() != 0) {
    							if (temp.charAt(0) == '-' && temp.length() <= (size + 1)) {
        							try {
        								inFloat = Float.parseFloat(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inFloat = 0;
                					}
        						} else if (temp.length() <= size) {
        							try {
        								inFloat = Float.parseFloat(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inFloat = 0;
                					}
        						} else {
        							System.out.println("El valor ingresado excede la cantidad de digitos permitida\n");
        						}
    						} else {
    							System.out.println("El campo " + column + " no puede estar vacio");
    						}
    					} while (repeat);
    					raf.writeFloat(inFloat);
    				}
    				break;
    			// solicita double
    			case "9":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "double" + " - [" + size + "]");
    				} else if (opt.equals("3")) {
    					repeat = true;
        				double inDouble = 0;
    					System.out.println("Insertar double");
    					do {
    						System.out.print(column + ": ");
    						temp = sc.nextLine();
    						if (temp.length() != 0) {
    							if (temp.charAt(0) == '-' && temp.length() <= (size + 1)) {
        							try {
        								inDouble = Double.parseDouble(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inDouble = 0;
                					}
        						} else if (temp.length() <= size) {
        							try {
        								inDouble = Double.parseDouble(temp);
                						repeat = false;
                					} catch (Exception e) {
                						System.out.println("El valor ingresado no es valido\n");
                						inDouble = 0;
                					}
        						} else {
        							System.out.println("El valor ingresado excede la cantidad de digitos permitida\n");
        						}
    						} else {
    							System.out.println("El campo " + column + " no puede estar vacio");
    						}
    					} while (repeat);
    					raf.writeDouble(inDouble);
    				}
    				break;
    			}
    			if(isEmpty) {
    				System.out.println(raf.length());
    				scored = raf.length();
    			} /*else {
    				System.out.println(raf.length() + "***");
    			}*/
    		}
	    	} catch (EOFException e) {
	    	} catch (IOException e) {
	    		System.out.println(e.getMessage());
	    	} System.out.println();
	    	if (isEmpty) {
	    		System.out.println("\n\n");
		    	try {
		    		/*
		    		 *  Esta parte es la que calcula la cantidad de byte que ocupa cada registro y crea un archivo llamado "." + "nombre db" + "_index"
		    		 *   donde almacena esta cantidad
		    		 * */
		    		RandomAccessFile index2 = new RandomAccessFile(directory+"."+db.substring(10) + "_index", "rw");
		    		index2.seek(0);
		    		index2.writeLong(scored);
		    		index2.close();
		    	} catch(IOException e){
		    	}
	    	}
		} else {
			System.out.println("Es necesario primero estructurar el archivo para poder utilizar esta opcion\n\n");
		}
  }
  
	// Esta funcion retorna la cantidad de byte que ocupa cada registro
	public static long spot (String directory, String db) {
		File index1 = new File(directory+"."+db.substring(10)+"_index");
		long num = 0;
		try {  
			RandomAccessFile raf = new RandomAccessFile(index1, "r");
			raf.seek(0);
			num = raf.readLong();
			raf.close();
		} catch (IOException e) {
			System.out.println("Es necesario primero estructurar el archivo y luego insertar registros para poder utilizar esta opcion\n\n");
		}
		return num;
	}
	
	// Aqui se listan todos los registros almacenados en la db
  public static void show(String directory, String db) {
  	File index1 = new File(directory+"."+db.substring(10));
		if (index1.exists()) {
			File current = new File(db);
	    	RandomAccessFile guide = null;
	    	RandomAccessFile raf = null;
	    	ArrayList<String> index = new ArrayList<String>();
	    	ArrayList<String> opt = new ArrayList<String>();
	    	ArrayList<Integer> size = new ArrayList<Integer>();
	    	try {
	    		guide = new RandomAccessFile(index1, "r");
	    		guide.seek(0);
	    		while(true) {
	    			index.add(guide.readUTF());
	    			opt.add(guide.readUTF());
	    			size.add(guide.readInt());
	    		} 
	    	} catch (EOFException e) {
	    	} catch (IOException e) {
	    		System.out.println(e.getMessage());
	    	}
	    	try {
	    		raf = new RandomAccessFile(current, "r");
	    		raf.seek(0);
	    		int cont =0;
	    		while(true) {
	    			raf.readBoolean();
	    			cont++;
	    			System.out.println("*CORRELATIVO " + cont + "*");
	    			for(byte i = 0; i < index.size(); i++) {
	    				// segun el tipo de dato que selecciono el usuario asi se leen los registros
	    				switch(index.get(i)) {
	    			    case "a":
	    			    	System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readUTF());
	    			    	break;
	    			    case "b":
	    			    	System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readUTF());
	    			    	break;
	    				case "1":
	    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readBoolean());
	    					break;
	    				case "2":
	    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readChar());
	    					break;
	    				case "3":
	    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readUTF());
	    					break;
	    				case "4":
	    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readByte());
	    					break;
	    				case "5":
	    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readShort());
	    					break;
	    				case "6":
	    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readInt());
	    					break;
	    				case "7":
	    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readLong());
	    					break;
	    				case "8":
	    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readFloat());
	    					break;
	    				case "9":
	    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readDouble());
	    					break;
	    				}
	    			}
					System.out.println();
	    		}
	    	} catch (EOFException e) {
	    	} catch (IOException e) {
	    		System.out.println(e.getMessage());
	    	}
		} else {
			System.out.println("Es necesario primero estructurar el archivo y luego insertar registros para poder utilizar esta opcion\n\n");
		}
  }
		
  /* Este metodo busca los registros segun el numero de correlativo */
  public static boolean find(String directory, String db, long num) {
  	File index1 = new File(directory+"."+db.substring(10));
		File current = new File(db);
		RandomAccessFile guide = null;
		RandomAccessFile raf = null;
		ArrayList<String> index = new ArrayList<String>();
		ArrayList<String> opt = new ArrayList<String>();
		ArrayList<Integer> size = new ArrayList<Integer>();
		boolean exists = false;
		try {
			guide = new RandomAccessFile(index1, "r");
			guide.seek(0);
			while(true) {
				index.add(guide.readUTF());
				opt.add(guide.readUTF());
				size.add(guide.readInt());
			} 
		} catch (EOFException e) {
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			raf = new RandomAccessFile(current, "r");
			//System.out.println(num + "---" + raf.length());
			if (num < raf.length()) {
			    raf.seek(num);
			    //int cont =0;
			    raf.readBoolean();
			    //cont++;
			    //System.out.println("*CORRELATIVO* " + cont);
			    for(byte i = 0; i < index.size(); i++) {
			    	//System.out.println("***"+raf.getFilePointer()+"***");
			    	switch(index.get(i)) {
			    	case "a":
    			    	System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readUTF());
    			    	break;
    			    case "b":
    			    	System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readUTF());
    			    	break;
    				case "1":
    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readBoolean());
    					break;
    				case "2":
    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readChar());
    					break;
    				case "3":
    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readUTF());
    					break;
    				case "4":
    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readByte());
    					break;
    				case "5":
    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readShort());
    					break;
    				case "6":
    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readInt());
    					break;
    				case "7":
    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readLong());
    					break;
    				case "8":
    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readFloat());
    					break;
    				case "9":
    					System.out.println(opt.get(i) + "(" + size.get(i) + ") " + "- " + raf.readDouble());
    					break;
			    	}
			    }
			    exists = true;
			} else {
				System.out.println("El numero de CORRELATIVO ingresado no existe");
				exists = false;
			}
			System.out.println();
		} catch (EOFException e) {
		} catch (IOException e) {
		   	System.out.println(e.getMessage());
		}
		return exists;
	}
  
  public static String validateDate(String style) {
  	boolean leap = false;
		boolean repeat = true;
		String temp;
		int year = 0;
		byte month = 0, day = 0;
		do {
			System.out.print(style.equals("1") ? "Año [YY]: " : "Año [YYYY]: ");
			temp = sc.nextLine();
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
			} catch(Exception e) {
				System.out.println("El año ingresado no es correcto");
			}
		} while (repeat);
		repeat = true;
		do {
			System.out.print("\nMes: ");
			temp = sc.nextLine();
			try {
				month = Byte.parseByte(temp);
				if (!(month > 0 && month < 13)) {
					System.out.println("El mes ingresado no es valido");
				} else {
					repeat = false;	
				}
			} catch(Exception e) {
				System.out.println("El mes ingresado no es correcto");
			}
		} while (repeat);
		repeat = true;
		do {
			System.out.print("\nDia: ");
			temp = sc.nextLine();
			try {
				day = Byte.parseByte(temp);
				if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
					if (day < 1 || day > 31) {
						System.out.println("El dia ingresado no es valido");
					} else {
						repeat = false;
					}
				} else if (month == 2){
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
			} catch(Exception e) {
				System.out.println("El dia ingresado no es correcto");
			}
		} while (repeat);
		//Date date = new GregorianCalendar(year, month, day).getTime();
		return (day + "/" + month + "/" + year);
  }
}

