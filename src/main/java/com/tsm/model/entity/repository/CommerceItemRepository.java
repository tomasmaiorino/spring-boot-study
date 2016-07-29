package com.tsm.model.entity.repository;

import java.util.List;

import com.tsm.model.entity.CommerceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tsm.model.entity.Ordercom;

@Repository
public interface CommerceItemRepository extends JpaRepository<CommerceItem, Long> {

	@Query("SELECT DISTINCT c.order FROM CommerceItem c WHERE c.sku.id =:sku)")
    public List<Ordercom> findBySku(@Param("sku") Long sku);
}
