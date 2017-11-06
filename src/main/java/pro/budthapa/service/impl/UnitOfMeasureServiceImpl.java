package pro.budthapa.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.domain.UnitOfMeasure;
import pro.budthapa.repositories.UnitOfMeasureRepository;
import pro.budthapa.service.UnitOfMeasureService;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

	@Autowired
	private UnitOfMeasureRepository unitOfMeasureRepository;
	@Override
	public Optional<UnitOfMeasure> findUOMByDescription(String unitOfMeasure) {
		return unitOfMeasureRepository.findByDescription(unitOfMeasure);
	}

}
