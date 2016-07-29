package com.tsm.model.entity.repository;

import com.tsm.model.entity.Ordercom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEcomRepository extends JpaRepository<Ordercom, Long> {

}
