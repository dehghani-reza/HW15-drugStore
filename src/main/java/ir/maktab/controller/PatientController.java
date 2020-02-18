package ir.maktab.controller;

import ir.maktab.service.PatientService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;
    ApplicationContext app;

    public PatientController(PatientService patientService, ApplicationContext app) {
        this.patientService = patientService;
        this.app = app;
    }


}
