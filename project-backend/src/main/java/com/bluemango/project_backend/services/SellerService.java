package com.bluemango.project_backend.services;

import java.util.stream.Collectors;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemango.project_backend.dto.SellerRequest;
import com.bluemango.project_backend.dto.SellerResponse;
import com.bluemango.project_backend.models.Seller;
import com.bluemango.project_backend.repositories.SellerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository; 

    public SellerResponse getById(Long id){
        Seller seller = sellerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Seller not found"));
        return seller.toDTO(); 

    }

    public List<SellerResponse> getAll(){
        return sellerRepository.findAll()
        .stream()
        .map(s -> s.toDTO())
        .collect(Collectors.toList()); 
    }

    public SellerResponse save(SellerRequest sellerRequest) {
        // TODO Auto-generated method stub
        Seller seller = sellerRepository.save(sellerRequest.toEntity());
        return seller.toDTO();
    }

    public void update(long id, SellerRequest sellerRequest) {
        // TODO Auto-generated method stub
        Seller seller = sellerRepository.getReferenceById(id); 
        seller.setName(sellerRequest.getName());
        seller.setSalary(sellerRequest.getSalary());
        seller.setBonus(sellerRequest.getBonus()); 
        seller.setGender(sellerRequest.getGender()); 
        sellerRepository.save(seller); 
        
        
    }
    

}
