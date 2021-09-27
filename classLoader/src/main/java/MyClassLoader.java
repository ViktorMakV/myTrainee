import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Viktor Makarov
 */
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(name + ".class");
        //Проверка существования файла
        if (!file.isFile()) {
            throw new ClassNotFoundException("No such class: " + name);
        }

        //Получение имени класса
        int i = name.lastIndexOf(File.separatorChar);
        i++;
        String className = name.substring(i);

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            byte[] b = new byte[(int) file.length()];
            inputStream.read(b);
            //С помощью функции defineClass загружаем класс
            return defineClass(className, b, 0, b.length);
        } catch (Exception e) {
            throw new ClassNotFoundException("Bytecode read exception");
        }
    }
}
