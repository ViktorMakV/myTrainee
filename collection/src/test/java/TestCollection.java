import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktor Makarov
 */
public class TestCollection {
    MyCollectionInterface<ClassForTest> collection;
    ClassForTest classForTest;

    @BeforeClass
    public static void testAddMethod() {
        MyCollectionInterface<ClassForTest> test = new MyCollection<>(ClassForTest.class);

        Assert.assertTrue(test.add(new ClassForTest()));
        Assert.assertFalse(test.isEmpty());
    }

    @Before
    public void initBeforeMethod() {
        collection = new MyCollection<>(ClassForTest.class);
        classForTest = new ClassForTest();
    }

    @Test
    public void shouldAddElement() {
        collection.add(classForTest);

        Assert.assertFalse(collection.isEmpty());
        Assert.assertEquals(1L, collection.size());
    }

    @Test
    public void shouldSaveAsImmutable() {
        collection.add(classForTest);
        classForTest.setNum(200);

        Assert.assertNotEquals(classForTest.getNum(), collection.get(0).getNum());
    }

    @Test
    public void shouldSaveBySpecificIndex() {
        collection.add(classForTest);
        classForTest.setNum(200);

        int positionInCollection = 0;
        collection.add(positionInCollection, classForTest);

        Assert.assertEquals(classForTest.getNum(), collection.get(0).getNum());
    }

    @Test
    public void shouldSaveFromOtherCollection() {
        List<ClassForTest> list = new ArrayList<>();
        list.add(classForTest);

        MyCollection<ClassForTest> collectionList = new MyCollection<>(ClassForTest.class, list);

        Assert.assertFalse(collectionList.isEmpty());
        Assert.assertEquals(classForTest.getNum(), collectionList.get(0).getNum());
    }

    @Test
    public void shouldFindEqualElementInCollection() {
        collection.add(classForTest);

        Assert.assertTrue(collection.contains(classForTest));
    }

    @Test
    public void shouldRemoveElement() {
        collection.add(classForTest);

        collection.remove(classForTest);

        Assert.assertTrue(collection.isEmpty());
    }

    @Test
    public void shouldRemoveElementByIndex() {
        collection.add(classForTest);
        classForTest.setNum(300);
        collection.add(classForTest);

        collection.remove(0);

        Assert.assertEquals(classForTest, collection.get(0));
    }

    @Test
    public void shouldRemoveElementByElement() {
        collection.add(classForTest);
        ClassForTest class2 = new ClassForTest();
        class2.setNum(300);
        collection.add(class2);

        collection.remove(classForTest);

        Assert.assertEquals(class2, collection.get(0));
    }

    @Test
    public void shouldClearCollection() {
        collection.add(classForTest);

        collection.clear();

        Assert.assertTrue(collection.isEmpty());
    }

    @Test
    public void shouldChangeElement() {
        collection.add(classForTest);
        classForTest.setNum(200);

        int positionInCollection = 0;
        collection.set(positionInCollection, classForTest);

        Assert.assertEquals(classForTest.getNum(), collection.get(0).getNum());
    }

    @Test
    public void shouldConvertToString() {
        collection.add(classForTest);
        classForTest.setNum(200);
        collection.add(classForTest);

        int numberOfFirstElement = collection.get(0).getNum();
        int numberOfSecondElement = collection.get(1).getNum();

        String result = "{ClassForTest{num=" +
                numberOfFirstElement +
                "}, ClassForTest{num=" +
                numberOfSecondElement +
                "}}";

        Assert.assertEquals(result, collection.toString());
    }
}
