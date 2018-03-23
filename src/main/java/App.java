import controller.DoctorController;
import repository.Repository;
import ui.DoctorUI;

public class App {

	public static void main(String[] args) {
		String patients = "src/main/FilePatients.txt";
		String consultations = "src/main/FileConsultations.txt";
		Repository repo = new Repository(patients, consultations);
		DoctorController ctrl = new DoctorController(repo);
		
		DoctorUI console = new DoctorUI(ctrl);
		console.Run();
	}
}
