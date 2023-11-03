class Circle {
  PVector pos;
  PVector vel;
  float size;
  String name;
  Circle(float xpos, float ypos) {
    size = 20;
    pos = new PVector(xpos, ypos);
    vel = new PVector(random(-3, 3), random(-3, 3));
    name = names[int(random(0, names.length))];
  }
  
  void display() {
    fill(0);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(vel.heading());
  
    // Draw the body of the bug
    ellipse(0, 0, size, size/3);
  
    // Draw the legs
    stroke(0); // Set the color of the legs
    float legLength = size / 2;
    for (int i = -2; i <= 2; i++) {
      if (i != 0) { // Skip the middle leg position
        line(i * size / 8, -size / 6, i * size / 8, -size / 6 - legLength); 
        // Top legs
        line(i * size / 8, size / 6, i * size / 8, size / 6 + legLength); 
        // Bottom legs
      }
    }
  
    popMatrix();
  
    // Draw the direction line and name as before
    line(pos.x, pos.y, pos.x + 10 * vel.x, pos.y + 10 * vel.y);
    text(name, pos.x + 5, pos.y);
  }
  
  void move() {
    if (pos.x < 0 || pos.x > width) {
      vel.x *= -1;
    }
    if (pos.y < 0 || pos.y > height) {
      vel.y *= -1;
    }
    pos.add(vel);
  }
}






// just draw legs first and then translate it there

//void setup() {
//  size(600, 600);
//  ellipse(width/2, height/2, 30, 10);
//  line(width/2, height/2, width/2 + 50, height/2);
  
//  for (int i = 5; i < 15; i+= 5) {
//    line(width/2 - i, height/2 - 25, width/2 - i, height/2 + 25);
//    line(width/2 + i, height/2 - 25, width/2 + i, height/2 + 25);
//  }
//}
