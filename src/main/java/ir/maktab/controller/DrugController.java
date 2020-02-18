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
    public String loadDrugSavePage() {
        return "add_drug_view";
    }

    @PostMapping(value = "/add_drug_to_db")
    public String loadDrugSavePage(HttpServletRequest request, ModelMap map) {
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

    @GetMapping("/load_drug_for_edit")
    public String editDrugById(@RequestParam(value = "id") final Long id , Model model) {
        try {
            Drug drug = drugService.findById(id);
            model.addAttribute("goingToEditDrug" , drug);
            model.addAttribute("newDrug" , app.getBean("drug"));
        } catch (Exception e) {
            model.addAttribute("subject" , e);//Todo error handler and thymeleaf
            return "error";
        }
        return "edit_drug";
    }
    @PostMapping(value = "/edit_drug_to_db")
    public String editDrugSavePage(@ModelAttribute Drug drug , @RequestParam(value = "id") final Long id , Model model) {
        try {
            drugService.editDrug(id,drug);
        } catch (Exception e) {
            e.printStackTrace();//todo exception
        }
        model.addAttribute("drugListKey",drugService.loadAllDrug());
        return "show_all_drugs";
    }

    @RequestMapping(value = "/delete_drug_from_db")
    public String deleteByDrugById(@RequestParam(value = "id") final Long id,Model model){
        drugService.deleteDrug(id);
        model.addAttribute("drugListKey",drugService.loadAllDrug());
        return "show_all_drugs";
    }

}
