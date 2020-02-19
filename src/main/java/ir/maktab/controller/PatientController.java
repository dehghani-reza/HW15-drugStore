package ir.maktab.controller;

import ir.maktab.entity.Drug;
import ir.maktab.entity.Patient;
import ir.maktab.entity.Prescription;
import ir.maktab.service.PatientService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;
    ApplicationContext app;

    public PatientController(PatientService patientService, ApplicationContext app) {
        this.patientService = patientService;
        this.app = app;
    }

    @GetMapping(value = "/add")
    public String addPatient(Model model) {
        model.addAttribute("patient", app.getBean("patient"));
        model.addAttribute("prescriptions", app.getBean("prescription"));
        return "add_patient_view";
    }

    @PostMapping(value = "/save")
    public String addPatient(@ModelAttribute Patient patient, @ModelAttribute Prescription prescription,Model model) {
        patientService.savePatient(patient);
        prescription.setPatient(patient);
        patientService.prescriptionSave(prescription);
        model.addAttribute("saved",patient);
        return "add_patient_view";
    }
    @GetMapping(value = "load_all_patient")
    public String loadAllDrugs(Model model) {
        List<Patient> patientList = patientService.loadAllPatient();
        model.addAttribute("patientListKey",patientList);
        return "show_all_patients";
    }

    @GetMapping(value = "load_patient_by_id")
    public String patientDetails(@RequestParam(value = "id") final Long id , Model model){
        try {
            Patient patient = patientService.findById(id);
            model.addAttribute("loadedPatient",patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "patient_detail";
    }
}