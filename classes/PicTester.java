public class PicTester
{
    public static void testPictureMethods()
    {
        String fileName = "water.jpg";
        Pic photo = new Pic(fileName);
        photo.explore();
        //photo.zeroBlue();
        //photo.keepOnlyBlue();
        //photo.negate();       
        //photo.grayscale();
        //photo.sepia();        
        photo.fixUnderwater();        
        photo.explore();
    }
}