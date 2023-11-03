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
}

void mousePressed(){
  Circle c = new Circle(mouseX, mouseY);
  circles.add(c);
}
