package com.proxyapis.proxyservice.repository;

import com.proxyapis.proxyservice.entity.RequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<RequestEntity, String> {
}
