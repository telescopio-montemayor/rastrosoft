package unlp.rastrosoft.web.model;

import static ij.IJ.openImage;
import ij.ImagePlus;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
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
            float brightenFactor = 10f;
            RescaleOp op = new RescaleOp(brightenFactor, 0, null);
            imagen = op.filter(imagen, imagen);

            ImageIO.write(imagen, "jpg", out);
            } catch (IOException ex) {
                Logger.getLogger(Fits.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        public void fitsToJpgHightQualiy(String source, String destination, String image){
//            try {
//            ImagePlus imageP = openImage(source+image);
//            BufferedImage imagen = imageP.getBufferedImage();
//            
//            JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
//            jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//            jpegParams.setCompressionQuality(1f);
//            
//            final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
//            // specifies where the jpg image has to be written
//            writer.setOutput(new FileImageOutputStream(
//              new File(destination + "/" + image + ".jpg")));
//
//            // writes the file with given compression level 
//            // from your JPEGImageWriteParam instance
//            writer.write(null, new IIOImage(imagen, null, null), jpegParams);
//            
//            
//            } catch (IOException ex) {
//                Logger.getLogger(Fits.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
}


