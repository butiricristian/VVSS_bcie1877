package controller;


import exceptions.PatientException;
import model.Patient;
import org.junit.Test;
import repository.Repository;

public class DoctorControllerTest {
    @Test
    public void addPatient() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        Integer size = dc.getPatientList().size();
        dc.addPatient(new Patient("John Doe", "1111111111111", "addr1"));
        assert (dc.getPatientList().size() == size + 1);
    }

    @Test
    public void addInvalidPatient() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("John Doe", "1111111111", "addr1"));
            assert (false);
        }
        catch (PatientException e){
            assert (true);
        }

    }
}