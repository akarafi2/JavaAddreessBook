package com.tts.JavaAddressBook.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tts.JavaAddressBook.domain.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
	List<Address> findByFirstName(String firstName );
	List<Address> findByLastName(String lastName );
	List<Address> findByPhoneNumber(String phoneNumber );
	List<Address> findByEmailAddress(String emailAddress );
}







