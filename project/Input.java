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
    			//System.out.println(type + " % " + column);
    			switch(type) {
    			// Solicita booleano
    		    case "a":
    		    	if (opt.equals("2")) {
    					System.out.println(column + " - " + "Date YY/MM/DD");
    				} else if (opt.equals("3")) {
    					System.out.println("\n" + column + ": ");
						String inDate = validateDate("1");
    					while(inDate.length() != 8) {
								inDate += " ";
						}
    					raf.writeUTF(inDate);
    					//System.out.println(inDate.length());
					}
    		    	break;
    		    case "b":
    		    	if (opt.equals("2")) {
    					System.out.println(column + " - " + "Date YYYY/MM/DD");
    				} else if (opt.equals("3")) {
    					System.out.println("\n" + column + ": ");
						String inDate = validateDate("2");
    					while(inDate.length() != 10) {
								inDate += " ";
						}
    					raf.writeUTF(inDate);
    					//System.out.println(inDate.length());
					}
    		    	break;
    			case "1":
    				
    				/*byte 	‐128 to 127
					(i.e., ‐27 to 27 – 1) 	8 bits (Two’s Complement)
					short 	‐32768 to 32767
					(i.e., ‐215 to 215 – 1) 	16 bits (Two’s Complement)
					int 	‐2147483648 to 2147483647
					(i.e., ‐231 to 231 – 1) 	32 bits (Two’s Complement)
					long 	‐9223372036854775808 to 9223372036854775807
					(i.e., -263 to 263 – 1) 	64 bits (Two’s Complement)
					float 	Negative range: ‐3.4028235E+38 to ‐1.4E‐45
					Positive range: 1.4E‐45 to 3.4028235E+38 	32 bits (IEEE 754 Notation)
					double 	Negative range: ‐1.7976931348623157E+308 to ‐4.9E‐324
					Positive range: 4.9E‐324 to 1.7976931348623157E+308 	64 bits (IEEE 754 Notation)*/
    				
    				
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "boolean");
    				} else if (opt.equals("3")) {
    					repeat = false;
    					boolean inBoolean;
    					System.out.println("Insertar boolean");
    					//boolean inBoolean = true;
    					do {
    						System.out.println(column + ": ");
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
    					System.out.println(column + " - " + "char");
    				} else if (opt.equals("3")) {

        				repeat = true;
        				char inChar;
    					System.out.println("Insertar char");
        				//char inChar = '*';
    					do {
        					try {
	    						System.out.print(column + ": ");
	    						temp = sc.nextLine();
	    						inChar = temp.charAt(0);
	    						repeat = false;
        					} catch (Exception e) {
        						inChar = '*';
        					}
    					}  while(repeat);
    					raf.writeChar(inChar);
    				}
    				break;
    			// String maximo de 50 caracteres, si es menos de 50 concatena espacios si es mas de 50 vuelve a solicitar
    			case "3":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "String");
    				} else if (opt.equals("3")) {
    					String inString;
    					System.out.println("Insertar String (Max 50)");
    					do {
        					System.out.print(column + ": ");
    						inString = sc.nextLine();
    					} while (inString.length() == 0 || inString.length() > 50);
    					while (inString.length() < 50) {
    						inString +=" ";
    					}
    					raf.writeUTF(inString);
    				}
    				break;
    			// solicita byte
    			case "4":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "byte");
    				} else if (opt.equals("3")) {
        				repeat = true;
        				byte inByte;
    					System.out.println("Insertar byte");
    					do {
        					try {
        						System.out.print(column + ": ");
        						temp = sc.nextLine();
        						inByte = Byte.parseByte(temp);
        						repeat = false;
        					} catch (Exception e) {
        						inByte = 0;
        					}
    					} while(repeat);
    					raf.writeByte(inByte);
    				}
    				break;
    			// solicta short
    			case "5":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "short");
    				} else if (opt.equals("3")) {
    					repeat = true;
        				short inShort;
    					System.out.println("Insertar short");
    					do{
    						try {
    							System.out.print(column + ": ");
        						temp = sc.nextLine();
        						inShort = Short.parseShort(temp);
        						repeat = false;
    						} catch (Exception e) {
        						inShort = 0;
    						}
    					} while(repeat);
    					raf.writeShort(inShort);
    				}
    				break;
    			// soliicta int
    			case "6":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "int");
    				} else if (opt.equals("3")) {
    					repeat = true;
        				int inInt;
    					System.out.println("Insertar int");
    					do {
    						try {
    							System.out.print(column + ": ");
        						temp = sc.nextLine();
        						inInt = Integer.parseInt(temp);
        						repeat = false;
    						}catch (Exception e) {
        						inInt = 0;
    						}
    					} while (repeat);
    					raf.writeInt(inInt);
    				}
    				break;
    			// solicita long
    			case "7":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "long");
    				} else if (opt.equals("3")) {
    					repeat = true;
        				long inLong;
    					System.out.println("Insertar long");
    					do {
    						try {
    							System.out.print(column + ": ");
    							temp = sc.nextLine();
        						inLong = Long.parseLong(temp);
        						repeat = false;
    						}catch (Exception e) {
        						inLong = 0;
    						}
    					} while (repeat);
    					raf.writeLong(inLong);
    				}
    				break;
    			// solicita float
    			case "8":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "float");
    				} else if (opt.equals("3")) {
    					repeat = true;
        				float inFloat;
    					System.out.println("Insertar float");
    					do {
    						try {
    							System.out.print(column + ": ");
    							temp = sc.nextLine();
        						inFloat = Float.parseFloat(temp);
        						repeat = false;
    						}catch (Exception e) {
        						inFloat = 0;
    						}
    					} while (repeat);
    					raf.writeFloat(inFloat);
    				}
    				break;
    			// solicita double
    			case "9":
    				if (opt.equals("2")) {
    					System.out.println(column + " - " + "double");
    				} else if (opt.equals("3")) {
    					repeat = true;
        				double inDouble;
    					System.out.println("Insertar double");
    					do {
    						try {
    							System.out.print(column + ": ");
    							temp = sc.nextLine();
        						inDouble = Double.parseDouble(temp);
        						repeat = false;
    						}catch (Exception e) {
        						inDouble = 0;
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
	    	try {
	    		guide = new RandomAccessFile(index1, "r");
	    		guide.seek(0);
	    		while(true) {
	    			index.add(guide.readUTF());
	    			opt.add(guide.readUTF());
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
	    			    	System.out.println(opt.get(i) + ": " + raf.readUTF());
	    			    	break;
	    			    case "b":
	    			    	System.out.println(opt.get(i) + ": " + raf.readUTF());
	    			    	break;
	    				case "1":
	    					System.out.println(opt.get(i) + ": " + raf.readBoolean());
	    					break;
	    				case "2":
	    					System.out.println(opt.get(i) + ": " + raf.readChar());
	    					break;
	    				case "3":
	    					System.out.println(opt.get(i) + ": " + raf.readUTF());
	    					break;
	    				case "4":
	    					System.out.println(opt.get(i) + ": " + raf.readByte());
	    					break;
	    				case "5":
	    					System.out.println(opt.get(i) + ": " + raf.readShort());
	    					break;
	    				case "6":
	    					System.out.println(opt.get(i) + ": " + raf.readInt());
	    					break;
	    				case "7":
	    					System.out.println(opt.get(i) + ": " + raf.readLong());
	    					break;
	    				case "8":
	    					System.out.println(opt.get(i) + ": " + raf.readFloat());
	    					break;
	    				case "9":
	    					System.out.println(opt.get(i) + ": " + raf.readDouble());
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
		boolean exists = false;
		try {
			guide = new RandomAccessFile(index1, "r");
			guide.seek(0);
			while(true) {
				index.add(guide.readUTF());
				opt.add(guide.readUTF());
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
				    	System.out.println(opt.get(i) + ": " + raf.readUTF());
				    	break;
				    case "b":
				    	System.out.println(opt.get(i) + ": " + raf.readUTF());
				    	break;
			    	case "1":
			    		System.out.println(opt.get(i) + ": " + raf.readBoolean());
			    		break;
			    	case "2":
			    		System.out.println(opt.get(i) + ": " + raf.readChar());
			    		break;
			    	case "3":
			    		System.out.println(opt.get(i) + ": " + raf.readUTF());
			    		break;
			    	case "4":
			    		System.out.println(opt.get(i) + ": " + raf.readByte());
			    		break;
			    	case "5":
			    		System.out.println(opt.get(i) + ": " + raf.readShort());
			    		break;
			    	case "6":
			    		System.out.println(opt.get(i) + ": " + raf.readInt());
			    		break;
			    	case "7":
			    		System.out.println(opt.get(i) + ": " + raf.readLong());
			    		break;
			    	case "8":
			    		System.out.println(opt.get(i) + ": " + raf.readFloat());
			    		break;
			    	case "9":
			    		System.out.println(opt.get(i) + ": " + raf.readDouble());
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
