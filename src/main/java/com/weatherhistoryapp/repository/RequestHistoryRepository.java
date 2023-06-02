package com.weatherhistoryapp.repository;

import com.weatherhistoryapp.model.RequestHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHistoryRepository extends CrudRepository<RequestHistory, Long> {
}
