package ir.maktab.controller;

import ir.maktab.entity.Drug;
import ir.maktab.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/drug")
public class DrugController {

    private DrugService drugService;
    ApplicationContext app;

    @Autowired
    public DrugController(DrugService drugService, ApplicationContext app) {
        this.drugService = drugService;
        this.app = app;
    }

    @GetMapping(value = "/add_drug")
    public String findDrugById() {
        return "add_drug_view";
    }

    @PostMapping(value = "/add_drug_to_db")
    public String findDrugById(HttpServletRequest request, ModelMap map) {
        Drug drug = app.getBean("drug", Drug.class);
        drug.setName(request.getParameter("name"));
        drug.setDrugCode(Long.valueOf(request.getParameter("Code")));
        drug.setCost(Float.valueOf(request.getParameter("Cost")));
        drug.setDescription(request.getParameter("Description"));
        Drug drug1 = drugService.saveDrug(drug);
        map.addAttribute("saved", drug1);
        return "add_drug_view";
    }

    @GetMapping(value = "load_all_drugs")
    public String loadAllDrugs(Model model) {
        List<Drug> drugList = drugService.loadAllDrug();
        model.addAttribute("drugListKey",drugList);
        return "show_all_drugs";
    }
}
