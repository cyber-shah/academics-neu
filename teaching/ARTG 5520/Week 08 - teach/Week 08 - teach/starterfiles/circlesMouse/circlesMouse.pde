ArrayList<Circle> circles = new ArrayList();

void setup(){
  size(900, 600);
}


void draw(){
  background(255);
  for(Circle c : circles){
    c.move();
    c.display();
  }
  
  for(int i = circles.size() - 1; i >= 0; i--){
    Circle c = circles.get(i);
    if(c.lifespan <= 0){
      circles.remove(i);
    }
  }
}

void mouseClicked() {
  circles.add(new Circle(mouseX, mouseY, random(1,10), random(1,60)));
}
