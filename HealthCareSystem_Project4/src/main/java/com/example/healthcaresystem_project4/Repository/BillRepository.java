package com.example.healthcaresystem_project4.Repository;

import com.example.healthcaresystem_project4.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {

    Bill findBillById(Integer id);


}
