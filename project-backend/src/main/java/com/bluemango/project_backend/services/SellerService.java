package com.bluemango.project_backend.services;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.bluemango.project_backend.dto.SellerRequest;
import com.bluemango.project_backend.dto.SellerResponse;
import com.bluemango.project_backend.models.Seller;
import com.bluemango.project_backend.repositories.SellerRepository;
import com.bluemango.project_backend.services.exceptions.DatabaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public SellerResponse getById(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found"));
        return seller.toDTO();

    }

    public List<SellerResponse> getAll() {
        return sellerRepository.findAll()
                .stream()
                .map(s -> s.toDTO())
                .collect(Collectors.toList());
    }

    public SellerResponse save(SellerRequest sellerRequest) {
        // TODO Auto-generated method stub
        try {
            Seller seller = sellerRepository.save(sellerRequest.toEntity());
            return seller.toDTO();
        } catch (DataIntegrityViolationException e) {

            // TODO: handle exception
            throw new DatabaseException("Constrain violation, seller cannot be saved");
        }
    }

    public void update(long id, SellerRequest sellerRequest) {
        // TODO Auto-generated method stub
        try {
            Seller seller = sellerRepository.getReferenceById(id);
            seller.setName(sellerRequest.getName());
            seller.setSalary(sellerRequest.getSalary());
            seller.setBonus(sellerRequest.getBonus());
            seller.setGender(sellerRequest.getGender());
            sellerRepository.save(seller);

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Seller not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Constrain violation, seller cannot be updated");

        }

    }

    public void deleteById(Long id){
        try{
            sellerRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Constrain violation, seller cannot be deleted");
        }
        catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Seller not found");
        }
    }

}
