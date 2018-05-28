import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/* 
 * Aqui se empieza a leer todos los registros a un archivo temporal mientras el byte del correlativo sea diferente al byte que se encuentra leyendo
 * en el archivo, si los byte son iguales entonces se escriben los registros en un archivo "." + "nombre db" + "_deleted".
 * Cuando se finaliza de leer el archivo, se borra el archivo origina, se renombra el archivo temporal al nombre de la db que fue borrada
 *  */
public class Delete {
	public static void delete(String directory, String db, ArrayList<String> column, ArrayList<String> dataType, long num) {
		File file = new File(db);
		File temp = new File(directory + "/." + db.substring(10) + "_temp");
		File deleted = new File(directory + "/." + db.substring(10) + "_deleted");
		try {
			RandomAccessFile raf = new RandomAccessFile(db, "r");
			RandomAccessFile rafTemp = new RandomAccessFile(temp, "rw");
			RandomAccessFile rafDeleted = new RandomAccessFile(deleted, "rw");
			raf.seek(0);
			rafTemp.seek(rafTemp.length());
			while (true) {
				if (raf.getFilePointer() != num) {
					rafTemp.writeBoolean(raf.readBoolean());
					for (int i = 0; i < dataType.size(); i++) {
						switch(dataType.get(i)) {
					    case "a":
					    	rafTemp.writeUTF(raf.readUTF());
					    	break;
					    case "b":
					    	rafTemp.writeUTF(raf.readUTF());
					    	break;
	    				case "1":
	    					rafTemp.writeBoolean(raf.readBoolean());
	    					break;
	    				case "2":
	    					rafTemp.writeChar(raf.readChar());
	    					break;
	    				case "3":
	    					rafTemp.writeUTF(raf.readUTF());
	    					break;
	    				case "4":
	    					rafTemp.writeByte(raf.readByte());
	    					break;
	    				case "5":
	    					rafTemp.writeShort(raf.readShort());
	    					break;
	    				case "6":
	    					rafTemp.writeInt(raf.readInt());
	    					break;
	    				case "7":
	    					rafTemp.writeLong(raf.readLong());
	    					break;
	    				case "8":
	    					rafTemp.writeFloat(raf.readFloat());
	    					break;
	    				case "9":
	    					rafTemp.writeDouble(raf.readDouble());
	    					break;
	    				}
					}
				} else {
					rafDeleted.seek(rafDeleted.length());
					rafDeleted.writeBoolean(raf.readBoolean());
					for (int i = 0; i < dataType.size(); i++) {
						switch(dataType.get(i)) {
					    case "a":
					    	rafDeleted.writeUTF(raf.readUTF());
					    	break;
					    case "b":
					    	rafDeleted.writeUTF(raf.readUTF());
					    	break;
	    				case "1":
	    					rafDeleted.writeBoolean(raf.readBoolean());
	    					break;
	    				case "2":
	    					rafDeleted.writeChar(raf.readChar());
	    					break;
	    				case "3":
	    					rafDeleted.writeUTF(raf.readUTF());
	    					break;
	    				case "4":
	    					rafDeleted.writeByte(raf.readByte());
	    					break;
	    				case "5":
	    					rafDeleted.writeShort(raf.readShort());
	    					break;
	    				case "6":
	    					rafDeleted.writeInt(raf.readInt());
	    					break;
	    				case "7":
	    					rafDeleted.writeLong(raf.readLong());
	    					break;
	    				case "8":
	    					rafDeleted.writeFloat(raf.readFloat());
	    					break;
	    				case "9":
	    					rafDeleted.writeDouble(raf.readDouble());
	    					break;
	    				}
					}
				}
			}
		} catch (EOFException e) {
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		if (deleted.exists()) {
			file.delete();
			temp.renameTo(file);
			System.out.println("El registro ha sido borrado exitosamente");
		}
	}
}
