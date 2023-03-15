package phototouch;

import imagefileio.PGM;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class PPM of app PhotoTouch.
 * 
 *** STUDENTS SHOULD FINISH THIS CLASS PROPERLY TO MAKE PhotoTouch WORK!
 * 
 * CSCI1130 Java Assignment 5 PhotoTouch
 *
 * Remark: Name your class, variables, methods, etc. properly. 
 * You should write comment for your work and follow good styles.
 *
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
 *
 * Reference: https://en.wikipedia.org/wiki/Netpbm#PPM_example
 * Assumption: this assignment DOES NOT handle #comment in PPM image files
 * Tool: https://kylepaulsen.com/stuff/NetpbmViewer/
 */
public class PPM {
    
    /** all instance field are private */
    private int[][][] pixels;
    private int width, height, maxValue;
    
    /** given public class constants */
    public static final int RED = 0, GREEN = 1, BLUE = 2, COLORS = 3;

    /**
     * Default constructor takes no parameters.
     * Initializes this new object with width 0, height 0, maxValue 255
     * and pixels 3D array of dimensions 0 x 0 x [3-COLORS-RGB]
     */
    public PPM()
    {
        width = 0;
        height = 0;
        maxValue = 255;
        // row-major order 3D array: [row][column][color]
        pixels = new int[height][width][COLORS];
    }

    /**
     * Read from a PPM File using Scanner.
     * 1. Create a new File object and then a new Scanner object
     * 2. Read first token using Scanner object's next() method to get header
     * 3. If header is not equal to "P3", throw new Exception("Invalid header");
     * 4. Read following three integers as width, height and maxValue
     * 5. Read many pixel value lines that contains RGB-triples, delimited by white spaces.
     *    Store them in the 3D array pixels.
     * 6. Close the Scanner object
     * @param filename is an existing ASCII file containing an image in PPM format
     * @throws Exception 
     */
    public void read(String filename) throws Exception
    {
        String header = "";

        /*** Students' work here ***/
       
       File file = new File(filename);
       
       Scanner scan;
       scan = new Scanner(file);
      
       header = scan.next();
        if (header.equals("P3")== false){
            throw new Exception("Invalid header");
                }
       this.width = scan.nextInt();
       this.height = scan.nextInt();
       this.maxValue = scan.nextInt();
       
       this.pixels = new int[height][width][COLORS];
        
       for (int i=0; i < height; i++){
           for (int j=0; j < width; j++){
               for (int rgb = RED; rgb <= BLUE; rgb++){
                   this.pixels[i][j][rgb] = scan.nextInt();
               }
           }
       }
       
       scan.close();
       
        // demo code for students' reference
        //for (int rgb = RED; rgb <= BLUE; rgb++);

        System.out.println("Read " + filename + " with header " + header + " of image size " + width + "x" + height);
    }

    /**
     * Write to a PPM file using PrintStream.
     * 1. Declare and initialize a String header with content "P3"
     * 2. Create a PrintStream object with given filename
     * 3. Write header, width, height, maxValue through the PrintStream object
     * 4. Write the lines of R G B pixel values with TAB delimiter
     * 5. Close the PrintStream object
     * @param filename is a file to be created/ overwritten in working folder
     * @throws Exception 
     */
    public void write(String filename) throws Exception
    {
        /*** Students' work here ***/

        String header = "P3";
        
        PrintStream prnt = new PrintStream(filename);
        
        prnt.println(header);
        prnt.print(width);
        prnt.print("\t");
        prnt.println(height);
        prnt.println(maxValue);
        
        for (int i=0; i < height; i++){
            for (int j=0; j < width; j++){
                for (int rgb = RED; rgb <= BLUE; rgb++){
                    prnt.print(pixels[i][j][rgb]); //// Fai explain
                    prnt.print("\t");
                }
            }
        }
        prnt.close();

        
        System.out.println("Wrote " + filename + " with header " + header + " of image size " + width + "x" + height);
    }
    
    /**
     * getWidth() method
     * @return width is an int
     */
    public int getWidth()
    {
        return width;
    }
    
    /**
     * getHeight() method
     * @return height is an int
     */
    public int getHeight()
    {
        return height;
    }
    
    /**
     * getPixels() method
     * @return pixels is a 3D array of int
     */
    public int[][][] getPixels()
    {
        return pixels;
    }
    
