package se.project.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Util {

  private Pane view;

  // load pane with bike data
	/*public void loadPaneData(String scene, BikeType bike,MouseEvent node) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(scene));
		try {
			Parent root = loader.load();
			ShopController controller = loader.getController();
			controller.setBikeData(bike); // set bike info
			//load
			Stage stage = (Stage) ((Node)node.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
			
	
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

  /*
   * @scene : scene path
   *
   * @but : button source return switch another scene
   */
  public void change(String scene, Button but) {
    try {
      Parent root = (Parent) FXMLLoader.load(getClass().getResource(scene));
      Stage stage = (Stage) but.getScene().getWindow();
      // para event Stage stage = (Stage) (Node)event.getScene().getWindow();
      stage.setScene(new Scene(root));

      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // load scene
  public Pane getPage(String fileName) {

    try {

      view = FXMLLoader.load(getClass().getResource(fileName));
    } catch (Exception e) {
      System.out.print(e);
    }
    return view;
  }
	
	/*
	public Pane getPage(String fileName,BikeType bike) {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(fileName));
	    try {
	    	view = loader.load();
			// access the controller and call a method
			// access the controller and call a method
			
			if(fileName.equals("/se/project/gui/pay.fxml")) {
				
			PayController controller = loader.getController();
			controller.setBikeData(bike,order); // set bike info
			}
			
			if(fileName.equals("/se/project/gui/scene1.fxml")) {
				Scene1Controller controller = loader.getController();
				controller.setBikeData(bike,order); // set bike info
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	   
	    return view;    
	}*/

}
