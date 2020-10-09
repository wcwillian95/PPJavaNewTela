package principal;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.funcionario.ControllerFuncionario;
import view.login.ControllerLogin;
import view.proprietario.ControllerProprietario;
import view.sindico.ControllerSindico;
import view.tela.Controller;
import view.visitante.ControllerVisitante;

public class Principal {

	public static void main(String[] args) {

		//new ControllerSindico().execute();
		//new ControllerVisitante().execute();
		//new ControllerProprietario().execute();
		new Controller().execute();
		//new ControllerFuncionario().execute();
	}

}
