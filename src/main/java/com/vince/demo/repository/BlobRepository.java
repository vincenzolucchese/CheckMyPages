package com.vince.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.vince.demo.entity.BlobEntity;

public interface BlobRepository extends CrudRepository<BlobEntity, Long> {

}
