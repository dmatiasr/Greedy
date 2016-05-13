public class Pair{
   private Integer first;//first member of pair
   private Integer second;//second member of pair
   public Pair (){

   }

   public Pair(Integer first, Integer second){
     this.first = first;
     this.second = second;
   }

   public void setFirst(Integer first){
    this.first = first;
   }

   public void setSecond(Integer second) {
     this.second = second;
   }

   public Integer getFirst() {
     return this.first;
   }

   public Integer getSecond() {
     return this.second;
   }
   public String toString(){
    return "("+first+","+second+")";
   }
}