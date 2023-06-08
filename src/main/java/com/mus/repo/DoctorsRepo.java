package com.mus.repo;

import com.mus.model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorsRepo extends JpaRepository<Doctors, Long> {
    List<Doctors> findAllByCategory(String category);
}
