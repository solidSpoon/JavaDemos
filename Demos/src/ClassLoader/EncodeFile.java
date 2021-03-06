package ClassLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author : solidSpoon
 * @date : 2021/3/5 1:57
 */
public class EncodeFile {

    public static void main(String[] args) {
        String name = "Hello";
        EncodeFile ef = new EncodeFile();
        byte[] fileByteArray = ef.loadFile(name);
        fileByteArray = ef.encode(fileByteArray);;
        ef.storeFile(fileByteArray, name);
    }
    public byte[] loadFile(String name){
        File f = new File(this.getClass().getResource(name + ".class").getPath());
        byte[] fileByteArray = new byte[(int)f.length()];
        try {
            new FileInputStream(f).read(fileByteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileByteArray;
    }
    void storeFile(byte[] fileByteArray, String name) {
        String p = this.getClass().getResource("").getPath();
        File file = new File(p + "/" + name + ".xlass");
        try (FileOutputStream fop = new FileOutputStream(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            fop.write(fileByteArray);
            fop.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public byte[] encode (byte[] fileToDecode){
        for(int i=0; i< fileToDecode.length; i++){
            fileToDecode[i] = (byte) (255 - fileToDecode[i]);
        }
        return fileToDecode;
    }
}