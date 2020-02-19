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
        model.addAttribute("patient", app.getBean("patient"));
        model.addAttribute("prescriptions", app.getBean("prescription"));
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

    @GetMapping("/load_patient_for_edit")
    public String editDrugById(@RequestParam(value = "id") final Long id , Model model) {
        try {
            Patient patient = patientService.findById(id);
            model.addAttribute("goingToEditPatient" , patient);
            model.addAttribute("newPatient" , app.getBean("patient"));
        } catch (Exception e) {
            model.addAttribute("subject" , e);//Todo error handler and thymeleaf
            return "error";
        }
        return "edit_patient";
    }

    @PostMapping(value = "/edit_patient_to_db")
    public String editDrugSavePage(@ModelAttribute Patient patient , @RequestParam(value = "id") final Long id , Model model) {
        try {
            patientService.editPatient(id,patient);
        } catch (Exception e) {
            e.printStackTrace();//todo exception
        }
        model.addAttribute("patientListKey",patientService.loadAllPatient());
        return "show_all_patients";
    }

    @RequestMapping(value = "/delete_patient_from_db")
    public String deleteById(@RequestParam(value = "id") final Long id,Model model){
        try {
            System.out.println(patientService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        patientService.deletePatient(id);
        model.addAttribute("patientListKey",patientService.loadAllPatient());
        return "show_all_patients";
    }
}