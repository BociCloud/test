package com.foo.dpd.test.app;

import com.foo.dpd.test.entity.Address;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByAddressIdAndPersonPersonId(Long id, String personId);

}
