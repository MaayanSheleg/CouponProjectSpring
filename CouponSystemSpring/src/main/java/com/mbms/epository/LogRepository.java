package com.mbms.epository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mbms.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

	List<Log> findTop20ByDate(Date date);

	List<Log> findFrist20ByDateBetween(Date startDate, Date endDate);

	@Query("select l from Log l where l.success = 0 ")
	List<Log> FindFailedLogs();

	@Query("select l from Log l where l.success = 1 ")
	List<Log> FindSuccessedLogs();

}
