package com.example.SchoolManagementSystem.Address;

import com.example.SchoolManagementSystem.Address.Dto.NewAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address createAddress(NewAddressDto address) {
        try {
            Address newAddress = Address.builder()
                    .street(address.getStreet())
                    .lga(address.getLga())
                    .state(address.getState())
                    .contactPersonName(address.getContactPersonName())
                    .contactPersonEmail(address.getContactPersonEmail())
                    .build();

            return addressRepository.save(newAddress);

        } catch (Exception e) {
            throw new IllegalStateException("unable to create address");
        }
    }
}
