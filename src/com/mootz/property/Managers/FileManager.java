package com.mootz.property.Managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
	static boolean WriteObjectToFile(Object serObj, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
	
	static Object LoadObjectFromFile(String filePath) {	
		try {
			FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            objectIn.close();
            return obj;
		} catch (Exception ex) {
			return null;
		}
	}
	
	static boolean DeleteFile(String filePath) {
		try {
			Files.delete(Paths.get(filePath));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	static void RenameFile(String filePath, String newPath) {
		Object obj = LoadObjectFromFile(filePath);
		DeleteFile(filePath);
		WriteObjectToFile(obj, newPath);
	}
	
	static String[] GetAllFiles(String folderPath) {
		File path = new File(folderPath);
		return path.list();
	}
}
