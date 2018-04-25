//package project2;

import java.io.EOFException;
//import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;;

public class Input {
	public static void call(String directory, String db, String opt) {
		File index1 = new File(directory+"."+db.substring(10));
		if (index1.exists()) {
			File current = new File(db);
	    	RandomAccessFile guide = null;
	    	RandomAccessFile raf = null;
	    	int scored = 0;
	    	try{
	    		guide = new RandomAccessFile(index1, "r");
	    		raf = new RandomAccessFile(current, "rw");
	    		Scanner sc = new Scanner(System.in);
	    		byte type;
	    		String column;
	    		String temp;
	    		boolean repeat;
	    		guide.seek(0);
	    		raf.seek(raf.length());
	    		while(true) {
	    			type =guide.readByte();
	    			column = guide.readUTF();
	    			//System.out.println(type + " % " + column);
	    			switch(type) {
	    			case 1:
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
	    					scored += 1;
	    					raf.writeBoolean(inBoolean);
	    					//System.out.println(raf.getFilePointer());
	    			    	//System.out.println(scored);
	    				} /*else if (opt.equals("4")) {
	    					System.out.println(raf.readBoolean());
	    				}*/
	    				break;
	    			case 2:
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
	    					scored += 2;
	    					raf.writeChar(inChar);
	    					//System.out.println(raf.getFilePointer());
	    			    	//System.out.println(scored);
	    				} /*else if (opt.equals("4")) {
	    					System.out.println(raf.readChar());
	    				}*/
	    				break;
	    			case 3:
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
	    					scored += 52;
	    					raf.writeUTF(inString);
	    					//System.out.println(raf.getFilePointer());
	    			    	//System.out.println(scored);
	    				}/* else if (opt.equals("4")) {
	    					System.out.println(raf.readUTF());
	    				}*/
	    				break;
	    			case 4:
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
	    					scored += 1;
	    					raf.writeByte(inByte);
	    					//System.out.println(raf.getFilePointer());
	    			    	//System.out.println(scored);
	    				} /*else if (opt.equals("4")) {
	    					System.out.println(raf.readByte());
	    				}*/
	    				break;
	    			case 5:
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
	    					scored += 2;
	    					raf.writeShort(inShort);
	    					//System.out.println(raf.getFilePointer());
	    			    	//System.out.println(scored);
	    				} /*else if (opt.equals("4")) {
	    					System.out.println(raf.readShort());
	    				}*/
	    				break;
	    			case 6:
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
	    					scored += 4;
	    					raf.writeInt(inInt);
	    					//System.out.println(raf.getFilePointer());
	    			    	//System.out.println(scored);
	    				} /*else if (opt.equals("4")) {
	    					System.out.println(raf.readInt());
	    				}*/
	    				break;
	    			case 7:
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
	    					scored += 8;
	    					raf.writeLong(inLong);
	    					//System.out.println(raf.getFilePointer());
	    			    	//System.out.println(scored);
	    				} /*else if (opt.equals("4")) {
	    					System.out.println(raf.readLong());
	    				}*/
	    				break;
	    			case 8:
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
	    					scored += 4;
	    					raf.writeFloat(inFloat);
	    					//System.out.println(raf.getFilePointer());
	    			    	//System.out.println(scored);
	    				} /*else if (opt.equals("4")) {
	    					System.out.println(raf.readFloat());
	    				}*/
	    				break;
	    			case 9:
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
	    					scored += 8;
	    					raf.writeDouble(inDouble);
	    					//System.out.println(raf.getFilePointer());
	    			    	//System.out.println(scored);
	    				} /*else if (opt.equals("4")) {
	    					System.out.println(raf.readDouble());
	    				}*/
	    				break;
	    			}
	    		}
	    	} catch (EOFException e) {
	    	} catch (IOException e) {
	    		System.out.println(e.getMessage());
	    	} System.out.println("\n\n");
	    	//System.out.println(scored);
		} else {
			System.out.println("Es necesario primero estructurar el archivo y luego insertar registros para poder utilizar esta opcion\n\n");
		}
    }
    
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
	    			index.add(String.valueOf(guide.readByte()));
	    			opt.add(guide.readUTF());
	    		} 
	    	} catch (EOFException e) {
	    	} catch (IOException e) {
	    		System.out.println(e.getMessage());
	    	}
	    	try {
	    		raf = new RandomAccessFile(current, "r");
	    		raf.seek(0);
	    		while(true) {
	    			for(byte i = 0; i < index.size(); i++) {
	    				switch(index.get(i)) {
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
}
