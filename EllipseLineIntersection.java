/* First you have to write out the ellipse

(1) (x/h)^2 + (y/v)^2 = 1
and the line in the format

(2) y = ax + b
First, shift over your coordinate axis so the ellipse is centered on the origin. You can do that by subtracting mid from the line. Once you have calculated the points of intersection, shift them back by adding mid.

You can calculate the linear slope from delta-y/delta-x from the starting and ending points of the line. You will have to check if the slope is vertical. If the slope is vertical, you just have to check whether or not the x-value of the line points falls in the location of the ellipse, and then easily calculate the values. Draw it out on paper and see how to calculate it.

Assume now that the slope is not vertical. Since you know y in terms of x from the line, square that and substitute into (1). Simplifying gives a quadratic equation.

(3) ((ah)^2+v^2)x^2 + (2abh^2)x + ((hb)^2-(hv)^2) = 0
Using the quadratic formula gives the two values for the x coordinates of the intersection. If there are two real values for x, there are two intersections. If there is only one real solution for x, there is one intersection. If there are no real solutions for x, there is no intersection.

Given ax^2 + bx + c = 0, x is given by

x = (1/2a)(-b +- Sqrt(b^2 - 4ac))
Let D = b^2 - 4ac

If D < 0, there are no intersections

If D = 0, there is one intersection

If D > 0, there are two intersections

Once you have calculated the values of the x intersection, substitute the values of x into (2) to get the y values.

Now, you need to make sure that these points fall within the line. To do this, just check that the x and y components of the calculated points satisfy x1 <= x <= x2 and y1 <= y <= y2 where x1 is the smallest and x2 the largest x-endpoint of the line, and y1 is the smallest and y2 the largest y-endpoint of the line.

Here is an example method that I made

*/



public static ArrayList<Point2D> getIntersection(double x1, double x2, double y1, double y2, double midX, double midY, double h, double v) {
     ArrayList<Point2D> points = new ArrayList();

     x1 -= midX;
     y1 -= midY;

     x2 -= midX;
     y2 -= midY;

     if (x1 == x2) { 
         double y = (v/h)*Math.sqrt(h*h-x1*x1);
         if (Math.min(y1, y2) <= y && y <= Math.max(y1, y2)) {
             points.add(new Point2D(x1+midX, y+midY);
         }
         if (Math.min(y1, y2) <= -y && -y <= Math.max(y1, y2)) {
             points.add(newPoint2D(x1+midX, -y+midY);
         }
     }
     else {
         double a = (y2 - y1) / (x2 - x1);
         double b = (y1 - a*x1);

         double r = a*a*h*h + v*v;
         double s = 2*a*b*h*h;
         double t = h*h*b*b - h*h*v*v;

         double d = s*s - 4*r*t;

         if (d > 0) {
             double xi1 = (-s+Math.sqrt(d))/(2*r);
             double xi2 = (-s-Math.sqrt(d))/(2*r);

             double yi1 = a*xi1+b;
             double yi2 = a*xi2+b;

             if (isPointInLine(x1, x2, y1, y2, xi1, yi1)) {
                 points.add(new Point2D.Double(xi1+midX, yi1+midY);
             }
             if (isPointInLine(x1, x2, y1, y2, xi2, yi2)) {
                 points.add(new Point2D.Double(xi2+midX, yi2+midY);
             }
         }
         else if (d == 0) {
             double xi = -s/(2*r);
             double yi = a*xi+b;

             if (isPointInLine(x1, x2, y1, y2, xi, yi)) {
                 points.add(new Point2D.Double(xi+midX, yi+midY));
             }
         }
     }

     return points;
 }

 public static boolean isPointInLine(double x1, double x2, double y1, double y2, double px, double py) {
     double xMin = Math.min(x1, x2);
     double xMax = Math.max(x1, x2);

     double yMin = Math.min(y1, y2);
     double yMax = Math.max(y1, y2);

     return (xMin <= px && px <= xMax) && (yMin <= py && py <= yMax);
 }
