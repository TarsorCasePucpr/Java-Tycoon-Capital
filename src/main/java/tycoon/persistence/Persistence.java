package tycoon.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import tycoon.model.User;

public class Persistence {
    
    public static void saveUser(User user, String path) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(user);
        objOut.close();
        fileOut.close();
    }
    
    public static User loadUser(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        User user = (User) objIn.readObject();
        objIn.close();
        fileIn.close();
        return user;
    }
}
