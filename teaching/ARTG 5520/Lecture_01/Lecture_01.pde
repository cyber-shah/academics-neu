size(800, 400);

////// //EXERCISE 1 ------------------------------------
//int rect_size = 100;
//int half = rect_size / 2;
//int gap = 30;
//int y = (height/2) - half;
//int x = (width/2) - half;

//int spacer = gap + rect_size;

////rect(x - spacer, y, rect_size, rect_size);
////rect(x, y, rect_size, rect_size);
////rect(x + spacer, y, rect_size, rect_size);
//rectMode(CENTER);
//rect((width /2), 200, 100, 100);
//rect((width /2) - 130, 200, 100, 100);
//rect((width /2) + 130, 200, 100, 100);



// EXERCISE 2 ------------------------------------
//ellipse(width/2, height/2, 150, 150);
//ellipse(width/2, height/2, 100, 100);
//ellipse(width/2, height/2, 50, 50);

//quad(10, 10, 40, 30, 100, 100, 50,0);


//int s = 100;
//int cx = width/2 - 3*s;
//int cy = height/2;
//strokeWeight(50);

//strokeJoin(BEVEL);
//quad(cx, cy - s, cx - s, cy, cx, cy + s, cx + s, cy);
//cx = width/2;
//strokeJoin(MITER);
//quad(cx, cy - s, cx - s, cy, cx, cy + s, cx + s, cy);
//cx = width/2 + 3*s;
//strokeJoin(ROUND);
//quad(cx, cy - s, cx - s, cy, cx, cy + s, cx + s, cy);

//quad(0, height/2, 
//      width/2, 0, 
//      width, height/2, 
//      width/2, height);

 //LAST EXERCISE --------------------------------
background(255);
int x = width/2;
int rect_width = 50;

// 1st = rectangles
fill(200); stroke(200);
rect(x - (rect_width), 0, rect_width, height);
rect(x + (rect_width/4), 0, rect_width, height);

// circles
fill(60);
ellipse(width/4, height, height, height);
arc((width/4) * 3, height/2, height, height, 0, PI);

// 3rd = rectangle
fill(0); stroke(0);
rect(0, height - rect_width, width, rect_width);
