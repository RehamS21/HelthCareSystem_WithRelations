package com.example.healthcaresystem_project4.Service;

import com.example.healthcaresystem_project4.Api.ApiException;
import com.example.healthcaresystem_project4.Api.ApiResponse;
import com.example.healthcaresystem_project4.Model.Doctor;
import com.example.healthcaresystem_project4.Model.Patient;
import com.example.healthcaresystem_project4.Repository.DoctorRepository;
import com.example.healthcaresystem_project4.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public List<Patient> getAllPatient(){
        return patientRepository.findAll();
    }

    public void addPatient(Patient patient){
        Doctor doctor = doctorRepository.findDoctorById(patient.getDoctorid());
        if (doctor == null)
            throw new ApiException("Sorry the doctor id is wrong");

        patientRepository.save(patient);
    }

    public void updatePatient(Integer id, Patient patient){
        Patient oldPatient = patientRepository.findPatientById(id);

        if (oldPatient == null)
            throw new ApiException("Sorry, patient id is wrong");

        Doctor doctor = doctorRepository.findDoctorById(patient.getDoctorid());
        if (doctor == null)
            throw new ApiException("Sorry the doctor is wrong");

        oldPatient.setName(patient.getName());
        oldPatient.setAge(patient.getAge());
        oldPatient.setPhone(patient.getPhone());
        oldPatient.setMoney(patient.getMoney());
        oldPatient.setAppointment(patient.getAppointment());


        patientRepository.save(oldPatient);
    }

    public void deletePatient(Integer id){
        Patient deletePatient = patientRepository.findPatientById(id);

        if (deletePatient == null)
            throw new ApiException("Sorry, patient id is wrong");

        patientRepository.delete(deletePatient);
    }

    public void appointmentBooking(Integer id){
        Patient patient = patientRepository.findPatientById(id);

        if (patient == null)
            throw new ApiException("the patient id is wrong");

        if(patient.getAppointment())
            throw new ApiException("You have already an appointment");
        else
            patient.setAppointment(true);

        patientRepository.save(patient);
    }

    public List<Patient> getAllPatentWithAppointment(){
        return patientRepository.getAllPatientWithAppintment();
    }
    public List<Patient> patientsOrdered(){
        List<Patient> patients = patientRepository.orderPatientByMoney();

        if (patients.isEmpty())
            throw new ApiException("Sorry , No patients exist");

        return patients;
    }



}
