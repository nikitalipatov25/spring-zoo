package com.nikitalipatov.zoospring.repos;

import com.nikitalipatov.zoospring.models.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZooRepository extends JpaRepository<Zoo, Integer> {
}
