package org.example.sample1.Repository;



import org.example.sample1.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepository extends JpaRepository<Booking, Long> {}
