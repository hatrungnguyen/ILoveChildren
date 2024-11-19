package vn.edu.usth.ilovechildren.models;

import org.w3c.dom.CDATASection;

import java.util.List;

public class Patient {
    private String patient_id;
    private String name;
    private List<Data> daily_data;


    public Patient(String patient_id, String name, List<Data> daily_data) {
        this.patient_id = patient_id;
        this.name = name;
        this.daily_data = daily_data;
    }


    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Data> getDaily_data() {
        return daily_data;
    }

    public void setDaily_data(List<Data> daily_data) {
        this.daily_data = daily_data;
    }
}
