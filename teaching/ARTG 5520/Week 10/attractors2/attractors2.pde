ArrayList<Particle> particles = new ArrayList();
PVector att;

void setup(){ 
  size(800, 800, OPENGL);
  colorMode(HSB, 360, 100, 100, 100);
  att = new PVector(width/2, height/2);
  pixelDensity(2);
  background(0);
}

void draw(){
  background(0);
  
  for(int i = particles.size() - 1; i >= 0; i--){
    Particle p = particles.get(i);
    if(p.isOutOfBounds()){
      particles.remove(i);
    } else{
      p.move();
      p.display();
    }
  }
  
  /* display the attractor */
  strokeWeight(3);
  stroke(0, 100, 100);
  noFill();
  ellipse(att.x, att.y, 30, 30);
}

void mousePressed(){
  float h = random(360);
  for(int i = 0; i < 100; i++){
    Particle p = new Particle(h);
    particles.add(p);
  }
}
