package controller;

import exceptions.ConsultationException;
import exceptions.PatientException;
import model.Patient;
import org.junit.Test;
import repository.Repository;

import java.util.Arrays;
import java.util.List;

public class BigBangTest {
    @Test
    public void addValidPatient() throws PatientException {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        repository.cleanFiles();
        DoctorController dc = new DoctorController(repository);
        Integer size = dc.getPatientList().size();
        dc.addPatient(new Patient("John Doe the first", "1111111111111", "addr1"));
        dc.addPatient(new Patient("John Doe the second", "1111111111112", "addr1"));
        dc.addPatient(new Patient("John Doe the third", "1111111111113", "addr1"));
        assert (dc.getPatientList().size() == size + 3);
    }

    @Test
    public void addValidConsultation() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        repository.cleanFiles();
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("John Doe the first", "1111111111111", "addr1"));
            dc.addPatient(new Patient("John Doe the second", "1111111111112", "addr1"));
            dc.addPatient(new Patient("John Doe the third", "1111111111113", "addr1"));
            dc.addConsultation("1", "1111111111111", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
            dc.addConsultation("4", "1111111111111", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
            dc.addConsultation("5", "1111111111111", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
            dc.addConsultation("2", "1111111111112", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
            dc.addConsultation("3", "1111111111113", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
            dc.addConsultation("6", "1111111111113", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
            assert (true);
        }
        catch (ConsultationException e){
            assert (false);
        }
    }

    @Test
    public void listPatients() throws PatientException, ConsultationException {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        repository.cleanFiles();
        DoctorController dc = new DoctorController(repository);
        dc.addPatient(new Patient("John Doe the first", "1111111111111", "addr1"));
        dc.addPatient(new Patient("John Doe the second", "1111111111112", "addr1"));
        dc.addPatient(new Patient("John Doe the third", "1111111111113", "addr1"));
        dc.addConsultation("11", "1111111111111", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
        dc.addConsultation("14", "1111111111111", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
        dc.addConsultation("15", "1111111111111", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
        dc.addConsultation("12", "1111111111112", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
        dc.addConsultation("13", "1111111111113", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
        dc.addConsultation("16", "1111111111113", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
        List<Patient> patients = dc.getPatientsWithDisease("Cold");
        assert(patients.get(0).getSSN().equals("1111111111111"));
        assert(patients.get(1).getSSN().equals("1111111111113"));
        assert(patients.get(2).getSSN().equals("1111111111112"));
    }

    @Test
    public void integrate() throws Exception {
        addValidPatient();
        addValidConsultation();
        listPatients();
    }
}
