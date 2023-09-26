package org.braulioecheverria.system;

import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.braulioecheverria.controller.EnunciadoController;

/**
 *
 * @author Brau
 */
public class Main extends Application {
    private final String PAQUETE_VIEW = "../views/";
    private Stage escenarioPrinicipal;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage escenarioPrincipal) throws Exception{
        this.escenarioPrinicipal = escenarioPrincipal;
        this.escenarioPrinicipal.setTitle("Examen Final Bimestre V");
        Image icono = new Image(getClass().getResourceAsStream("/org/braulioecheverria/assets/img/icono.png"));
        this.escenarioPrinicipal.getIcons().add(icono);
        this.mostrarEscenaEnunciado();
        this.escenarioPrinicipal.show();
    }
    
    public void mostrarEscenaEnunciado(){
        try{
            EnunciadoController controlador = (EnunciadoController) this.cambiarEscena("EnunciadoView.fxml", 760, 516);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public Initializable cambiarEscena(String escena, int ancho, int alto) throws IOException{
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(this.PAQUETE_VIEW + escena));
        AnchorPane root = (AnchorPane) cargadorFXML.load();
        Scene scene = new Scene(root,ancho,alto);
        this.escenarioPrinicipal.setScene(scene);
        this.escenarioPrinicipal.sizeToScene();
        resultado = (Initializable) cargadorFXML.getController();
        return resultado;
    }
    
}
