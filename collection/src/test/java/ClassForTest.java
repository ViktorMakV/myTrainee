import java.util.Objects;

/**
 * @author Viktor Makarov
 */
public class ClassForTest {
    private Integer num = 100;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer s) {
        this.num = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassForTest that = (ClassForTest) o;
        return Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public String toString() {
        return "ClassForTest{" +
                "num=" + num +
                '}';
    }
}
