package assignment2;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;

public class FlipImage {

   private BufferedImage image;

   public BufferedImage getImage(){
      return this.image;
   }

   public FlipImage(BufferedImage img){
      int width  = img.getWidth();
      int height = img.getHeight();
      this.image = new BufferedImage(width, height, /* BufferedImage.TYPE_INT_RGB); */ BufferedImage.TYPE_BYTE_GRAY);

      WritableRaster inraster  = img.getRaster();
      WritableRaster outraster = this.image.getRaster();

      for (int i=0; i<width; i++)
          for (int j=0; j<height; j++) {
              int value = inraster.getSample(i,j,0);
              outraster.setSample(i,j,0, 255-value );
          }
   }
   
   public static void main(String[] args) {
       try {
           String file        = args[0];
           BufferedImage img  = ImageIO.read(new File(file));
           FlipImage flip     = new FlipImage(img);
           ImageIO.write(flip.getImage(), "PNG", new File("flip_"+file+".png"));
       } catch (Exception e) {
           System.out.println("Failed processing!\nUsage: java FlipImage 'image_file'\n\n"+e.toString());
       }

   }
}


