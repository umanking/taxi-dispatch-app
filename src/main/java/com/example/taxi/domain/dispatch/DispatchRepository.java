package com.example.taxi.domain.dispatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DispatchRepository extends JpaRepository<Dispatch, Long> {

    Optional<Dispatch> findByIdAndDriverStatus(Long id, DriverStatus driverStatus);

    List<Dispatch> findByDriverStatusNot(DriverStatus driverStatus);

}
