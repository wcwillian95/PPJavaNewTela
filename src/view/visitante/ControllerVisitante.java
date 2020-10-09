package view.visitante;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


import alerts.ShowAlert;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import dao.visitanteDao;
import entity.Visitante;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerVisitante extends Application implements Initializable{

    @FXML
    private Button BTNSalvar;

    @FXML
    private Button BTNEditar;

    @FXML
    private Button BTNExcluir;

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtCPF;

    @FXML
    private TextField TxtTelefone;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextArea textAreaLista;

    @FXML
    private TextField TXId;

    @FXML
    private Label Labelid;

    @FXML
    private Label LabelLabel;

    @FXML
    private Button BTNBuscarID;

    @FXML
    private DatePicker datePickerVisita;

    @FXML
    private TextField TxtRG;
    
    @FXML
    private TableView<Visitante> TableView;
    
    @FXML
    private TableColumn<Visitante, Integer> tcId;


    @FXML
    private TableColumn<Visitante, String> tcNome;

    @FXML
    private TableColumn<Visitante, String> tcRg;

    @FXML
    private TableColumn<Visitante, String> tcCpf;

    @FXML
    private TableColumn<Visitante, String> tcTelefone;

    @FXML
    private TableColumn<Visitante, String> tcEmail;

    @FXML
    private TableColumn<Visitante, String> tcDatavisita;
    
    public void StartTable() {
    	tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	tcNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
    	tcRg.setCellValueFactory(new PropertyValueFactory<>("RG"));
    	tcCpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
    	tcTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
    	tcEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
    	tcDatavisita.setCellValueFactory(new PropertyValueFactory<>("DataVisita"));
    	TableView.setItems(atualizaTabela());
    }
    
    public ObservableList<Visitante> atualizaTabela(){
    	visitanteDao dao = new visitanteDao();
    	return FXCollections.observableArrayList(dao.listAll());
    }
    


    @FXML
    void buscarID(ActionEvent event) {
    	String idString = TXId.getText();
    	Visitante visitante = null;
    	if(!idString.equals("")) {
    		try {
    			int id = Integer.valueOf(idString);
    			visitante = new visitanteDao().findByID(id);
    		} catch (Exception e) {
    	
    		}
    		if(visitante != null) {
    			Labelid.setVisible(true);
    			LabelLabel.setVisible(true);
    			LabelLabel.setText(visitante.getId()+  "");
    			TxtNome.setText(visitante.getNome());
    			TxtRG.setText(visitante.getRG());
    			TxtCPF.setText(visitante.getCPF());
    			TxtTelefone.setText(visitante.getTelefone());
    			TxtEmail.setText(visitante.getEmail());
    			datePickerVisita.setValue(visitante.getDataVisita().toLocalDate());
    		}
    	}
    	TXId.clear();
    }// Fim metodo buscarID
    
    @FXML
    void Excluir(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
  	 alert.setTitle("Alerta!");
	 alert.setHeaderText("Dados � serem apagados.");
	 alert.setContentText("Pressione OK para apagar este usuario.");
       Optional<ButtonType> result = alert.showAndWait();
       
        if (result.get() == ButtonType.OK){
        	System.out.println("Cadastro Apagado");
            Visitante visitante = obtemDadosID();
            int qtde = new visitanteDao().deletar(visitante.getId());
            limpaCampo();
            listarVisitantes();
        }
    }// Fim Metodo Excluir

    @FXML
    void Salvar(ActionEvent event) {
    	if(validaCampos()) {
	    	Visitante visitante = obtemDados();
	    	limpaCampo();
	    	int qtde = new visitanteDao().inserir(visitante);
	    	listarVisitantes();
	    	StartTable();
	    	System.out.println(qtde);
    	}else {
    	new ShowAlert().validaAlert();
    	}
    }// Fim metodo Salvar
    
    @FXML
    void Editar(ActionEvent event) {
    	if(validaCampos()) {
	    	Visitante visitante = obtemDadosID();
	    	limpaCampo();
	    	int qtde = new visitanteDao().alterar(visitante);
	    	listarVisitantes();
	    	StartTable();
    	}else {
    	new ShowAlert().validaAlert();
    	}
    }
    
    public boolean validaCampos() {	
		if(TxtNome.getText().isEmpty() | TxtRG.getText().isEmpty() | TxtCPF.getText().isEmpty() | TxtTelefone.getText().isEmpty() | TxtEmail.getText().isEmpty()) {
			return false;
		}	
		return true;
	}
    private void limpaCampo() {
    	TxtNome.clear();
    	TxtRG.clear();
    	TxtCPF.clear();
    	TxtTelefone.clear();
    	TxtEmail.clear();
    	datePickerVisita.setValue(null);
    			
    	TxtNome.requestFocus();
    	
    	LabelLabel.setVisible(false);
    	LabelLabel.setText("");
    	Labelid.setVisible(false);
    }
    
    private Visitante obtemDados() {
    	return new Visitante(TxtNome.getText(),TxtRG.getText(), TxtCPF.getText(), TxtTelefone.getText(), TxtEmail.getText(), java.sql.Date.valueOf(datePickerVisita.getValue()));
    }

    private Visitante obtemDadosID() {
    	
    	return new Visitante(Integer.valueOf(LabelLabel.getText()), TxtNome.getText(), TxtRG.getText(), TxtCPF.getText(), TxtTelefone.getText(), TxtEmail.getText(), java.sql.Date.valueOf(datePickerVisita.getValue()));
    }
    
    private void listarVisitantes() {
    	textAreaLista.clear();
    	List<Visitante> listaVisitante = new visitanteDao().listAll();
    listaVisitante.forEach(visitante -> {
    	textAreaLista.appendText(visitante.toString() +"\n");
    });
    }
    
    public void execute() {
        launch();
    }

    @Override
    public void start(Stage stage) {

        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("VisitanteView.fxml"));
            Scene sc = new Scene(pane);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }   	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		StartTable();
		listarVisitantes();
		BTNEditar.setDefaultButton(false);
				
	}	 
}