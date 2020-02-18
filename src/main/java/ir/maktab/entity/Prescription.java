package ir.maktab.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
@Component("prescription")
@Scope("prototype")
@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true , nullable = false)
    private Long prescriptionCode;

    private LocalDate issueDate;

    private LocalDate referralDate;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToMany
    @JoinTable(name = "Drog_Prescription"
            , joinColumns = @JoinColumn(name = "prescription_id")
            , inverseJoinColumns = @JoinColumn(name = "drug_id"))
    private List<Drug> drugList;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrescriptionCode() {
        return prescriptionCode;
    }

    public void setPrescriptionCode(Long prescriptionId) {
        this.prescriptionCode = prescriptionId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(LocalDate referralDate) {
        this.referralDate = referralDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prescription)) return false;
        Prescription that = (Prescription) o;
        return getPrescriptionCode().equals(that.getPrescriptionCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrescriptionCode());
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionCode +
                ", issueDate=" + issueDate +
                ", referralDِate=" + referralDate +
                ", patient=" + patient +
                ", drugList=" + drugList +
                '}';
    }
}
