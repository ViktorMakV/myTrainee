import java.lang.reflect.Method;

/**
 * @author Viktor Makarov
 */
public class Application {
    public static void main(String[] args) throws Exception {

        String nameOfClassToLoad = "HelloClass";

        //Получаем путь к текущему классу и формируем путь к классу для загрузки
        String currentClassLocation = Application.class.getResource("Application.class").toString();
        String classToLoadPath = replaceCurrentClassWithLoadingClass(Application.class.getName(),
                currentClassLocation, nameOfClassToLoad);

        MyClassLoader loader = new MyClassLoader();

        //Объявляем переменную типа Class.
        int indexOfDot = classToLoadPath.lastIndexOf(".");
        Class cl = loader.findClass(classToLoadPath.substring(0, indexOfDot));

        System.out.println(cl);

        //Вызов динамического метода
        //Создаем объект для вызова метода
        Method firstMethod = cl.getMethod("sayHello", String.class, int.class);
        firstMethod.invoke(cl.newInstance(), "Bobby", 2);

        //Вызов статического метода
        Method secondMethod = cl.getMethod("sayMyName");
        System.out.println((String) secondMethod.invoke(cl));
    }

    public static String replaceCurrentClassWithLoadingClass(String className, String pathToCurrentClass,
                                                             String classToLoad) {
        //Изменяем название класса
        String result = pathToCurrentClass.replaceAll(className, classToLoad);
        //Убираем file:
        result = result.substring(6);
        //Изменяем разделитель
        result = result.replaceAll("\\/", "\\\\");
        return result;
    }
}
