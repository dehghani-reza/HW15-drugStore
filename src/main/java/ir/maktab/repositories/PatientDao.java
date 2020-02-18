package ir.maktab.repositories;

import ir.maktab.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient,Long> {
}
