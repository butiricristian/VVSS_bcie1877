package controller;


import exceptions.ConsultationException;
import exceptions.PatientException;
import model.Patient;
import org.junit.Test;
import repository.Repository;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Test
    public void addConsultationWithNullMeds() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("John Doe", "1234567890123", "Some address"));
            dc.addConsultation("1", "1234567890123", "Cold", null, "12-12-2018");
            assert (false);
        }
        catch (ConsultationException e){
            assert (true);
        }
    }

    @Test
    public void addConsultationWithNullConsID() throws Exception {
        Repository repository = new Repository("src/main/FilePatients.txt", "src/main/FileConsultations.txt");
        DoctorController dc = new DoctorController(repository);
        try {
            dc.addPatient(new Patient("John Doe", "1234567890123", "Some address"));
            dc.addConsultation(null, "1234567890123", "Cold", Arrays.asList("Med1", "Med2"), "12-12-2018");
            assert (false);
        }
        catch (ConsultationException e){
            assert (true);
        }
    }
}