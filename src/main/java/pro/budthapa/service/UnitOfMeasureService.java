package pro.budthapa.service;

import java.util.Optional;

import pro.budthapa.domain.UnitOfMeasure;

public interface UnitOfMeasureService {
	Optional<UnitOfMeasure> findUOMByDescription(String unitOfMeasure);
}
