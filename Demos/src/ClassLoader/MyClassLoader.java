package ClassLoader;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author : solidSpoon
 * @date : 2021/3/5 1:30
 */

public class MyClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            Class<?> clazz = new  MyClassLoader().findClass("Hello");
            Object obj = clazz.getConstructor().newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File(this.getClass().getResource(name + ".xlass").getPath());
        byte[] fileByteArray = new byte[(int)f.length()];
        try {
            new FileInputStream(f).read(fileByteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileByteArray = decode(fileByteArray);
        String pack = this.getClass().getPackage().getName();
        return defineClass(pack + "." + name, fileByteArray, 0, fileByteArray.length);
    }
    /**
     * 将编码过的字节数组解码
     * @param fileToDecode 要解码的字节数组
     * @return 解码的字节数组
     */
    public byte[] decode (byte[] fileToDecode){
        for(int i=0; i< fileToDecode.length; i++){
            fileToDecode[i] = (byte) (255 - fileToDecode[i]);
        }
        return fileToDecode;
    }
}

