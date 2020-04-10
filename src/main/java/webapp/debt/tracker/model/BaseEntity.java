package webapp.debt.tracker.model;

import org.modelmapper.ModelMapper;

public abstract class BaseEntity {
	public <T> T toDTO(Class<T> type) {
		ModelMapper modelMapper = new ModelMapper();
		T objectDTO = modelMapper.map(this, type);
		    return objectDTO;
	}
}
