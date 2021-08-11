import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Pic extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Pic ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Pic(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Pic(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Pic(Pic copyPic)
  {
    // let the parent class do the copy
    super(copyPic);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Pic(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for(int r = 0; r < pixels.length; r++)
    {
        for(int c = 0; c < pixels[0].length; c++)
        {
            pixels[r][c].setBlue(0);
        }
    }
  }
  
  /**Method to set the red and green values to 0 */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for(int r = 0; r < pixels.length; r++)
    {
        for(int c = 0; c < pixels[0].length; c++)
        {
            pixels[r][c].setGreen(0);
            pixels[r][c].setRed(0);
        }
    }
  }
  
  /** Method to negate all of the pixels in a photo */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    int red = pixels[0][0].getRed();
    int blue = pixels[0][0].getBlue();
    int green = pixels[0][0].getGreen();
    for(int r = 0; r < pixels.length; r++)
    {
        for(int c = 0; c < pixels[0].length; c++)
        {
            red = pixels[r][c].getRed();
            blue = pixels[r][c].getBlue();
            green = pixels[r][c].getGreen();
            
            red = 255 - red;
            blue = 255 - blue;
            green = 255 - green;
            
            pixels[r][c].setRed(red);
            pixels[r][c].setGreen(green);
            pixels[r][c].setBlue(blue);
        }
    }
  }
  
  /** Method to set all of the pixels in a photo to a grayscale using an arithmetic mean*/
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    int red = 0;
    int blue = 0;
    int green = 0;
    int average = 0;
    for(int r = 0; r < pixels.length; r++)
    {
        for(int c = 0; c < pixels[0].length; c++)
        {
            red = pixels[r][c].getRed();
            blue = pixels[r][c].getBlue();
            green = pixels[r][c].getGreen();
            
            average = (red + blue + green) / 2;
            
            pixels[r][c].setRed(average);
            pixels[r][c].setGreen(average);
            pixels[r][c].setBlue(average);
        }
    }
  }
  
  
  
  /** Method to set apply a sepia tone to a picture */
  public void sepia()
  {
      /*to be implemented by you*/
  }
  
  /** Method to enhance an underwater photo */
  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();
      for(int r = 0; r < pixels.length; r++)
    {
        for(int c = 0; c < pixels[0].length; c++)
        {
            if(pixels[r][c].getRed() < 22 && pixels[r][c].getGreen() > 136 && pixels[r][c].getBlue() > 140)
            {
                int red = (int) (pixels[r][c].getRed() * 1.5);
                pixels[r][c].setRed(red);
                
                //int green = (int) (pixels[r][c].getGreen() * 0.7);
                //pixels[r][c].setGreen(green);
                
                //int blue = (int) (pixels[r][c].getBlue() * 0.7);
                //pixels[r][c].setBlue(blue);
                
            }
            pixels[r][c].setGreen((int) (pixels[r][c].getGreen() * 1.2));
            pixels[r][c].setBlue((int) (pixels[r][c].getBlue() * 1.2));
        }
    }
  }

  /** Method for a more sophisticated grayscale */
  public void improvedGrayscale()
  {
      Pixel[][] pixels = getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[0].length; col++)
          {
              Pixel currentPixel = pixels[row][col];
              int r = (int) (currentPixel.getRed() * 0.299);
              int g = (int) (currentPixel.getGreen() * .587);
              int b = (int) (currentPixel.getBlue() * .114);
              int avg = r + g + b;
              currentPixel.setRed(avg);
              currentPixel.setGreen(avg);
              currentPixel.setBlue(avg);
          }
      }
  }
  
  private final double colorScaleFactor = 1.75; 
  /** Method to add a little bit of red to a photo */
  public void addRed()
  {
      Pixel[][] pixels = getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[0].length; col++)
          {
              Pixel currentPixel = pixels[row][col];
              currentPixel.setRed((int) (currentPixel.getRed() * colorScaleFactor));
          }
      }
  }
  
  /** Method to add a little bit of green to a photo */
  public void addGreen()
  {
      Pixel[][] pixels = getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[0].length; col++)
          {
              Pixel currentPixel = pixels[row][col];
              currentPixel.setGreen((int) (currentPixel.getGreen() * colorScaleFactor));
          }
      }
  }
  
  /** Method to add a little bit of green to a photo */
  public void addBlue()
  {
      Pixel[][] pixels = getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[0].length; col++)
          {
              Pixel currentPixel = pixels[row][col];
              currentPixel.setBlue((int) (currentPixel.getBlue() * colorScaleFactor));
          }
      }
  }
  
  /** Method to double every alpha value in half of a photo */
  public void doubleAlpha()
  {
      Pixel[][] pixels = getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[0].length; col++)
          {
              Pixel currentPixel = pixels[row][col];
              int currentAlpha = currentPixel.getAlpha();
              currentPixel.setAlpha(Math.min(currentAlpha * 2, 255));
          }
      }
  }
  
  /**Method that mirrors the picture around a vertical mirror line at the center of the picture. */
  public void mirrorVerticalFrame()
  {
      Pixel[][] pixels = getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[0].length / 2; col++)
          {
              //grab the pixel that is to be copied
              Pixel pix = pixels[row][col];
              Color color = pix.getColor();
              //replace the pixel across the mirror line
              pixels[row][pixels[0].length - 1 - col].setColor(color);
          }
      }
  }
  
  /** Method that mirrors a picture around a horizontal midline from top to bottom */
  public void mirrorHorizontal()
  {
      Pixel[][] pixels = getPixels2D();
      //write an appropriate nested for loop. pay attention to the order in which you should traverse through your photo
      
              //grab the top pixel that is to be copied
              
              //grab the top pixel's color
              
              //grab that pixel that is to be replaced
              
              //replace the pixel's color across the horizontal line 
      
  }
  
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror the seagull found in "seagull.jpg" to create a second seagull on the beach */
  public void mirrorSeagull()
  {
      Pixel[][] pixels = this.getPixels2D();
      int mirrorLine = 353;
      for (int row = 234; row <= 322; row++)
      {
          for (int col = 237; col <= 345; col++)
          {
              //grab the current pixel and color to copy
              Pixel pixToCopy = pixels[row][col];
              Color colorToCopy = pixToCopy.getColor();
              //identify the distance from the mirror line of the pixel that will be replaced
              int dist = mirrorLine - col;
              //grab the pixel to be replaced
              Pixel pixToReplace = pixels[row][mirrorLine + dist];
              pixToReplace.setColor(colorToCopy);
          }
      }
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Pic fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Pic flower1 = new Pic("flower1.jpg");
    Pic flower2 = new Pic("unicorn.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Pic flowerNoBlue = new Pic(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  /** Method that applies a 3-tier RGB tint to a photo */
  public void flagPhoto()
  {
      Pixel[][] pixels = this.getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          //set to all red
          for (int col = 0; col < pixels[0].length / 3; col++)
          {
              Pixel pix = pixels[row][col];
              pix.setGreen(0);
              pix.setBlue(0);
          }
          //set to all white
          for (int col = pixels[0].length / 3; col < pixels[0].length / 3 * 2; col++)
          {
              Pixel pix = pixels[row][col];
              pix.setRed(Math.min(pix.getRed() * 3, 255));
              pix.setGreen(Math.min(pix.getGreen() * 3, 255));
              pix.setBlue(Math.min(pix.getBlue() * 3, 255));
          }
          //set to all blue
          for (int col = pixels[0].length / 3 * 2; col < pixels[0].length; col++)
          {
              Pixel pix = pixels[row][col];
              pix.setRed(0);
              pix.setGreen(0);
          }
      }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    // default file is beach.jpg
    // using a string file name will replace beach
    // Example BlueJ input field should be {"dexter"}
    
    String fileName = "water.jpg";
    if(args.length>0 && args[0]!=null) fileName = args[0]+".jpg";
    Pic picture = new Pic(fileName);
    picture.explore();
    
    //WHEN YOU'RE READY TO TEST YOUR METHODS, UNCOMMENT THE TWO LINES BELOW. 
    //picture.zeroBlue();
    //picture.explore();

    // ANOTHER METHOD TO TINKER WITH IF YOU LIKE
    // picture.edgeDetection(8);  // range of 2 to 12 is typical    
    //picture.explore();
  }

  // put all new methods above, starting at line 88
  
} // this } is the end of class Pic