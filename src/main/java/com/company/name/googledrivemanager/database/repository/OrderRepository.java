package com.company.name.googledrivemanager.database.repository;

import com.company.name.googledrivemanager.database.model.Order;
import com.company.name.googledrivemanager.database.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
