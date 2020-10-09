package view.tela;

import dao.visitanteDao;
import entity.Visitante;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerItem extends Application{

    @FXML
    private HBox itemC;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbRg;

    @FXML
    private Label lbCpf;

    @FXML
    private Label lbTelefone;

    @FXML
    private Label lbEmail;

    @FXML
    private Label lbDatavisita;

    public void mudar() {
    	try {
    		lbNome.setText("123");;
    		System.out.println("123");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    public void buscarID(int listarId) {
    	Visitante visitante = null;
    		try {
    			visitante = new visitanteDao().findByID(listarId);
    			lbNome.setText("Willian");
    			lbRg.setText(visitante.getRG().toString());
    			lbCpf.setText(visitante.getCPF().toString());
    			lbTelefone.setText(visitante.getTelefone().toString());
    			lbEmail.setText(visitante.getEmail().toString());
    			lbDatavisita.setText(visitante.getDataVisita().toString());
    		} catch (Exception e) {
    	
    		}
    }// Fim metodo buscarID
    
    public void execute() {
        launch();
        this.mudar();
    }
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		  HBox root =(HBox) FXMLLoader.load(getClass().getResource("Item.fxml"));

	        primaryStage.setScene(new Scene(root));
	        //set stage borderless
	        primaryStage.initStyle(StageStyle.UNDECORATED);

	        primaryStage.show();		
	}
}
