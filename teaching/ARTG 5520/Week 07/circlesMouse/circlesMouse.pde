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
}  
