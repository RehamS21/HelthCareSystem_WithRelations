package com.example.healthcaresystem_project4.Controller;

import com.example.healthcaresystem_project4.Api.ApiResponse;
import com.example.healthcaresystem_project4.Model.Patient;
import com.example.healthcaresystem_project4.Service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/get")
    public ResponseEntity getAllPatients(){
        return ResponseEntity.status(200).body(patientService.getAllPatient());
    }

    @PostMapping("/add")
    public ResponseEntity addNewPatient(@RequestBody @Valid Patient patient){
        patientService.addPatient(patient);
        return ResponseEntity.status(200).body(new ApiResponse("the patient '"+patient.getName() +"' added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePatient(@PathVariable Integer id, @RequestBody @Valid Patient patient){
        patientService.updatePatient(id,patient);
        return ResponseEntity.status(200).body(new ApiResponse("the patient '"+patient.getName() +"' updated info successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePatient(@PathVariable Integer id){
        patientService.deletePatient(id);
        return ResponseEntity.status(200).body(new ApiResponse("The patient with id '"+id+"' deleted successfully"));
    }

    @PutMapping("/bookingApo/{id}")
    public ResponseEntity bookingAppointment(@PathVariable Integer id){
        patientService.appointmentBooking(id);
        return ResponseEntity.status(200).body(new ApiResponse("Booking appointment for patient successfully"));
    }

    @GetMapping("/getApo")
    public ResponseEntity getPatientWithAppointment(){
        return ResponseEntity.status(200).body(patientService.getAllPatentWithAppointment());
    }

    @GetMapping("order")
    public ResponseEntity orderPatientAsec(){
        return ResponseEntity.status(200).body(patientService.patientsOrdered());
    }


}
