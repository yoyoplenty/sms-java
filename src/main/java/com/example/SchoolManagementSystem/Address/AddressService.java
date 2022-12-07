package com.example.SchoolManagementSystem.Address;

import com.example.SchoolManagementSystem.Address.Dto.NewAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address findAddressById(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("address not found on :: " + id));
    }

    public Address updateAddress(NewAddressDto updateAddressDto, UUID id) {
        Address address = findAddressById(id);

        address.setStreet(address.getStreet());
        address.setLga(address.getLga());
        address.setState(address.getState());
        address.setContactPersonName(address.getContactPersonName());
        address.setContactPersonEmail(address.getContactPersonEmail());

        return addressRepository.save(address);
    }

    public void deleteAddress(UUID id) {
        Address address = findAddressById(id);

        addressRepository.delete(address);
    }
}
