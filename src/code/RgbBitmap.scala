package code

import java.awt.image.BufferedImage
import java.awt.Color
 
object RgbBitmap extends App {
  class RgbBitmap(val dim: (Int, Int)) {
    def width = dim._1
    def height = dim._2
 
    private val image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR)
 
    def apply(x: Int, y: Int) = new Color(image.getRGB(x, y))
 
    def update(x: Int, y: Int, c: Color) = image.setRGB(x, y, c.getRGB)
    
    def getPixel(x: Int, y: Int) = this(x, y)
    
    def setPixel(x: Int, y: Int, c: Color) = this(x, y) = c
 
    def fill(c: Color) = {
      val g = image.getGraphics
      g.setColor(c)
      g.fillRect(0, 0, width, height)
    }
  }
 
  object RgbBitmap {
    def apply(width: Int, height: Int) = new RgbBitmap(width, height)
  }
 
 
}