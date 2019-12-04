package code

import RgbBitmap.RgbBitmap
import scala.io._
import java.io._
import java.awt.Color
import code.RgbBitmap

object Pixmap {
     private case class PpmHeader(format:String, width:Int, height:Int, maxColor:Int)
 
   def load(filename:String):Option[RgbBitmap]={
      implicit val in=new BufferedInputStream(new FileInputStream(filename))
      val header=readHeader
      if(header.format=="P6")
      {
         val bm=new RgbBitmap(header.width, header.height);
         for(y <- 0 until bm.height; x <- 0 until bm.width; c=readColor)
            bm.setPixel(x, y, c)
         return Some(bm)
      }
      None
   }
 
   private def readHeader(implicit in:InputStream)={
      var format=readLine
 
      var line=readLine
      while(line.startsWith("#"))   //skip comments
         line=readLine
 
      val parts=line.split("\\s")
      val width=parts(0).toInt
      val height=parts(1).toInt
      val maxColor=readLine.toInt
 
      new PpmHeader(format, width, height, maxColor)
   }
 
   private def readColor(implicit in:InputStream)=new Color(in.read, in.read, in.read)
 
   private def readLine(implicit in:InputStream)={
      var out=""
      var b=in.read
      while(b!=0xA){out+=b.toChar; b=in.read}
      out
   }
    def save(width:Int,height:Int,QuadTree:Node, filename:String)={
      val out=new DataOutputStream(new FileOutputStream(filename))
 
      out.writeBytes("P6\u000a%d %d\u000a%d\u000a".format(width, height, 255))
 
      for(y <- 0 until height; x <- 0 until width){
         out.writeByte(QuadTree.getColor(x, y).red)
         out.writeByte(QuadTree.getColor(x, y).green)
         out.writeByte(QuadTree.getColor(x, y).blue)
      }
   }
  
}