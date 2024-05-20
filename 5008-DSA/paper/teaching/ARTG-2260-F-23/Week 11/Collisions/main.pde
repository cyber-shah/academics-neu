ArrayList<Circle> circles = new ArrayList<>();

void setup() {
  size(800, 800);
  background(0);
  colorMode(HSB, 360, 100, 100);
  for(int i = 0; i < 100; i++){
    circles.add(
      new Circle(
          width/2 + random(-30, 30),
          height/2 + random(-10, 10)
      ));
    }
}

void draw() {  
  background(0);
  for (Circle c : circles) {
    c.display();
    c.update();
  }
  
  for (int i = 0; i < circles.size() - 1; i++) {
    // for each i we check all other j circles.
    for (int j = i + 1; j < circles.size(); j++) {
      Circle c1 = circles.get(i);
      Circle c2 = circles.get(j);
      
      /* we now have a unique pair of circles c1,c2
      that won't be checked again */
      
      float dist = dist(c1.pos.x, c1.pos.y, c2.pos.x, c2.pos.y);
      if(dist < c1.radius + c2.radius){
      //they are overlapping
        float overlap = (c1.radius + c2.radius) - dist;
        PVector push = PVector.sub(c1.pos, c2.pos); //c2 to c1
        push.div(dist);
        
        push.mult(overlap/2);
        c1.pos.add(push);
        push.mult(-1);
        c2.pos.add(push);
      }
    }
  }
}


Circle getHover() {
  Circle cc = null;
  for (Circle c : circles) {
    if (dist(c.pos.x, c.pos.y, mouseX, mouseY) <= c.radius) {
      cc = c;
      c.hovered = true;
    } 
    else {
      c.hovered = false;
    }
  }
  return cc;
}


void mouseMoved(){
  getHover();
}

void mouseDragged() {
  Circle cHovered = getHover();
  if (cHovered != null) {
    cHovered.pos.x = mouseX;
    cHovered.pos.y = mouseY;
    
    cHovered.vel.x = (mouseX - pmouseX);
    cHovered.vel.y = (mouseY - pmouseY);
  }
}
