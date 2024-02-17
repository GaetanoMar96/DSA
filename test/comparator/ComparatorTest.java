package comparator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ComparatorTest {

    @Test
    void testComparatorSort() {
        List<Item> list = new ArrayList<>();
        Item item1 = new Item(4);
        Item item2 = new Item(5);
        Item item3 = new Item(2);
        Item item4 = new Item(1);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);

        list.sort(Comparator.comparingInt(Item::getValue));

        for(int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i).getValue() > list.get(i-1).getValue());
        }
    }

    @Test
    void testComparableSort() {
        List<ItemComparable> list = new ArrayList<>();
        ItemComparable item1 = new ItemComparable(4);
        ItemComparable item2 = new ItemComparable(5);
        ItemComparable item3 = new ItemComparable(2);
        ItemComparable item4 = new ItemComparable(1);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);

        list.sort(Comparator.comparingInt(ItemComparable::getValue));

        for(int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i).getValue() > list.get(i-1).getValue());
        }
    }
}
