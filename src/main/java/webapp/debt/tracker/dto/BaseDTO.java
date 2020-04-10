package webapp.debt.tracker.dto;

import org.modelmapper.ModelMapper;

public abstract class BaseDTO {
	
	public <T> T toEntity(Class<T> type) {
		ModelMapper modelMapper = new ModelMapper();
		T objectDTO = modelMapper.map(this, type);
		    return objectDTO;
	}

}
