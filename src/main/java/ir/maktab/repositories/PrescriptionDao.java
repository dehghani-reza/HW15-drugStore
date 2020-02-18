package ir.maktab.repositories;

import ir.maktab.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionDao extends JpaRepository<Prescription,Long> {
}
