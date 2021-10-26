package com.restapp.controller;

import com.restapp.model.Unit;
import com.restapp.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Viktor Makarov
 */
@RestController
public class UnitController {
    private final UnitService unitService;

    public UnitController(@Autowired UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public Iterable<Unit> allUnits() {
        return unitService.findAll();
    }

    @GetMapping("/{id}")
    public Unit getUnit(@PathVariable(value = "id") long id) {
        return unitService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Unit saveUnit(@RequestBody Unit unit) {
        unit.setId(0);
        return unitService.saveUnit(unit);
    }

    @PutMapping
    public Unit updateUnit(@RequestBody Unit unit) {
        Unit result = unitService.findById(unit.getId());
        if (result.getId() != 0) {
            result = unitService.saveUnit(unit);
        }
        return result;
    }

    @DeleteMapping
    public Unit deleteUnit(@RequestBody Unit unit) {
        return unitService.deleteUnit(unit);
    }
}
