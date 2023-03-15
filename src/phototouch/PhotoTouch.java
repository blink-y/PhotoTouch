package phototouch;

import imagefileio.ImageFileIO;
import imagefileio.PGM;

/**
 * PhotoTouch, a continuation of ImageFileIO
 * This is the main class.
 * DO NOT MODIFY THE GIVEN main() METHOD!
 * 
 * @author pffung
 *
 * CSCI1130 Java Assignment 5 PhotoTouch
 *
 * Remark: Name your class, variables, methods, etc. properly. 
 * You should write comment for your work and follow good styles.
 * I declare that the assignment here submitted is original except for source
 * material explicitly acknowledged, and that the same or closely related
 * material has not been previously submitted for another course. I also
 * acknowledge that I am aware of University policy and regulations on honesty
 * in academic work, and of the disciplinary guidelines and procedures
 * applicable to breaches of such policy and regulations, as contained in the
 * website.
 *
 * University Guideline on Academic Honesty:
 *     http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelines to Academic Honesty:
 *     https://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 *
 * Section     : CSCI1130 A
 * Student Name: Mitul Mohammad Abdullah Al Mukit
 * Student ID  : 1155165211
 * Date        : 25/11/2022
 */
public class PhotoTouch {
    
    /**
     * PhotoTouch main method
     * @param args is an array of command line arguments
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {
        /** DO NOT MODIFY THE GIVEN main() METHOD! */
        
        /**
         * Before you FINISH another class PPM.java PROPERLY,
         * there may be errors and warnings in main() and other methods.
         */
        
        // Michael's method in the given ImageFileIO.jar that checks if you added the library correctly
        try {
            // if you have added the library correctly, the following message will generate an Exception
            // YES, Michael uses an Exception to indicate that you have done something CORRECTLY!
            ImageFileIO.main(null);
        }
        catch (Exception exceptionObject) {
            // further check to see if something we expect has happened
            if (exceptionObject.getMessage().contains("Bingo!"))
                // congratulations!
                System.out.println(exceptionObject.getMessage());
            else
                // oops, something unexpected happened; show you the guidelines
                
                // if you encounter compilation error on this line,
                // it means you HAVE NOT added the correct, given ImageFileIO.jar as Library
                ImageFileIO.assignment5Guidelines();
        }
        
        System.out.println("*** Test of PPM read/ write ***");
        PPM colorPPM = new PPM();
        colorPPM.read("RGB_4by2.ppm");
        colorPPM.screenShow();
        colorPPM.write("RGB_4by2_copy.ppm");
        System.out.println();
        
        System.out.println("*** Demonstrate use of convertToPGM() ***");
        PPM colorDongDong = new PPM();
        colorDongDong.read("DongDong.ppm");
        // show only first one line of pixels on screen
        colorDongDong.screenShow(1);

        PGM grayDongDong = colorDongDong.convertToPGM();
        grayDongDong.write("DongDong.pgm");
        System.out.println();

        System.out.println("*** Blur ***");
        PPM blurDongDong = colorDongDong.blur();
        blurDongDong.write("DongDongBlur.ppm");
        blurDongDong.convertToPGM().write("DongDongDong.pgm");

        /** DO NOT MODIFY THE GIVEN main() METHOD! */
    }    
}
