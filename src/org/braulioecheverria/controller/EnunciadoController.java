package org.braulioecheverria.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import org.braulioecheverria.dao.Conexion;
import org.braulioecheverria.model.Enunciado;
import org.braulioecheverria.system.Main;

/**
 * FXML Controller class
 *
 * @author Brau
 */
public class EnunciadoController implements Initializable {
    @FXML
    private TextField txtTituloEnunciado;
    @FXML
    private TextField txtCarne;
    @FXML
    private TextArea dspEnunciado;
    @FXML
    private Button btnGenerar;
    @FXML
    private Button btnCerrar;
    private Enunciado enunciado = new Enunciado();
    private Main escenarioPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dspEnunciado.setEditable(false);
        txtTituloEnunciado.setEditable(false);
        txtTituloEnunciado.setAlignment(Pos.CENTER);
        btnCerrar.setDisable(true);
    }


    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void generarEnunciado(ActionEvent event ) {
        if(txtCarne.getText().length() == 7){
            Random rand = new Random();
            int numeroAleatorio = rand.nextInt(15) + 1;
            try {
                PreparedStatement sp1 = Conexion.getInstancia().getConexion().prepareCall("call sp_registrarAsignacionEnunciado(?,?)");
                sp1.setInt(1,Integer.parseInt(txtCarne.getText()));
                sp1.setInt(2,numeroAleatorio);
                sp1.execute();
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Fundación Kinal");
                aviso.setHeaderText("Generador de enunciados");
                aviso.setContentText("Enunciado generado Exitosamente!!!");
                aviso.show();
                try {
                    PreparedStatement sp = Conexion.getInstancia().getConexion().prepareCall("call sp_buscarEnunciado(?);");
                    sp.setInt(1,numeroAleatorio);
                    ResultSet respuesta = sp.executeQuery();
                    if(respuesta.next()){
                        enunciado.setIdEnunciado(respuesta.getInt(1));
                        enunciado.setNombreEnunciado(respuesta.getString(2));
                        enunciado.setDescripcionEnunciado(respuesta.getString(3));
                        txtTituloEnunciado.setAlignment(Pos.CENTER_LEFT);
                        txtTituloEnunciado.setText(enunciado.getNombreEnunciado());
                        dspEnunciado.setText(enunciado.getDescripcionEnunciado());
                        btnGenerar.setDisable(true);
                        btnCerrar.setDisable(false);
                    }
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                    Alert aviso1 = new Alert(Alert.AlertType.ERROR);
                    aviso1.setTitle("Fundación Kinal");
                    aviso1.setHeaderText("Generador de enunciados");
                    aviso1.setContentText("Carné Erroneo: No fue posible obtener un enunciado");
                    aviso1.show();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                Alert aviso = new Alert(Alert.AlertType.ERROR);
                aviso.setTitle("Fundación Kinal");
                aviso.setHeaderText("Generador de enunciados");
                aviso.setContentText("Este número ya recibió una asignación previamente");
                aviso.show();
            }
        }else{
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Fundación Kinal");
            aviso.setHeaderText("Generador de enunciados");
            aviso.setContentText("Carné Erroneo: No tiene el formato adecuado");
            aviso.show();
        }
    }

    public void cerrarPrograma(ActionEvent event) {
        System.exit(0);
    }
}