    /**
     * Convert this PPM image to grayscale and return a new resultant PGM image.
     * Resultant image size is the same as this PPM image.
     * Refer to the technique demonstrated in given method screenShow() for 3D array processing.
     * For each PPM pixel, there are 3 color components (R, G, B).
     * Calculate the average value (R + G + B) / 3, take the quotient (round down).
     * Store the single grayscale value in a PGM pixels String via StringBuilder.
     * Separate the resultant grayscale pixel values by tabs and newlines.
     * @return a new PGM that represents the converted image of the source
     */
    public PGM convertToPGM()
    {
        PGM grayImg = new PGM();

        /*** Students' work here ***/
       StringBuilder sBuilder = new StringBuilder();

       int sumOfPixels = 0, pgmPixels = 0;
       String grayScaleValue;
       for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
           for (int rgb = RED; rgb <= BLUE; rgb++) {
             sumOfPixels += this.pixels[i][j][rgb];
           }
           pgmPixels = (int) Math.floor(sumOfPixels / 3);
           grayScaleValue = pgmPixels + "\t";
           sBuilder.append(grayScaleValue);
           sumOfPixels = 0;
         }
         sBuilder.append("\n");

       }

       String pgmStr = String.valueOf(sBuilder);
       grayImg.setPixels(this.width, this.height, pgmStr);
       return grayImg;
    }
    
    /**
     * Blur this image using a 5x5 filter and return a new resultant PPM image.
     * For each PPM pixel and each RGB color channel, find the average value of a 5x5 neighborhood.
     * E.g., consider the pixel at row [4], column [6], color GREEN [1] component:
     * 
     *   [2][4][1]  [2][5][1]  [2][6][1]  [2][7][1]  [2][8][1]
     * 
     *   [3][4][1]  [3][5][1]  [3][6][1]  [3][7][1]  [3][8][1]
     *                        +++++++++++
     *   [4][4][1]  [4][5][1] +[4][6][1]+ [4][7][1]  [4][8][1]
     *                        +++++++++++
     *   [5][4][1]  [5][5][1]  [5][6][1]  [5][7][1]  [5][8][1]
     * 
     *   [6][4][1]  [6][5][1]  [6][6][1]  [6][7][1]  [6][8][1]
     * 
     *   Calculate the average of these 25 values, taken the quotient (round down).
     *   Store the result to a new PPM image, at pixel-color location [4][6][1].
     * 
     * Perform this operation for ALL pixels and ALL 3 RGB components.
     * 
     * Boundary pixels (at the four sides and corners) of the original image shall not
     * have such a 5x5 neighborhood well-defined due to out of boundary.  
     * In such case, take the original pixel and color value.
     * 
     * @return a new PPM that represents the blurred image of the source
     */
    public PPM blur()
    {
        final int FILTER_SIZE = 5;
        
        PPM result = new PPM();
        result.width = width;

        /*** Students' work here ***/
        result.height = height;
        result.maxValue = maxValue;
        result.pixels = pixels;
        
        int sumOfPixels = 0, resultPixels =0;
        for (int i =2; i <= height-3; i++){
            for(int j = 2; j <= width-3; j++){
                for(int rgb = RED; rgb <= BLUE; rgb++){
                    for(int filter_i = i-2; filter_i <= i+2; filter_i++){
                        for(int filter_j =j-2; filter_j <= j+2; filter_j++){
                            sumOfPixels += pixels[filter_i][filter_j][rgb];
                            
                        }
                        resultPixels = (int) Math.floor(sumOfPixels /Math.pow(FILTER_SIZE,2));
                        result.pixels[i][j][rgb] = resultPixels;
                        sumOfPixels = 0;
                        
                    }
                }
            }
        }

          
        // debugging aid
        // show only first few lines of pixels on screen
        //result.screenShow(3);
        
        return result;
    }
    
    /**
     * Display this PPM as pixel (R, G, B) values on screen for inspection and debugging.
     * Demonstrate how to access pixel values and to perform screen formatting.
     * GIVEN, DO NOT MODIFY!
     * @param headNumberOfLines in (0, height) limits number of pixel lines to display
     */
    public void screenShow(int headNumberOfLines)
    {
        int limitedHeight = height;
        if (headNumberOfLines > 0 && headNumberOfLines < height)
            limitedHeight = headNumberOfLines;

        System.out.println("Screen show (head " + limitedHeight + " lines) of image size " + width + "x" + height + ":");
        for (int h = 0; h < limitedHeight; h++) {
            for (int w = 0; w < width; w++) {
                System.out.printf("(%4d", pixels[h][w][RED]);
                System.out.printf(",%4d", pixels[h][w][GREEN]);
                System.out.printf(",%4d) ", pixels[h][w][BLUE]);
            }
            System.out.println();
        }
    }

    /**
     * Display this PPM as pixel (R, G, B) values on screen for inspection and debugging.
     * Shows all pixel lines: pours to System.out and expected to be slow for large PPM.
     * GIVEN, DO NOT MODIFY!
     */
    public void screenShow()
    {
        screenShow(height);
    }
}
