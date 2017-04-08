package unlp.rastrosoft.web.model;

import static ij.IJ.openImage;
import ij.ImagePlus;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Fits  {

          public void fitsToJpg(String source, String destination, String image){
            try {
            ImagePlus imageP = openImage(source+image);
            final File out = new File(destination+image+".jpg");
            BufferedImage imagen = imageP.getBufferedImage();
            ImageIO.write(imagen, "jpg", out);
            } catch (IOException ex) {
                Logger.getLogger(Fits.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
}


