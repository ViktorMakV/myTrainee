package com.restapp.service;

import com.restapp.model.Unit;

/**
 * @author Viktor Makarov
 */
public interface UnitService {
    Unit findById(long id);
    Iterable<Unit> findAll();
    Unit saveUnit(Unit unit);
    Unit deleteUnit(Unit unit);
}
