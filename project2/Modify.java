import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/* Este metodo abre el archivo en la cantidad de byte segun el numero de correlativo que ingresa el usuario, solicita todos los campos pero si el
 * usuario unicamente presiona enter, el campo no es modificado */
public class Modify {
	public static void modify(String directory, String db, ArrayList<String> column, ArrayList<String> dataType, int num) {
		File current = new File(db);
		try {
			boolean repeat;
			String temp;
			Scanner sc = new Scanner(System.in);
			RandomAccessFile raf = new RandomAccessFile(current, "rw");
			raf.seek(num);
			raf.readBoolean();
			for (int i = 0; i < dataType.size(); i++) {
				//System.out.println("+++" + raf.getFilePointer() + "+++");
    			switch(dataType.get(i)) {
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
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
