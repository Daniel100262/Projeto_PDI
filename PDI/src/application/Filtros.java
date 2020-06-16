package application;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Filtros {
	public static Image cinzaMediaAritmetica(Image imagem, int pcr, int pcb, int pcg) {
		try {
			int w = (int)imagem.getWidth();
			int h = (int)imagem.getHeight();
			
			PixelReader pr = imagem.getPixelReader();
			WritableImage wi = new WritableImage(w,h);
			PixelWriter pw = wi.getPixelWriter();
			
			for(int i=0; i<w; i++) {
				for(int j=0; j<h; j++) {
					Color corA = pr.getColor(i,j);
					double media = (corA.getRed()+corA.getGreen()+corA.getBlue())/3;
					if(pcr != 0 || pcg != 0 || pcb != 0)
						media = (corA.getRed()*pcr + corA.getGreen()*pcg +corA.getBlue()*pcb)/100;
					Color corN = new Color(media, media, media, corA.getOpacity());
					pw.setColor(i, j, corN);
				}
			}
			return wi;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image negativa (Image imagem) {
		try {
			
			int w = (int)imagem.getWidth();
			 int h = (int)imagem.getHeight();
			 
			 PixelReader pr = imagem.getPixelReader();
			 WritableImage wi = new WritableImage(w,h);
			 PixelWriter pw = wi.getPixelWriter();
			 
			 for (int i=0; i<w; i++) {
				 for (int j=0; j<h; j++) {
					 Color corA = pr.getColor(i, j);
					 Color corN = new Color ( 1-corA.getRed(),  1-corA.getGreen(),  1-corA.getBlue(), corA.getOpacity());
					 pw.setColor(i, j, corN); 
				 }
			 }
			 return wi;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static int[] histogramaUnico(Image img) {
		int[] qt = new int [256];
		PixelReader pr = img.getPixelReader();
		int w = (int)img.getWidth();
		int h = (int)img.getHeight();
		
		for(int i=0; i<w; i++) {
			for(int j=0; j<h; j++) {
				qt[(int)(pr.getColor(i,j).getRed()*255)]++;
				qt[(int)(pr.getColor(i,j).getGreen()*255)]++;
				qt[(int)(pr.getColor(i,j).getBlue()*255)]++;
			}
		}
		return qt;
	}
	
	public static int[] histograma(Image img, Color cor) {
		int[] qt = new int [256];
		PixelReader pr = img.getPixelReader();
		int w = (int)img.getWidth();
		int h = (int)img.getHeight();
		
		if(Color.RED.equals(cor)) {
			for(int i=0; i<w; i++) {
				for(int j=0; j<h; j++) {
					qt[(int)(pr.getColor(i,j).getRed()*255)]++;
				}
			}
		}
		else if(Color.GREEN.equals(cor)) {
			for(int i=0; i<w; i++) {
				for(int j=0; j<h; j++) {
					qt[(int)(pr.getColor(i,j).getGreen()*255)]++;
				}
			}
		}
		else if(Color.BLUE.equals(cor)) {
			for(int i=0; i<w; i++) {
				for(int j=0; j<h; j++) {
					qt[(int)(pr.getColor(i,j).getBlue()*255)]++;
				}
			}
		}
		return qt;
	}
	
	
	
	//@SuppressWarnings({ "rawtypes", "unchecked" })
	//public static void getGrafico(Image img, BarChart<String, Number> grafico) {
	//	int[] hist = histogramaUnico(img);
	//	XYChart.Series vlr = new XYChart.Series();
	//	for(int i=0; i<hist.length; i++) {
	//		vlr.getData().add(new XYChart.Data(i+"", hist[i]));
	//	}
	//	grafico.getData().addAll(vlr);
	//	
	//	for(Node n:grafico.lookupAll(".default-color0.chart-bar")) {
	//		n.setStyle("-fx-bar-fill: red;");
	//	}
	//	
	//	for(Node n:grafico.lookupAll(".default-color1.chart-bar")) {
	//		n.setStyle("-fx-bar-fill: green;");
	//	}
	//	
	//	for(Node n:grafico.lookupAll(".default-color2.chart-bar")) {
	//		n.setStyle("-fx-bar-fill: blue;");
	//	}
	//}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void getGrafico(Image img, BarChart<String, Number> grafico) {
		int[] histR = histograma(img, Color.RED);
		int[] histG = histograma(img, Color.GREEN);
		int[] histB = histograma(img, Color.BLUE);
		XYChart.Series vlrR = new XYChart.Series();
		XYChart.Series vlrG = new XYChart.Series();
		XYChart.Series vlrB = new XYChart.Series();

		for (int i = 0; i < 256; i++) {
			vlrR.getData().add(new XYChart.Data(i + "", histR[i]));
			vlrG.getData().add(new XYChart.Data(i + "", histG[i]));
			vlrB.getData().add(new XYChart.Data(i + "", histB[i]));
		}
		grafico.getData().addAll(vlrR, vlrG, vlrB);

		for (Node n : grafico.lookupAll(".default-color0.chart-bar")) {
			n.setStyle("-fx-bar-fill:red;");
		}
		for (Node n : grafico.lookupAll(".default-color1.chart-bar")) {
			n.setStyle("-fx-bar-fill:green;");
		}
		for (Node n : grafico.lookupAll(".default-color2.chart-bar")) {
			n.setStyle("-fx-bar-fill:blue;");
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * Primeira prova
	 * 
	 */
	
	 public static Image InverteQuadrantes(Image img, int qMod1, int qMod2) {

		 int w = (int)img.getWidth();
		 int h = (int)img.getHeight();
		 int MetadeLargura = w/2; //Primeiro e terceiro de um lado, segundo e quarto do outro
		 int MetadeAltura = h/2; //Primeiro e segundo em cima, terceiro e quarto em baixo
		 PixelReader pr = img.getPixelReader();
		 WritableImage wi = new WritableImage(w, h);
		 PixelWriter pw = wi.getPixelWriter();

		 ArrayList<Color> quad1 = new ArrayList<>();
		 ArrayList<Color> quad2 = new ArrayList<>();
		 ArrayList<Color> quad3 = new ArrayList<>();
		 ArrayList<Color> quad4 = new ArrayList<>();

		 for (int i = 0; i < w; i++) {
			 for (int j = 0; j < h; j++) {
				 pw.setColor(i, j, pr.getColor(i, j));
			 }	
		 }

	        
	        /*
	         * Calcula primeiro quadrante
	    	 * 
	    	 */
	        
		 if (qMod1 == 1.0 || qMod2 == 1.0) {
			 for (int i = 0; i < MetadeLargura; i++) {
				 for (int j = 0; j < MetadeAltura; j++) {
					 quad1.add(pr.getColor(i, j)); // --> Joga em quad1
				 }
			 }

			 int count = 0;
			 for (int i = (MetadeLargura)-1; i > 0; i--) {
				 for (int j = (MetadeAltura)-1; j > 0; j--) {
					 pw.setColor(i, j, quad1.get(count));
					 count++;
				 }
				 count++;
			 }	
		 }
	        
	        

	        /*
	    	 * Calcula segundo quadrante
	    	 * 
	    	 */
	        
		 if (qMod1 == 2.0 || qMod2 == 2.0) {
			 for (int i = MetadeLargura; i < w; i++) {
				 for (int j = 0; j < MetadeAltura; j++) {
					 quad2.add(pr.getColor(i, j));
				 }
			 }

	            int count = 0;
	            for (int i = w-1; i > (MetadeLargura)-1; i--) {
	                for (int j = (MetadeAltura)-1; j > 0; j--) {
	                    pw.setColor(i, j, quad2.get(count));
	                    count++;
	                }
	                count++;
	            }
	        }
	        
	        

	        /*
	    	 * Calcula terceiro quadrante
	    	 * 
	    	 */
	        
	        if (qMod1 == 3.0 || qMod2 == 3.0) {
	            for (int i = 0; i < MetadeLargura; i++) {
	                for (int j = MetadeAltura; j < h; j++) {
	                    quad3.add(pr.getColor(i, j));
	                }
	            }

	            int count = 0;
	            for (int i = (MetadeLargura)-1; i > 0; i--) {
	                for (int j = h-2; j > (MetadeAltura)-1; j--) {
	                    pw.setColor(i, j, quad3.get(count));
	                    count++;
	                }
	                count++;
	            }
	        }

	        
	        /*
	    	 * Calcula quarto quadrante
	    	 * 
	    	 */
	        
	        if (qMod1 == 4.0 || qMod2 == 4.0) {
	            for (int i = MetadeLargura; i < w; i++) {
	                for (int j = MetadeAltura; j < h; j++) {
	                    quad4.add(pr.getColor(i, j));
	                }
	            }

	            int count = 0;
	            for (int i = w-1; i > (MetadeLargura)-1; i--) {
	                for (int j = h-2; j > (MetadeAltura)-1; j--) {
	                    pw.setColor(i, j, quad4.get(count));
	                    count++;
	                }
	                count++;
	            }
	        }

	        return wi;

	    }
	
	
	 
	 
	 
	 public static Image DividirDiagonal(Image img) {
		 int w = (int)img.getWidth();
	     int h = (int)img.getHeight();
	     PixelReader pr = img.getPixelReader();
	     WritableImage wi = new WritableImage(w, h);
	     PixelWriter pw = wi.getPixelWriter();
	     
	     Image imgEqualizada = equalizacaoHistograma(img, false);
	        
	     for (int i = 0; i < w -1; i++) {
	            for (int j = 0;j < h -1; j++) {
	                Color corA = pr.getColor(i, j);
	                if(i == j ) {
	                    pw.setColor(i,j,Color.BLACK);
	                }else if(i > j) {
	                    pw.setColor(i, j, imgEqualizada.getPixelReader().getColor(i, j));
	                }else {
	                    pw.setColor(i, j, corA);
	                }
	            }
	        }
	     return wi;
	 }
	 
	 
	 
	 public static void ChecaRetangulo(Image img) {
		int w = (int)img.getWidth();
	    int h = (int)img.getHeight();
	    PixelReader pr = img.getPixelReader();
	     
	    int[] CantoCimaEsquerdo = new int[2];
		int[] CantoCimaDireito = new int[2];
		int[] CantoBaixoEsquerda = new int[2];
		int[] CantoBaixoDireita = new int[2];
		
		boolean pxPreto = false;
			
	     for (int i=0; i<w-1; i++) {
	    	 for(int j=0; j<h-1; j++) {
	    		 
					if (pr.getColor(i, j).equals(Color.BLACK)){ //Quando encontra um pixel preto, verifica continuidade de linha em todas as direcoes
						pxPreto = true;
						if(pr.getColor(i+1, j).equals(Color.BLACK) && pr.getColor(i, j+1).equals(Color.BLACK)) {
							CantoCimaEsquerdo[0] = i;
							CantoCimaEsquerdo[1] = j;

						} else if (pr.getColor(i+1, j).equals(Color.BLACK) && pr.getColor(i, j-1).equals(Color.BLACK)) {
							CantoBaixoEsquerda[0] = i;
							CantoBaixoEsquerda[1] = j;

						} else if (pr.getColor(i-1, j).equals(Color.BLACK) && pr.getColor(i, j+1).equals(Color.BLACK)) {
							CantoCimaDireito[0] = i;
							CantoCimaDireito[1] = j;

						} else if (pr.getColor(i-1, j).equals(Color.BLACK) && pr.getColor(i, j-1).equals(Color.BLACK)) {
							CantoBaixoDireita[0] = i;
							CantoBaixoDireita[1] = j;
						}
					}
				}
	    	 }
	    if(!pxPreto) {
	    	JOptionPane.showMessageDialog(null, "Não ha pixels pretos na imagem!");
	   	 	return;
	     }
	     
	     //Linha esquerda para direita (cima)
		for (int i = CantoCimaEsquerdo[0]; i < CantoCimaDireito[0]; i++) {
			if (pr.getColor(i, CantoCimaEsquerdo[1]).equals(Color.WHITE)) {
				JOptionPane.showMessageDialog(null, "Retangulo aberto!");
				return;
			}
		}
			
		//Linha esquerda para direita (baixo)
		for (int i = CantoBaixoEsquerda[0]; i < CantoBaixoDireita[0]; i++) {
			if (pr.getColor(i, CantoBaixoEsquerda[1]).equals(Color.WHITE)) {
				JOptionPane.showMessageDialog(null, "Retangulo aberto!");
				return;
			}
		}
		
		
		//Linha de cima para baixo (esquerda)
		for (int j = CantoCimaEsquerdo[1]; j < CantoBaixoEsquerda[1]; j++) {
			if (pr.getColor(CantoCimaEsquerdo[0], j).equals(Color.WHITE)) {
				JOptionPane.showMessageDialog(null, "Retangulo aberto!");
				return;
			}
		}
			
		//Linha de cima para baixo (direita)
		for (int j = CantoCimaDireito[1]; j < CantoBaixoDireita[1]; j++) {
			if (pr.getColor(CantoCimaDireito[0], j).equals(Color.WHITE)) {
				JOptionPane.showMessageDialog(null, "Retangulo aberto!");
				return;
			}
		}

		JOptionPane.showMessageDialog(null, "Retangulo fechado!");
	     
	 }
	 
	 
	 
	 

	 
	
	public static Image equalizacaoHistograma(Image img, boolean todos) {
		int w = (int)img.getWidth();
		int h = (int)img.getHeight();
		
		PixelReader pr = img.getPixelReader();
		WritableImage wi = new WritableImage(w,h); 
		PixelWriter pw = wi.getPixelWriter();
		
		int[] hR = histograma(img, Color.RED);
		int[] hG = histograma(img, Color.GREEN);
		int[] hB = histograma(img, Color.BLUE);
		
		int[] histAcR = Util.histogramaAC(hR);
		int[] histAcG = Util.histogramaAC(hG);
		int[] histAcB = Util.histogramaAC(hB);
		
		int qtTonsRed = Util.qtTons(hR);
		int qtTonsGreen = Util.qtTons(hG);
		int qtTonsBlue = Util.qtTons(hB);
		
		double minR = pontoMin(hR);
		double minG = pontoMin(hG);
		double minB = pontoMin(hB);
		
		if (todos) {
			qtTonsRed = 255;
			qtTonsGreen = 255;
			qtTonsBlue = 255;
			
			minR = 0;
			minG = 0;
			minB = 0;
		}
		
		double n = w*h;
		
		
		for (int i=1; i<w; i++) {
			for (int j=1; j<h; j++) {
				Color oldColor = pr.getColor(i,j);
				double acR = histAcR[(int)(oldColor.getRed()*255)];
				double acG = histAcG[(int)(oldColor.getGreen()*255)];
				double acB = histAcB[(int)(oldColor.getBlue()*255)];
				
				double pxR = ((qtTonsRed-1)/n)*acR;
				double pxG = ((qtTonsGreen-1)/n)*acG;
				double pxB = ((qtTonsBlue-1)/n)*acB;
				
				double corR = (minR+pxR)/255;
				double corG = (minG+pxG)/255;
				double corB = (minB+pxB)/255;
				
				Color newCor = new Color(corR, corG, corB, oldColor.getOpacity());
				pw.setColor(i, j, newCor);
			}
		}
		return wi;
	}
	
	
	
	
	
	public static int pontoMin(int[] hist) {
		for (int i=0; i<hist.length; i++) {
			if(hist[i] > 0) {
				return i;
			}
		}
		return 0;
	}
	
	
	private static final double PCT_PIXEL_BORDA = 5;
	
	private static double getWidthBorder(double positionStartX, double positionStartY, double positionEndX, double positionEndY) {
		double width = positionEndX - positionStartX;
		double height = positionEndY - positionStartY;
		if (width < height) {
			return (width * PCT_PIXEL_BORDA)/100;
		} else {
			return (height * PCT_PIXEL_BORDA)/100;
		}
	}
	
	
	public static Image drawSquare(Image image, double positionStartX, double positionStartY, double positionEndX, double positionEndY) {
		try {	

			int w = (int)image.getWidth();
			int h = (int)image.getHeight();
			
			PixelReader pr = image.getPixelReader();
			WritableImage wi = new WritableImage(w,h);
			PixelWriter pw = wi.getPixelWriter();
			
			//caso o click começar de baixo e terminar encima, inverte o y inicial e final
			double aux = 0;
			if (positionStartY > positionEndY) {
				aux = positionStartY;
				positionStartY = positionEndY;
				positionEndY = aux;
			}
			
			//caso o click começar da direita para esquerda, inverte o x inicial e final
			if (positionStartX > positionEndX) {
				aux = positionStartX;
				positionStartX = positionEndX;
				positionEndX = aux;
			}
			
			double widthBorder = getWidthBorder(positionStartX, positionStartY, positionEndX, positionEndY); //pega a largura da borda de acordo com o calculo que pega a porcentagem da constante de tamanho de borda.
			
			for (int i = 1; i < w-1; i++) {
				for (int j = 1;j < h-1; j++) {
					Color colorPixel = pr.getColor(i, j);
					if (i >= positionStartX && i <= positionEndX && j >= positionStartY && j <= positionEndY) {
						if (i < positionStartX+widthBorder || i > positionEndX-widthBorder) {
							Color newColor = new Color(250.0/255.0, 250.0/255.0, 2.0/255.0, colorPixel.getOpacity());
							pw.setColor(i, j, newColor);							
						} else {
							if (j < positionStartY+widthBorder || j > positionEndY-widthBorder) {
								Color newColor = new Color(250.0/255.0, 250.0/255.0, 2.0/255.0, colorPixel.getOpacity());
								pw.setColor(i, j, newColor);	
							} else {
								pw.setColor(i, j, colorPixel);
							}
						}
					} else {
						pw.setColor(i, j, colorPixel);
					}
				}
			}
			return wi;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}	
	
	public static Image limiarizacao (Image imagem, double limiar) {
		try {
			 int w = (int)imagem.getWidth();
			 int h = (int)imagem.getHeight();
			 
			 PixelReader pr = imagem.getPixelReader();
			 WritableImage wi = new WritableImage(w,h);
			 PixelWriter pw = wi.getPixelWriter();
			 
			 for (int i=0; i<w; i++) {
				 for (int j=0; j<h; j++) {
					 Color corA = pr.getColor(i, j);
					 Color corN;
					 
					 if(corA.getRed() >= limiar) {
						 corN = new Color(1, 1, 1, corA.getOpacity());
					 }
					 else {
						 corN = new Color(0, 0, 0, corA.getOpacity());
					 }
					 pw.setColor(i, j, corN);
				 }
			 }
			 return wi;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Image adicao(Image img1, Image img2, double ti1, double ti2) {
		int w1 = (int)img1.getWidth();
		int h1 = (int)img1.getHeight();
		int w2 = (int)img2.getWidth();
		int h2 = (int)img2.getHeight();
		int w = Math.min(w1, w2);
		int h = Math.min(h1, h2);
		
		PixelReader pr1 = img1.getPixelReader();
		PixelReader pr2 = img2.getPixelReader();
		WritableImage wi = new WritableImage(w,h);
		PixelWriter pw = wi.getPixelWriter();
		
		for (int i = 1; i < w-1; i++) {
			for (int j = 1;j < h-1; j++) {
				Color colorImg1 = pr1.getColor(i, j);
				Color colorImg2 = pr2.getColor(i, j);
				double r = (colorImg1.getRed()*ti1) + (colorImg2.getRed()*ti2);
				double g = (colorImg1.getGreen()*ti1) + (colorImg2.getGreen()*ti2);
				double b = (colorImg1.getBlue()*ti1) + (colorImg2.getBlue()*ti2);
				r = r > 1 ? 1 : r;
				g = g > 1 ? 1 : g;
				b = b > 1 ? 1 : b;
				Color newColor = new Color(r, g, b, 1);
				pw.setColor(i, j, newColor);
			}
		}
		return wi;
	}
	
	
	
	
	
	
	
	public static Image subtracao(Image img1, Image img2, double ti1, double ti2) {
		int w1 = (int)img1.getWidth();
		System.out.println("Altura: "+w1);
		int h1 = (int)img1.getHeight();
		int w2 = (int)img2.getWidth();
		int h2 = (int)img2.getHeight();
		int w = Math.min(w1, w2);
		int h = Math.min(h1, h2);
		
		PixelReader pr1 = img1.getPixelReader();
		PixelReader pr2 = img2.getPixelReader();
		WritableImage wi = new WritableImage(w,h);
		PixelWriter pw = wi.getPixelWriter();
		
		for (int i = 1; i < w-1; i++) {
			for (int j = 1;j < h-1; j++) {
				Color colorImg1 = pr1.getColor(i, j);
				Color colorImg2 = pr2.getColor(i, j);
				double r = (colorImg1.getRed()*ti1) - (colorImg2.getRed()*ti2);
				double g = (colorImg1.getGreen()*ti1) - (colorImg2.getGreen()*ti2);
				double b = (colorImg1.getBlue()*ti1) - (colorImg2.getBlue()*ti2);
				r = r < 0 ? 0 : r;
				g = g < 0 ? 0 : g;
				b = b < 0 ? 0 : b;
				Color newColor = new Color(r, g, b, colorImg1.getOpacity());
				pw.setColor(i, j, newColor);
			}
		}
		return wi;
	}

	public static Image desafio(Image imagem, int PxClicado, int PyClicado, int PxSolto, int PySolto){
		
		int w = (int)imagem.getWidth();
		int h = (int)imagem.getHeight();
		
		PixelReader pr = imagem.getPixelReader();
		WritableImage wi = new WritableImage(w,h);
		PixelWriter pw = wi.getPixelWriter();
		
		//caso o click começar de baixo e terminar encima, inverte o y inicial e final
		int aux = 0;
		if (PyClicado > PySolto) {
			aux = PyClicado;
			PyClicado = PySolto;
			PySolto = aux;
		}
		
		//caso o click começar da direita para esquerda, inverte o x inicial e final
		if (PxClicado > PxSolto) {
			aux = PxClicado;
			PxClicado = PxSolto;
			PxSolto = aux;
		}
		
		Color corNova = Color.RED;
		
		for(int i=0; i<w; i++) {
			for(int j=0; j<h; j++) {
				pw.setColor(i, j, pr.getColor(i, j));	
			}
		}
		
		
		//Coluna esquerda
		for(int x=PxClicado; x<=PxSolto; x++) {
			pw.setColor(x,PyClicado , corNova);	
		}
			
		 //Coluna direita
		for(int x=PxClicado; x<=PxSolto; x++) {
			pw.setColor(x,PySolto , corNova);	
		}
			
		 //Linha de cima
		for(int y=PyClicado; y<=PySolto; y++) {
			pw.setColor(PxClicado,y , corNova);	
		}
			
		 //Linha de baixo
		for(int y=PyClicado; y<=PySolto; y++) {
			pw.setColor(PxSolto,y , corNova);	
		}
		
		return wi;
	}
	
	public static Image ruidos(Image imagem, int tipoVizinhos) {
		try {
			
			 int w = (int)imagem.getWidth();
			 int h = (int)imagem.getHeight();
			 
			 PixelReader pr = imagem.getPixelReader();
			 WritableImage wi = new WritableImage(w,h);
			 PixelWriter pw = wi.getPixelWriter();
			 
			 
			 for(int i=1; i<w-1; i++) {
				 for(int j=1; j<h-1; j++) {
					 Color corA = pr.getColor(i,j);
					 Pixel p = new Pixel(corA.getRed(), corA.getGreen(), corA.getBlue(), i, j);
					 buscaVizinhos(imagem, p);
					 Pixel vetor[] = null;
					 if(tipoVizinhos == 1) {
						 vetor = p.viz3;
					 }
					 if(tipoVizinhos == 2) {
						 vetor = p.vizC;
					 }
					 if(tipoVizinhos == 3) {
						 vetor = p.vizX;
					 }
					 double red = mediana(vetor, Constantes.CANALR);
					 double green = mediana(vetor, Constantes.CANALG);
					 double blue = mediana(vetor, Constantes.CANALB);
					 Color corN = new Color(red, green, blue, corA.getOpacity());
					 pw.setColor(i, j, corN);
				 }
			 }
			
			 
			 return wi;
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private static void buscaVizinhos(Image imagem, Pixel p) {
		p.vizX = buscaVizinhosX(imagem, p);
		p.vizC = buscaVizinhosC(imagem, p);
		p.viz3 = buscaVizinhos3(imagem, p);
	}
	
	private static Pixel[] buscaVizinhosX(Image imagem, Pixel p) {
		Pixel[] vizX = new Pixel[5];
		PixelReader pr = imagem.getPixelReader();
		
		Color cor = pr.getColor(p.x-1,p.y+1);
		vizX[0] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x-1, p.y+1);
		
		cor = pr.getColor(p.x+1, p.y-1);
		vizX[1] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x+1, p.y-1);
		
		cor = pr.getColor(p.x-1, p.y-1);
		vizX[2] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x-1, p.y-1);
		
		cor = pr.getColor(p.x+1, p.y+1);
		vizX[3] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x+1, p.y+1);
		
		vizX[4] = p;
		return vizX;
	}
	
	
	
	private static Pixel[] buscaVizinhosC(Image imagem, Pixel p) {
		Pixel[] vizC = new Pixel[5];
		PixelReader pr = imagem.getPixelReader();
		
		Color cor = pr.getColor(p.x,p.y+1);
		vizC[0] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x, p.y+1);
		
		cor = pr.getColor(p.x, p.y-1);
		vizC[1] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x, p.y-1);
		
		cor = pr.getColor(p.x-1, p.y);
		vizC[2] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x-1, p.y);
		
		cor = pr.getColor(p.x+1, p.y);
		vizC[3] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x+1, p.y);
		
		vizC[4] = p;
		return vizC;
	}
	
	private static Pixel[] buscaVizinhos3(Image imagem, Pixel p) {
		Pixel[] viz3 = new Pixel[9];
		PixelReader pr = imagem.getPixelReader();
		Color cor;
		
		cor = pr.getColor(p.x-1,p.y+1);
		viz3[0] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x-1, p.y+1);
		
		cor = pr.getColor(p.x+1, p.y-1);
		viz3[1] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x+1, p.y-1);
		
		cor = pr.getColor(p.x-1, p.y-1);
		viz3[2] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x-1, p.y-1);
		
		cor = pr.getColor(p.x+1, p.y+1);
		viz3[3] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x+1, p.y+1);
		
		cor = pr.getColor(p.x,p.y+1);
		viz3[4] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x, p.y+1);
		
		cor = pr.getColor(p.x, p.y-1);
		viz3[5] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x, p.y-1);
		
		cor = pr.getColor(p.x-1, p.y);
		viz3[6] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x-1, p.y);
		
		cor = pr.getColor(p.x+1, p.y);
		viz3[7] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x+1, p.y);
		
		viz3[8] = p;
		
		return viz3;
	}
	
	
	
	private static double mediana(Pixel[] pixels, int canal) {
		double v[] = new double[pixels.length];
		
		for(int i=0; i<pixels.length; i++) {
			if(canal == Constantes.CANALR) {
				v[i] = pixels[i].r;
			}
			if(canal == Constantes.CANALG) {
				v[i] = pixels[i].g;
			}
			if(canal == Constantes.CANALB) {
				v[i] = pixels[i].b;
			}
		}
		Arrays.sort(v);
		return v[v.length/2];
	}
	
	public static Image escalaDeCinza (Image imagem, int pcr, int pcg, int pcb) {
		 try {
			 
			 int w = (int)imagem.getWidth();
			 int h = (int)imagem.getHeight();
			 
			 PixelReader pr = imagem.getPixelReader();
			 WritableImage wi = new WritableImage(w,h);
			 PixelWriter pw = wi.getPixelWriter();
			 
			 
			 for (int i=0; i<w; i++) {
				 for (int j=0; j<h; j++) {
					 Color corA = pr.getColor(i,j);
					 double media = (corA.getRed()+corA.getGreen()+corA.getBlue())/3;
					 if (pcr != 0 || pcg != 0 || pcb != 0) {
						 media = (corA.getRed()*pcr + corA.getGreen()*pcg + corA.getBlue()*pcb)/100;
					 }
					 Color corN = new Color(media, media, media, corA.getOpacity());
					 pw.setColor(i, j, corN);
				 }
			 }
			 return wi;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		 return null;
	 }
}
