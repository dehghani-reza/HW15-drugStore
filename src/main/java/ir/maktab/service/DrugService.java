package ir.maktab.service;

import ir.maktab.entity.Drug;
import ir.maktab.repositories.DrugDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugService {

    private DrugDao drugDao;

    @Autowired
    public DrugService(DrugDao drugDao) {
        this.drugDao = drugDao;
    }

    public Drug findById(Long id) throws Exception {
        Optional<Drug> byId = drugDao.findById(id);
        if(byId.isEmpty()){
            throw new Exception("Drug with this id not exist");
        }
        return byId.get();
    }

    public Drug saveDrug(Drug drug){
        return drugDao.save(drug);
    }

    public List<Drug> loadAllDrug(){
       return drugDao.findAll();
    }

    public Drug editDrug(Long id , Drug drug) throws Exception {
        Drug byId = findById(id);

        if(drug.getName()!=null){
            byId.setName(drug.getName());
        }
        if(drug.getDrugCode()!=null){
            byId.setDrugCode(drug.getDrugCode());
        }
        if(drug.getCost()!=null){
            byId.setCost(drug.getCost());
        }
        if(drug.getDescription()!=null){
            byId.setDescription(drug.getDescription());
        }
        return saveDrug(byId);
    }

    public void deleteDrug(Long id) {
        drugDao.deleteById(id);
    }
}
