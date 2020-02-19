package ir.maktab.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component("patient")
@Scope("prototype")
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId;

    private String name;

    private String lastName;

//    @Enumerated(value = EnumType.STRING)
    private String gender;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "patient" )
    private List<Prescription> prescriptionList = new ArrayList<>();

    //*********************************

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long id) {
        this.patientId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getPatientId(), patient.getPatientId()) &&
                Objects.equals(getName(), patient.getName()) &&
                Objects.equals(getLastName(), patient.getLastName()) &&
                getGender() == patient.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPatientId(), getName(), getLastName(), getGender());
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
