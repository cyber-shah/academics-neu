ArrayList<Circle> circles = new ArrayList<>();

for(int i = 0; i < 100; i++){
  circles.add(new Circle(width/2 + random(-30, 30),
  height/2 + random(-10, 10)));
}
