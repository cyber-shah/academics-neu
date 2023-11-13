ArrayList<Circle> circles = new ArrayList();

void setup(){
  size(900, 600);
  frameRate(30);
  colorMode(HSB, 360, 100, 100, 360);
}

void draw(){
  background(0);
  for(Circle c : circles){
    c.move();
    c.display();
  }
   
  for (int i = 0; i < circles.size(); i++) {
     if (circles.get(i).lifeSpan < 0) {
       circles.remove(i);
     }
  }
}

void mousePressed(){
  float hue;
  if (random(1, 6) < 1) {
    hue = random(0, 60);
  } else if (random(1, 6) < 2) {
    hue = random(60, 120);
  } else if (random(1, 6) < 3) {
    hue = random(120, 180);
  } else if (random(1, 6) < 4) {
    hue = random(180, 240);
  } else if (random(1, 6) < 5) {
    hue = random(240, 300);
  } else {
    hue = random(300, 360); 
  }
  float number = random(100, 200);
  for (int i = 0; i < number; i++) {
    Circle c = new Circle(mouseX, mouseY, hue);
    circles.add(c);
  }
}
