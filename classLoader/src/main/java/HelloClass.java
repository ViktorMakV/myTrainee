/**
 * @author Viktor Makarov
 */
public class HelloClass {
    public void sayHello(String name, int cycles) {
        System.out.println("Hello " + name);
        for (int i = 0; i < cycles; i++) {
            System.out.println("Repeating cycle");
        }
    }

    public static String sayMyName() {
        return HelloClass.class.getName() + " is my name.";
    }
}
