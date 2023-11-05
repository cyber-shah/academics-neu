void setup() {
  size(600, 600);
  
  String[] stringArr = new String[3];
  ArrayList<String> stringArrayList = new ArrayList<>();
 
  // adding elements
  stringArr[0] = "Apple";
  stringArr[1] = "Banana";
  stringArr[2] = "Cherry";
  
  stringArrayList.add("Apple");
  stringArrayList.add("Banana");
  stringArrayList.add("Cherry");
  
  
  // indexing elements
  println(stringArr[0]);
  println(stringArrayList.get(0));
  
  // length
  println(stringArr.length);
  println(stringArrayList.size());
  
  // setting elements
  stringArr[0] = "Blueberry";
  stringArrayList.set(1, "Blueberry");
  
  // removing
  stringArr[1] = null;
  stringArrayList.remove(1);
  
  println(stringArrayList);
  
  
 
  stringArrayList.add(1, "Blueberry");
  println(stringArrayList);

  for(int i = 0; i < stringArrayList.size(); i ++) {
    stringArrayList.set(i, "Blueberry");
  }
  println(stringArrayList);
  
  
  
  
  
}

void draw() {

}
