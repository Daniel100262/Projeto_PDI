package application;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AplicaFiltrosController {
	
	int pxClicado;
	int pyClicado;
	int pxSolto;
	int pySolto;
	
	int positionStartX;
	int positionStartY;
	
	int positionEndX;
	int positionEndY;
	
	@FXML ImageView imageview1;
	@FXML ImageView imageview2;
	@FXML ImageView imageview3;

	@FXML Label lblR;
	@FXML Label lblG;
	@FXML Label lblB;
	@FXML Label lblLimiar;
	
	@FXML Button btRemoveMaisRuido;
	
	@FXML TextField pcr;
	@FXML TextField pcg;
	@FXML TextField pcb;
    @FXML TextField txtQ1;
    @FXML TextField txtQ2;
	
	@FXML Slider sliLimiar;
	@FXML Slider sliderImg1;
	@FXML Slider sliderImg2;
	

    @FXML private ToggleGroup rbTpRuido;
	@FXML private RadioButton cbVizC;
	@FXML private RadioButton cbVizX;
	@FXML private RadioButton cbViz3x3;
	
	private Image img1;
	private Image img2;
	private Image img3;
	
	
	// ================= Abrindo imagens e atualizando ===================
	@FXML
	public void abreImagem1() {
		img1 = abreImagem(imageview1, img1);
	}
	
	@FXML
	public void abreImagem2() {
		img2 = abreImagem(imageview2, img2);
	}
	
	private void atualizaImagem3() {
		imageview3.setImage(img3);
		imageview3.setFitWidth(img3.getWidth());
		imageview3.setFitHeight(img3.getHeight());
	}
	
	private void atualizaImagem1() {
		imageview1.setImage(img1);
		imageview1.setFitWidth(img1.getWidth());
		imageview1.setFitHeight(img1.getHeight());
	}
	//====================================================================
	
	
	
	public void cinzaAritmetica(){
		img3 = Filtros.cinzaMediaAritmetica(img1, 0, 0, 0);
		atualizaImagem3();
	}
	
	@FXML
	public void cinzaPonderado(){
		img3 = Filtros.escalaDeCinza(img1, Integer.parseInt(pcr.getText()), Integer.parseInt(pcg.getText()), Integer.parseInt(pcb.getText()));
		atualizaImagem3();
	}
	
	@FXML
	public void limirizar() {
		img3 = Filtros.limiarizacao(img1, sliLimiar.getValue()/100);
		atualizaImagem3();
	}
	
	@FXML
	public void negativar() {
		img3 = Filtros.negativa(img1);
		atualizaImagem3();
	}
	
	
	@FXML
	public void limiar(){
		img3 = Filtros.escalaDeCinza(img1, Integer.parseInt(pcr.getText()), Integer.parseInt(pcg.getText()), Integer.parseInt(pcb.getText()));
		atualizaImagem3();
	}
	
	@FXML public void divideDiagonal() {
		img3 = Filtros.DividirDiagonal(img1);
		atualizaImagem3();
	}
	
	@FXML public void checaQuadrado() {
		 Filtros.ChecaRetangulo(img1);
	}
	
	
	@FXML
	public void removeRuidos() {
		if(rbTpRuido.getSelectedToggle().equals(cbVizX)) {
			img3 = Filtros.ruidos(img1, Constantes.VIZINHOSX);
			atualizaImagem3();
			
		}
		if(rbTpRuido.getSelectedToggle().equals(cbVizC)) {
			img3 = Filtros.ruidos(img1, Constantes.VIZINHOSCRUZ);
			atualizaImagem3();
		}
		if(rbTpRuido.getSelectedToggle().equals(cbViz3x3)) {
			img3 = Filtros.ruidos(img1, Constantes.VIZINHOS3x3);
			atualizaImagem3();
		}
	}
	
	@FXML
	public void adicao() {
		img3 = Filtros.adicao(img1, img2, sliderImg1.getValue()/100, sliderImg2.getValue()/100);
		atualizaImagem3();
	}
	
	@FXML
	public void subtracao() {
		img3 = Filtros.subtracao(img1, img2, sliderImg1.getValue()/100, sliderImg2.getValue()/100);
		atualizaImagem3();
	}
	 
	 
	@FXML
	public void salvarImagem() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new 
				FileChooser.ExtensionFilter("PNG",".png"));
		fileChooser.setTitle("Salvar Imagem");
		File file = fileChooser.showSaveDialog(null); 
			
		if (file != null) {
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(img3, null), "png", file);
			} catch (IOException ex) {
				System.out.println("aconteceu um erro inesperado");
			}
		}
		
	}
	
	@FXML public void viraQuadrantes() {
		img3 = Filtros.InverteQuadrantes(img1, Integer.parseInt(txtQ1.getText()), Integer.parseInt(txtQ2.getText()));
		atualizaImagem3();
	}
	 
	 
	 @FXML
	  public void rasterImg(MouseEvent evt){
		 ImageView iw = (ImageView)evt.getTarget();
		 if(iw.getImage()!=null)
			 verificaCor(iw.getImage(), (int)evt.getX(), (int)evt.getY());
	  }
	 
	 @FXML
	 public void equalizacao() {
		 img3 = Filtros.equalizacaoHistograma(img1, true);
		 atualizaImagem3();
	 }
	
	 @FXML
	 public void equalizacaoValidos() {
		 img3 = Filtros.equalizacaoHistograma(img1, false);
		 atualizaImagem3();
	 }

	
	 private void verificaCor(Image img, int x, int y){
		  try {
				Color cor = img.getPixelReader().getColor(x-1, y-1); // -1 pq vetor java começa em zero  spel checking desabilitado
				lblR.setText("R: "+(int) (cor.getRed()*255)); //Valores de 0 a 1 * 255
				lblG.setText("G: "+(int) (cor.getGreen()*255));
				lblB.setText("B: "+(int) (cor.getBlue()*255));
			} catch (Exception e) {
				//e.printStackTrace();
			}
	  }

	
	 private Image abreImagem(ImageView imageView, Image image) {
		File f = selecionaImagem();
		if(f != null) {
			image = new Image(f.toURI().toString());
			imageView.setImage(image);
			imageView.setFitWidth(image.getWidth());
			imageView.setFitHeight(image.getHeight());
			return image;
		}
		return null;
	}
	 
	 @FXML
	 public void abreModalHistograma(ActionEvent event) {
		 try {
			 	Stage stage = new Stage();
			 	FXMLLoader loader = new FXMLLoader(getClass().getResource("ModalHistograma.fxml"));
			 	Parent root = loader.load();
			 	stage.setScene(new Scene(root));
			 	stage.setTitle("Histograma");
			 	stage.initOwner(((Node)event.getSource()).getScene().getWindow());
			 	stage.show();
			 	ModalHistogramaController controller = (ModalHistogramaController)loader.getController();
			 	
			 	if(img1!=null) {
			 		Filtros.getGrafico(img1, controller.bcImagem1);
			 	}
			 	
			 	if(img2!=null) {
			 		Filtros.getGrafico(img2, controller.bcImagem2);
			 	}
			 	
			 	if(img3!=null) {
			 		Filtros.getGrafico(img3, controller.bcImagem3);
			 	}
			 	
			 	
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 
	 
	 
	public void initialize() {
		setOverrideEventsImageView();
	}
	
		
	private void setOverrideEventsImageView() {
			System.out.println("entrou override");
			imageview1.setOnMousePressed(e -> {
				positionStartX = (int) e.getX();
				positionStartY = (int) e.getY();
			});
			imageview1.setOnMouseReleased(e -> {
				positionEndX = (int)e.getX();
				positionEndY = (int)e.getY();
				//System.out.println("X_FINAL: "+positionEndX+"\nY_FINAL: "+positionEndY);
				DesenhaQuadrado();
				
			});
		}
	 
		private void DesenhaQuadrado() {
			//System.out.println("Chamou drawsquare\n"+positionStartX+"\n"+positionStartY+"\n"+positionEndX+"\n"+positionEndY);
			img1 = Filtros.desafio(img1, positionStartX, positionStartY, positionEndX, positionEndY);
			atualizaImagem1();
		}
	 
	private File selecionaImagem() {
		   FileChooser fileChooser = new FileChooser();
		   fileChooser.getExtensionFilters().add(new 
				   FileChooser.ExtensionFilter(
						   "Imagens", "*.jpg", "*.JPG", 
						   "*.png", "*.PNG", "*.gif", "*.GIF", 
						   "*.bmp", "*.BMP")); 	
		   fileChooser.setInitialDirectory(new File(
				   "G:\\Arquivos_Usuario\\DeskWin10\\imgs"));
		   File imgSelec = fileChooser.showOpenDialog(null);
		   try {
			   if (imgSelec != null) {
			    return imgSelec;
			   }
		   } catch (Exception e) {
			e.printStackTrace();
		   }
		   return null;
	}
	
}
