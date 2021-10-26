package com.restapp.comparator;

import com.restapp.model.Unit;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * @author Viktor Makarov
 */
@Component
public class UnitComparator implements Comparator<Unit> {
    @Override
    public int compare(Unit o1, Unit o2) {
        return Long.compare(o1.getId(), o2.getId());
    }
}
