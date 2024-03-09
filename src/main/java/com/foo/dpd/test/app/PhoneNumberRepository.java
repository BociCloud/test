package com.foo.dpd.test.app;

import com.foo.dpd.test.entity.PhoneNumber;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

    Optional<PhoneNumber> findByPhoneNumberIdAndPersonPersonId(Long id, String personId);
}
