package com.bluemango.project_backend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bluemango.project_backend.dto.SellerRequest;
import com.bluemango.project_backend.dto.SellerResponse;
import com.bluemango.project_backend.models.Seller;
import com.bluemango.project_backend.repositories.SellerRepository;
import com.bluemango.project_backend.services.SellerService;

import jakarta.validation.Valid;

import java.util.List;
import java.net.URI;

@RestController
@CrossOrigin
@RequestMapping("sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private SellerRepository sellerRepository;


    //Create Endpoint
    @PostMapping
    public ResponseEntity<SellerResponse> createSeller(@Validated @RequestBody SellerRequest sellerRequest) {
        SellerResponse seller = sellerService.save(sellerRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(seller.getId())
                .toUri();

        return ResponseEntity.created(location).body(seller);
    }

    //Read Endpoint (all)
    @GetMapping
    public ResponseEntity<List<SellerResponse>> getAllSellers() {

        return ResponseEntity.ok(sellerService.getAll());
    }

    //Read Endpoint (by ID)
    @GetMapping("{id}")
    public ResponseEntity<SellerResponse> getSeller(@PathVariable long id) {
        return ResponseEntity.ok(sellerService.getById(id));
    }

    //Update Endpoint
    @PutMapping("{id}")
    public ResponseEntity<SellerResponse> updateSeller(@PathVariable long id,
            @Valid @RequestBody SellerRequest sellerRequest) {
        sellerService.update(id, sellerRequest);

        return ResponseEntity.ok().build();
    }


    //Delete Endpoint
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Seller not found"));

        sellerRepository.delete(seller);
        return ResponseEntity.noContent().build();

    }

}
