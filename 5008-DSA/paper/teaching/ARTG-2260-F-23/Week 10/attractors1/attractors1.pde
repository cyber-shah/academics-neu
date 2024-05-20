ArrayList<Particle> particles = new ArrayList();
PVector att;
ArrayList<PVector> attList = new ArrayList();

void setup(){ 
  size(800, 800, OPENGL);
  colorMode(HSB, 360, 100, 100, 100);
  att = new PVector(width/2, height/2);
  pixelDensity(2);
  background(0);
}

void draw(){
  background(0);
  
  for(Particle p : particles){
    p.move();
    p.display();
  }
  
  /* display the attractor */
  strokeWeight(3);
  stroke(0, 100, 100);
  noFill();
  for(PVector at : attList){
    ellipse(at.x, at.y, 10, 10);
  }
  
  
}

void mousePressed(){
  if(mouseButton == LEFT) {
    for(int i = 0; i < 100; i++){
      Particle p = new Particle();
      particles.add(p);
    }
  }
  else if (mouseButton == RIGHT) {
    att = new PVector(mouseX, mouseY);
    attList.add(att);
  }
}
