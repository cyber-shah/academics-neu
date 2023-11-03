ArrayList<Circle> circles = new ArrayList();
String[] names;

void setup() {
  size(900, 600);
  names = loadStrings("names.txt");
}
void draw() {
  background(255);
  for (Circle c : circles) {
    c.move();
    c.display();
  }
}

void mousePressed() {
  if (mouseButton == LEFT) {
    Circle c = new Circle(mouseX, mouseY);
    circles.add(c);
  } 
  else if (mouseButton == RIGHT){
    if(circles.size() > 0){
      circles.remove(circles.size() - 1);
    }
  }
}
