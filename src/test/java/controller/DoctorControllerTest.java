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

    @Test
    public void addLongCNP() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("John Doe", "1234567890123456", "addr1"));
            assert (false);
        }
        catch (PatientException e){
            assert (true);
        }
    }

    @Test
    public void addShortCNP() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("John Doe", "12345", "addr1"));
            assert (false);
        }
        catch (PatientException e){
            assert (true);
        }
    }

    @Test
    public void addSmallLettersCNP() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("John Doe", "abc", "addr1"));
            assert (false);
        }
        catch (PatientException e){
            assert (true);
        }
    }

    @Test
    public void addCapitalLettersCNP() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("John Doe", "ABC", "addr1"));
            assert (false);
        }
        catch (PatientException e){
            assert (true);
        }
    }

    @Test
    public void addZeroLengthName() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("", "1234567890123", "addr1"));
            assert (false);
        }
        catch (PatientException e){
            assert (true);
        }
    }

    @Test
    public void addNameWithDigits() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("abc123", "1234567890123", "addr1"));
            assert (false);
        }
        catch (PatientException e){
            assert (true);
        }
    }

    @Test
    public void addZeroLengthAddress() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("John Doe", "1234567890123", ""));
            assert (false);
        }
        catch (PatientException e){
            assert (true);
        }
    }
}