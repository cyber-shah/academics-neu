ArrayList<Circle> circles = new ArrayList();

void setup(){
  size(900, 600);
  frameRate(60);
}

void draw(){
  background(255);
  for(Circle c : circles){
    c.move();
    c.display();
  }
  
  for (int i = 0; i < circles.size(); i++) {
    println("Starting the for loop with size : " + circles.size());
     if (circles.get(i).lifeSpan < 0) {
       circles.remove(i);
       println("After removing " + circles.size());
     }
  }
}

void mousePressed(){
  Circle c = new Circle(mouseX, mouseY);
  circles.add(c);
}
