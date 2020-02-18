package ir.maktab.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
@Component("drug")
@Scope("prototype")
@Entity
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private Long drugCode;

    @Column(nullable = false)
    private Float cost;

    private String description;

    @ManyToMany(mappedBy = "drugList")
    private List<Prescription> prescriptionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(Long drugCode) {
        this.drugCode = drugCode;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drug)) return false;
        Drug drug = (Drug) o;
        return getDrugCode().equals(drug.getDrugCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDrugCode());
    }

    @Override
    public String toString() {
        return "Drug{" +
                "name='" + name + '\'' +
                ", drugCode=" + drugCode +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                '}';
    }
}
