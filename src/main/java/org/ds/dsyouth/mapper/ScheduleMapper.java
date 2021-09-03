package org.ds.dsyouth.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleMapper {

	boolean sqlQuery(String value);
	
}
