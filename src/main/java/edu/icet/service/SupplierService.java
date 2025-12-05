package edu.icet.service;

import edu.icet.model.dto.SupplierDTO;
import edu.icet.model.entity.SupplierEntity;
import edu.icet.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository repo;

    public SupplierDTO createSupplier(CreateSupplierRequest req) {
        SupplierEntity s = SupplierEntity.builder()
                .name(req.getName())
                .email(req.getEmail())
                .phoneNo(req.getPhoneNo())
                .address(req.getAddress())
                .build();
        return toDTO(repo.save(s));
    }

    private SupplierDTO toDTO(SupplierEntity s) {
        return SupplierDTO.builder()
                .supplierId(s.getSupplierId())
                .name(s.getName())
                .email(s.getEmail())
                .phoneNo(s.getPhoneNo())
                .address(s.getAddress())
                .build();
    }

}
