package com.example.healthcaresystem_project4.Service;

import com.example.healthcaresystem_project4.Api.ApiException;
import com.example.healthcaresystem_project4.Model.Bill;
import com.example.healthcaresystem_project4.Model.Patient;
import com.example.healthcaresystem_project4.Repository.BillRepository;
import com.example.healthcaresystem_project4.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
//    private final PatientService patientService;
    private final PatientRepository patientRepository;

    public List<Bill> getAllBill(){
        return billRepository.findAll();
    }

    public void addBill(Bill bill){
        Patient patient = patientRepository.findPatientById(bill.getPatientid());
        if (patient == null)
            throw new ApiException("patient id is wrong");

        billRepository.save(bill);
    }

    public void updateBill(Integer id, Bill bill){
        Bill oldBill = billRepository.findBillById(id);

        if (oldBill == null)
            throw new ApiException("Sorry, bill id is wrong");

        Patient patient = patientRepository.findPatientById(bill.getPatientid());
        if (patient == null)
            throw new ApiException("Sorry, patient id is wrong");

        oldBill.setBillprice(bill.getBillprice());
        oldBill.setPatientid(bill.getPatientid());

        billRepository.save(oldBill);
    }



    public void deleteBill(Integer id){
        Bill deleteBill = billRepository.findBillById(id);

        if (deleteBill == null)
            throw new ApiException("Sorry, bill id is wrong");

        billRepository.delete(deleteBill);
    }


    public Integer calculateBill(Integer billId){
        Bill bill = billRepository.findBillById(billId);
        if (bill == null)
            throw new ApiException("bill id is wrong");

        Patient patient = patientRepository.findPatientById(bill.getPatientid());
        if (patient == null)
            throw new ApiException("No bull for this patient");


        // calculate the bill for patient:
        Integer billResult = patient.getMoney() - bill.getBillprice() ;

        if (billResult < 0)
            throw new ApiException("The money of patient not sufficient");

        patient.setMoney(billResult);
        patientRepository.save(patient);

        return patient.getMoney();
    }


    // discount the bill for children where age < 18
    public Integer DiscountBill(Integer billId){
        Bill bill = billRepository.findBillById(billId);
        if (bill == null)
            throw new ApiException("Sorry the bill id is wrong");

        Patient patient = patientRepository.discountBillPatient(bill.getPatientid());

        if (patient == null)
            throw new ApiException("Sorry, the discount only for the patient where age < 18");
        Integer billDiscount = (int) (bill.getBillprice() - (bill.getBillprice() * 0.15));

        bill.setBillprice(billDiscount);

        billRepository.save(bill);

        return billDiscount;
    }





}
