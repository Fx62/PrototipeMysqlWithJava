import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/* Este metodo abre el archivo en la cantidad de byte segun el numero de correlativo que ingresa el usuario, solicita todos los campos pero si el
 * usuario unicamente presiona enter, el campo no es modificado */
public class Modify {

	static Scanner sc = new Scanner(System.in);
	
	public static void modify(String directory, String db, ArrayList<String> column, ArrayList<String> dataType, long num) {
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
    			switch(dataType.get(i)) {
    		    case "a":
    		    	start = raf.getFilePointer();
    		    	//System.out.println(start + "*");
    		    	dateTemp = raf.readUTF();
    		    	actualDate = dateTemp.split("/");
    		    	newDate = validateDate("1", actualDate);
    		    	while(newDate.length() != 8) {
						newDate += " ";
    		    	}
    		    	raf.seek(start);
    		    	raf.writeUTF(newDate);
    		    	//System.out.println(raf.getFilePointer()+"*");
    		    	break;
    		    case "b":
    		    	start = raf.getFilePointer();
    		    	//System.out.println(start + "*");
    		    	dateTemp = raf.readUTF();
    		    	actualDate = dateTemp.split("/");
    		    	newDate = validateDate("2", actualDate);
    		    	while(newDate.length() != 10) {
						newDate += " ";
    		    	}
    		    	raf.seek(start);
    		    	raf.writeUTF(newDate);
    		    	//System.out.println(raf.getFilePointer()+"*");
    		    	break;
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
        					}
        					else {
        						repeat = true;
        					}
    			
    				} while(repeat);
    			break;
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
					}  while(repeat);
					break;
    			case "3":
    				String inString;
					System.out.println("Insertar String o presionar enter para no modificar este campo");
					do {
    					System.out.print(column.get(i) + ": ");
						inString = sc.nextLine();
						if (inString.length() == 0) {
							raf.readUTF();
						}
					} while (inString.length() > 50);
					if (inString.length() > 0) {
						while (inString.length() < 50) {
							inString +=" ";
						}
						raf.writeUTF(inString);
					}
					break;
    			case "4":
    				repeat = true;
    				byte inByte;
					System.out.println("Insertar byte o presionar enter para no modificar este campo");
					do {
    					System.out.print(column.get(i) + ": ");
    					temp = sc.nextLine();
    					if (temp.length() == 0) {
    						raf.readByte();
    						repeat = false;
    					} else {
            				try {
            					inByte = Byte.parseByte(temp);
            					repeat = false;
            					raf.writeByte(inByte);
            				} catch (Exception e) {
    						}
    					}
					} while(repeat);
				break;
    			case "5":
    				repeat = true;
    				short inShort;
					System.out.println("Insertar short o presionar enter para no modificar este campo");
					do {
    					System.out.print(column.get(i) + ": ");
    					temp = sc.nextLine();
    					if (temp.length() == 0) {
    						raf.readShort();
    						repeat = false;
    					} else {
            				try {
            					inShort = Short.parseShort(temp);
            					repeat = false;
            					raf.writeShort(inShort);
            				} catch (Exception e) {
    						}
    					}
					} while(repeat);
				break;
    			case "6":
    				repeat = true;
    				int inInt;
					System.out.println("Insertar int o presionar enter para no modificar este campo");
					do {
    					System.out.print(column.get(i) + ": ");
    					temp = sc.nextLine();
    					if (temp.length() == 0) {
    						raf.readInt();
    						repeat = false;
    					} else {
            				try {
            					inInt = Integer.parseInt(temp);
            					repeat = false;
            					raf.writeInt(inInt);
            				} catch (Exception e) {
    						}
    					}
					} while(repeat);
				break;
    			case "7":
    				repeat = true;
    				long inLong;
					System.out.println("Insertar long o presionar enter para no modificar este campo");
					do {
    					System.out.print(column.get(i) + ": ");
    					temp = sc.nextLine();
    					if (temp.length() == 0) {
    						raf.readLong();
    						repeat = false;
    					} else {
            				try {
            					inLong = Long.parseLong(temp);
            					repeat = false;
            					raf.writeLong(inLong);
            				} catch (Exception e) {
    						}
    					}
					} while(repeat);
				break;
    			case "8":
    				repeat = true;
    				float inFloat;
					System.out.println("Insertar float o presionar enter para no modificar este campo");
					do {
    					System.out.print(column.get(i) + ": ");
    					temp = sc.nextLine();
    					if (temp.length() == 0) {
    						raf.readFloat();
    						repeat = false;
    					} else {
            				try {
            					inFloat = Float.parseFloat(temp);
            					repeat = false;
            					raf.writeFloat(inFloat);
            				} catch (Exception e) {
    						}
    					}
					} while(repeat);
				break;
    			case "9":
    				repeat = true;
    				double inDouble;
					System.out.println("Insertar double o presionar enter para no modificar este campo");
					do {
    					System.out.print(column.get(i) + ": ");
    					temp = sc.nextLine();
    					if (temp.length() == 0) {
    						raf.readDouble();
    						repeat = false;
    					} else {
            				try {
            					inDouble = Double.parseDouble(temp);
            					repeat = false;
            					raf.writeDouble(inDouble);
            				} catch (Exception e) {
    						}
    					}
					} while(repeat);
				break;

				}
    		}
		} catch(EOFException e) {
		}
		catch(IOException e) {
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
		System.out.print((style.equals("1") ? "Ingrese año en formato [YY]" : "Ingrese año en formato [YYYY]") + 
				" o presionar enter en caso de no querer modificar este campo ");
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
			} catch(Exception e) {
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
		while(repeat) {
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
			} catch(Exception e) {
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
		while(repeat) {
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
		}
		return (day + "/" + month + "/" + year);
	}
}
