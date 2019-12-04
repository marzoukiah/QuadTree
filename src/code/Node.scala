package code
import code.Colors
import code.RgbBitmap
class Node (x: Int,  y: Int,  width: Int,height: Int) {
  /* liste des noeuds
   * x et y
   * width height of the box
   * Node(matrix, x, y, width, height)
   * Pixel (x,y)
   * average(matrix, x , y, width, height){
   * red,blue,green= 0 calcul mel x lel x + width}
   */
 
  var color:Colors = new Colors(0,0,0)
  var nodeList:Array[Node] = null 

  

  def this(x: Int,  y: Int,  width: Int,height: Int, threshold: Float,mr:Array[Array[Int]], mg:Array[Array[Int]], mb:Array[Array[Int]])  
    { 
       
       this(x,y,width,height)
       if (detailMesure(mr, mg, mb, x, y, width, height) < threshold) {
         this.color = averageColor(mr, mg, mb, x, y, width, height)
     
       }else{
         nodeList = new Array[Node](4)
         this.nodeList(0) = new Node( x, y,width/2, height/2,threshold,mr, mg, mb)
         this.nodeList(1) = new Node( x+width/2, y,width/2, height/2,threshold,mr, mg, mb)
         this.nodeList(2) = new Node( x, y+height/2,width/2, height/2,threshold,mr, mg, mb)
         this.nodeList(3) = new Node( x+width/2, y+height/2,width/2, height/2,threshold,mr, mg, mb)
       }
    } 
  /*
   * calculate rectangular region of a Grid
   * changer les loops avec x.foreach(sum += _) 
   */
  def averageColor(mr:Array[Array[Int]], mg:Array[Array[Int]], mb:Array[Array[Int]], x:Int, y:Int, width:Int, height:Int ) : Colors = {
      var sum:Colors = new Colors(0,0,0)
      var area:Int = width * height
      for( i <- x until x+width) 
        { 
            for( j <- y until y+height) 
              { 

                  sum.red += mr(i)(j)
                  sum.green += mg(i)(j)
                  sum.blue += mb(i)(j)                 
              }  
        } 
                  sum.red /= area
                  sum.green /= area
                  sum.blue /= area
      return sum
   }
def detailMesure(mr:Array[Array[Int]], mg:Array[Array[Int]], mb:Array[Array[Int]], x:Int, y:Int, width:Int, height:Int ) : Int = {
      var avgColor:Colors = averageColor(mr, mg, mb, x, y, width, height)
      var red:Int = avgColor.red
      var green:Int = avgColor.green
      var blue:Int = avgColor.blue
      var colorSum:Int = 0
      for( i <- x until x+width) 
        { 
            for( j <- y until y+height) 
              { 

               colorSum =colorSum + (red-mr(i)(j)).abs+(green-mg(i)(j)).abs+(blue-mb(i)(j)).abs
                
              }  
        } 
            
      return colorSum/(3*width*height)
   }
def  getColor(i:Int,j:Int) : Colors = 
{
   if(this.nodeList==null){
     
     return this.color
   }else if(i<this.x+this.width/2 ){
     if(j< this.y+this.height/2){
       
       return this.nodeList(0).getColor(i, j)
     }else{
       return this.nodeList(2).getColor(i, j)
     }
     
   }else{
      if(j< this.y+this.height/2){
       return this.nodeList(1).getColor(i, j)
     }else{
       return this.nodeList(3).getColor(i, j)
     }
   }
   
}
}


