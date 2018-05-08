package controller;

import exceptions.ConsultationException;
import exceptions.PatientException;
import model.Consultation;
import model.Patient;
import org.junit.Test;
import repository.Repository;

import java.util.Arrays;
import java.util.List;

public class IncrementalTest {
    Repository repository = new Repository("src/main/FilePatients2.txt", "src/main/FileConsultations2.txt");
    DoctorController dc = new DoctorController(repository);

    @Test
    public void addValidPatient() throws PatientException {
        Integer size = dc.getPatientList().size();
        dc.addPatient(new Patient("John Doe the first", "1111111111111", "addr1"));
        dc.addPatient(new Patient("John Doe the second", "1111111111112", "addr1"));
        assert (dc.getPatientList().size() == size + 2);
    }

    @Test
    public void addValidConsultation() throws Exception {
        addValidPatient();
        try {
            String x = "0";
            if(dc.getConsultationList().size() > 0) {
                x = dc.getConsultationList().get(dc.getConsultationList().size() - 1).getConsID();
            }
            dc.addConsultation(String.valueOf(Integer.valueOf(x) + 100), "1111111111111", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
            dc.addConsultation(String.valueOf(Integer.valueOf(x) + 110), "1111111111111", "Cold", Arrays.asList("Med3", "Med4"), "12-12-2018");
            dc.addConsultation(String.valueOf(Integer.valueOf(x) + 120), "1111111111112", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
            assert (true);
        }
        catch (ConsultationException e){
            assert (false);
        }
    }

    @Test
    public void listPatients() throws Exception {
        addValidConsultation();
        List<Patient> patients = dc.getPatientsWithDisease("Cold");

        assert(patients.get(0).getSSN().equals("1111111111111"));
        assert(patients.get(1).getSSN().equals("1111111111112"));
    }
}
