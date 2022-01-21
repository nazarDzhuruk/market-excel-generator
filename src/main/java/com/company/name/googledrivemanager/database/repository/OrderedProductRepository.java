package com.company.name.googledrivemanager.database.repository;

import com.company.name.googledrivemanager.database.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Integer> {
}
