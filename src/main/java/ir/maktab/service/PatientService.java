package ir.maktab.service;

import ir.maktab.entity.Drug;
import ir.maktab.entity.Patient;
import ir.maktab.entity.Prescription;
import ir.maktab.repositories.PatientDao;
import ir.maktab.repositories.PrescriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private PatientDao patientDao;
    private PrescriptionDao prescriptionDao;

    @Autowired
    public PatientService(PatientDao patientDao , PrescriptionDao prescriptionDao) {
        this.patientDao = patientDao;
        this.prescriptionDao = prescriptionDao;
    }

    public Patient findById(Long id) throws Exception {
        Optional<Patient> byId = patientDao.findById(id);
        if(byId.isEmpty()){
            throw new Exception("patient with this id not exist");
        }
        return byId.get();
    }

    public Patient savePatient(Patient patient){
       return patientDao.save(patient);
    }

    public List<Patient> loadAllPatient(){
        return patientDao.findAll();
    }

    public void deletePatient(Long id) {
        patientDao.deleteById(id);
    }

    public Patient editPatient(Long id , Patient patient) throws Exception {
        Patient byId = findById(id);

        if(patient.getName()!=null){
            byId.setName(patient.getName());
        }
        if(patient.getGender()!=null){
            byId.setGender(patient.getGender());
        }
        if(patient.getLastName()!=null){
            byId.setLastName(patient.getLastName());
        }
        if(patient.getPrescriptionList()!=null){
            byId.setPrescriptionList(patient.getPrescriptionList());
        }
        return savePatient(byId);
    }

    public Prescription prescriptionSave(Prescription prescription){
       return prescriptionDao.save(prescription);
    }
}
