package com.bluemango.project_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluemango.project_backend.models.Seller;



public interface SellerRepository extends JpaRepository <Seller, Long> {

   
}
