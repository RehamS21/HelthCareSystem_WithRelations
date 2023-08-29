package com.example.healthcaresystem_project4.Repository;

import com.example.healthcaresystem_project4.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient findPatientById(Integer id);


    @Query("select p from Patient p where p.appointment = true ")
    List<Patient> getAllPatientWithAppintment();



    @Query("select p from Patient p where p.id =?1 and p.age < 18")
    Patient discountBillPatient(Integer id );

    @Query("select p from Patient p order by p.money asc ")
    List<Patient> orderPatientByMoney();

    @Query("select count (p.doctorid) from Patient p where p.doctorid=?1")
    Integer numberOfPatient(Integer doctorid);
}
