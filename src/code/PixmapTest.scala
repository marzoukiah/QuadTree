
package code

import code.Pixmap
object PixmapTest {
   def main(args: Array[String]): Unit = {
      val img=Pixmap.load("H:/ahmed/master2/scala/project/assets/Baboon512.ppm").get
      val width = img.width
      val height = img.height
      var matrixRed = Array.ofDim[Int](width, height)
      var matrixGreen = Array.ofDim[Int](width, height)
      var matrixBlue = Array.ofDim[Int](width, height)
      for( i <- 0 until width) 
        { 
            for( j <- 0 until height) 
              { 

                  matrixRed(i)(j) = img.getPixel(i,j).getRed
                  matrixGreen(i)(j) = img.getPixel(i,j).getGreen
                  matrixBlue(i)(j) = img.getPixel(i,j).getBlue                 
              }  
        } 
 val threshold : Float = (25).toFloat

 var quadTree : Node = new Node(0,0,width,height,threshold,matrixRed,matrixGreen,matrixBlue)
 
 Pixmap.save(width, height, quadTree, "baboon.ppm")
 
   }
}