package com.restapp.repo;

import com.restapp.model.Unit;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Viktor Makarov
 */
public interface UnitRepo extends CrudRepository<Unit, Long> {
}
