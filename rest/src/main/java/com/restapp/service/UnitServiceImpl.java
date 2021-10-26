package com.restapp.service;

import com.model.caches.CacheInterface;
import com.restapp.comparator.UnitComparator;
import com.restapp.model.Unit;
import com.restapp.repo.UnitRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Viktor Makarov
 */
@Component
@Log4j2
public class UnitServiceImpl implements UnitService {
    private final CacheInterface<Unit> cache;
    private final UnitRepo unitRepo;
    private final UnitComparator comparator;

    public UnitServiceImpl(@Autowired UnitRepo unitRepo, @Autowired(required = false) CacheInterface<Unit> cache,
                           @Autowired UnitComparator unitComparator) {
        this.unitRepo = unitRepo;
        this.cache = cache;
        this.comparator = unitComparator;
    }

    @Override
    public Unit findById(long id) {
        Unit out = new Unit();
        out.setId(id);
        //Check for Unit in cache.
        if (cache != null) {
            out = cache.getCachedValue(out, comparator);
            if (out != null) {
                log.debug("Got value from cache");
            }
        }
        //If value not found in cache - get it from DB
        if (out == null || out.getName() == null) {
            out = unitRepo.findById(id).orElse(new Unit());
            if (out.getId() != 0) {
                log.debug("Got value from database");
            }
        }
        //Store value in cache
        if (out.getId() != 0 && cache != null) {
            cache.cacheValue(out);
        }
        return out;
    }

    @Override
    public Iterable<Unit> findAll() {
        return unitRepo.findAll();
    }

    @Override
    public Unit saveUnit(Unit unit) {
        if (cache != null) {
            cache.cacheValue(unit);
        }
        return unitRepo.save(unit);
    }

    @Override
    public Unit deleteUnit(Unit unit) {
        Unit result = findById(unit.getId());
        if (result.getId() != 0) {
            unitRepo.delete(unit);
        }
        return result;
    }
}
