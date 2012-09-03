/** by Michiel Kamermans
 * This code is provided to you under the Creative Commons license, under attribute/non-commercial restrictions.
 * If you wish to use this code commercially, please contact me (not just so you can use the code, but because I'm
 * also interested in knowing what the product will be). You are allowed to modify this code to suit your taste, of course,
 * but please credit me in some way so that people can find the original work and associated author, so they can
 * discuss things with me when they have questions =)
 *
 * Michiel Kamermans, 2010
 * pomax at nihongoresources dot com
 */

// =========================================================
//                      Event Driven Framework code
// =========================================================

/**
 * A UI component has the same event handlers that canvas has, except they're
 * only called (thanks to the framework code all the way at the bottom) when they
 * are triggered inside the component's bounding box.
 */
class Component {
	boolean visible = true;		// visibility
	boolean hasfocus = false;	// focus
	boolean debug = false;		// debug status
	int xoffset = 0;			// nested positioning x-offset
	int yoffset = 0;			// nested positioning y-offset
	// empty constructor
	Component() {}
	// draw method
	void draw() {}
	// set offsets
	void setOffsets(int x, int y) { xoffset=x; yoffset=y; }
	int  getXOffset() { return xoffset; }
	int  getYOffset() { return yoffset; }
	// debug
	void setDebug(boolean d) { debug = d; }
	// handle visibility
	void setVisible(boolean v) { visible = v; }
	boolean isVisible() { return visible; }
	// event handling checks: mouse
	boolean listensAt(int x, int y) { return false; }
	// event handling checks: keybaord
	boolean listensForKeyPress() { return false; }
	// event handling checks: keyboard
	boolean listensForKeyRelease() { return false; }
	// event handling super methods
	void mouseMoved(int mouseX, int mouseY) {}
	void mouseClicked(int mouseX, int mouseY) {}
	void mouseDragged(int mouseX, int mouseY) {}
	void mousePressed(int mouseX, int mouseY) {}
	void mouseReleased(int mouseX, int mouseY) {}
	void keyPressed(int key, int keyCode) {}
	void keyReleased(int key, int keyCode) {}
	// action listening from other components
	void actionPerformed(Component source, int event, String action) {}
	// focus listening
	void setFocus(boolean f) { hasfocus = f; }
	boolean hasFocus() { return hasfocus; }
	void focusReceived() {}
	void focusLost() {}
}

/**
 * Think of this as the master list of UI component
 */
class Components {
	ArrayList components;
	Component focussed = null;
	
	Components() { components = new ArrayList(); }

	void add(Component component) { components.add(component); }
	Component get(int index) { return (Component) components.get(index); }
	int size() { return components.size(); }

	// cascades a debug down
	void setDebug(boolean debug) {
		for(int c=0; c<components.size(); c++) {
			Component component = ((Component)components.get(c));
			component.setDebug(debug); }}

	// draws all components, lowest first
	void draw(){
		for(int c=0; c<components.size(); c++) {
			Component component = ((Component)components.get(c));
			if (component.isVisible()) { component.draw(); }}}
			
	// we look for the last (=top most) component that will accept this mouseMove event.
	// if it's not the currently focussed component, send the old component a focusLost(),
	// and the new component a focusReceived() event.
	boolean mouseMoved(int mouseX, int mouseY) {
		boolean cfound = false;
		for(int c=components.size()-1; c>=0; c--) {
			Component component = ((Component)components.get(c));
			if(component.listensAt(mouseX,mouseY)) {
				if(focussed!=component) {
					if(focussed!=null) { 
						focussed.setFocus(false);
						focussed.focusLost(); }
					component.setFocus(true);
					component.focusReceived();
					focussed = component; }
				else if(!focussed.hasFocus()) {
					focussed.setFocus(true);
					focussed.focusReceived(); }
				component.mouseMoved(mouseX,mouseY);
				cfound = true;
				break; }}
		if(!cfound && focussed!=null) { focussed.focusLost(); focussed=null; }
		redraw();
		return cfound; }

	// standard cascade
	void mouseClicked(int mouseX, int mouseY) {
		for(int c=components.size()-1; c>=0; c--) {
			Component component = ((Component)components.get(c));
			if(component.listensAt(mouseX,mouseY)) {
				component.mouseClicked(mouseX,mouseY); break; }} redraw(); }

	// standard cascade
	void mousePressed(int mouseX, int mouseY) {
		for(int c=components.size()-1; c>=0; c--) {
			Component component = ((Component)components.get(c));
			if(component.listensAt(mouseX,mouseY)) {
				component.mousePressed(mouseX,mouseY); break; }} redraw(); }

	// standard cascade
	void mouseDragged(int mouseX, int mouseY) {
		for(int c=components.size()-1; c>=0; c--) {
			Component component = ((Component)components.get(c));
			if(component.listensAt(mouseX,mouseY)) {
				component.mouseDragged(mouseX,mouseY); break; }} redraw(); }

	// standard cascade
	void mouseReleased(int mouseX, int mouseY) {
		for(int c=components.size()-1; c>=0; c--) {
			Component component = ((Component)components.get(c));
			if(component.listensAt(mouseX,mouseY)) {
				component.mouseReleased(mouseX,mouseY); break; }} redraw(); }

	// standard cascade
	void keyPressed(int key, int keyCode) {
		for(int c=0; c<components.size(); c++) {
			Component component = ((Component)components.get(c));
			if(component.listensForKeyPress()) {
				component.keyPressed(key, keyCode); }} redraw(); }

	// standard cascade
	void keyReleased(int key, int keyCode) {
		for(int c=0; c<components.size(); c++) {
			Component component = ((Component)components.get(c));
			if(component.listensForKeyRelease()) {
				component.keyReleased(key, keyCode); }} redraw(); }
}

/**
 * It would be tempting to make the bounding box class an extension of the class
 * we're going to define for Rect, the object that models the rect() function, but this
 * would be a problem, as Rect will have a bounding box of its own, so we would get
 * stuck in a constructor spawning loop. It can't be an extension on Drawable for that
 * reason either. The code, therefore, is virtually identical to the Rect class, but without
 * the class being an extension on any known class.
 */
class BoundingBox
{
	int x, y, X, Y;
	BoundingBox(int x, int y, int X, int Y) { this.x=x; this.y=y; this.X=X; this.Y=Y; }
	// draw
	void draw() { rectMode(CORNER); rect(x, y, X-x, Y-y); }
	// getters
	int getMinX() { return x; }
	int getMinY() { return y; }
	int getMaxX() { return X; }
	int getMaxY() { return Y; }
	int getWidth() { return X-x; }
	int getHeight() { return Y-y; }
	// setters
	void setMinX(int v) { x = v; }
	void setMinY(int v) { y = v; }
	void setMaxX(int v) { X = v; }
	void setMaxY(int v) { Y = v; }
	// movers
	void move(int dx, int dy) { x+=dx; y+=dy; X+=dx; Y+=dy; }
	// in bound check
	boolean inBoundsX(int v) { return (x<=v && v<=X); }
	boolean inBoundsY(int v) { return (y<=v && v<=Y); }
	boolean inArea(int x, int y) { return (inBoundsX(x) && inBoundsY(y)); }
}

/**
 * generic drawable component class for uniformly setting
 * stroke and fill color for any component
 */
class Drawable extends Component {
	int stroke_r, stroke_g, stroke_b, stroke_a;	// stroke color
	int fill_r, fill_g, fill_b, fill_a;				// fill color
	int x, y;						// component's location on a surface
	BoundingBox bounds = null;
	Drawable(int x, int y) {
		super();
		setStroke(0,0,0,255);
		setFill(255,255,255,255); 
		setX(x);
		setY(y);
		setupBoundingBox(); }
	// all drawable components have a bounding box
	void setupBoundingBox() {
		bounds = new BoundingBox(x,y,x,y); }
	void setOffsets(int x, int y) { 
		super.setOffsets(x,y);
		bounds.move(x,y); }
	// draw means setting colors, and if debugging, drawing the bounding box
	void draw() {
		if(debug) { 
			stroke(255,0,0,100); 
			fill(255,0,0,10); 
			bounds.draw(); }
		stroke(stroke_r,stroke_g,stroke_b,stroke_a);
		fill(fill_r,fill_g,fill_b,fill_a); }
	void setStroke(int r, int g, int b, int a) {
		stroke_r = r; stroke_g = g; stroke_b = b; stroke_a = a; }
	void setFill(int r, int g, int b, int a) {
		fill_r = r; fill_g = g; fill_b = b; fill_a = a; }
	// standard getter/setters
	int getX() { return x; }
	int getY() { return y; }
	void setX(int v) { x=v; }
	void setY(int v) { y=v; }
	BoundingBox getBoundingBox() { return bounds; }
	// moved a drawable component around on the surface
	void move(int dx, int dy) { x += dx; y += dy; bounds.move(dx,dy); }
	// superancestral methods for determining whether focus and events should propagate
	boolean listensAt(int x, int y) { return inArea(x,y); }
	boolean inArea(int x, int y) { return false; }
}

/**
 * Ellipse class, corresponding to ellipse() command in Processing (assumes CENTER mode for ellipses)
 */
class Ellipse extends Drawable
{
	int width, height;
	Ellipse(int xv, int yv, int w, int h) {
		super(xv,yv);
		width = w;
		height = h; 
		setupBoundingBox(); }
	void setupBoundingBox() {
		int hw = (width/2);
		int hh = (height/2);
		bounds = new BoundingBox(x-hw, y-hh, x+hw, y+hh); }
	// draw
	void draw() { super.draw(); ellipse(x+xoffset, y+yoffset, width, height); }
	// getters
	int getWidth() { return width; }
	int getHeight() { return height; }
	// setters
	void setWidth(int v) { width = v; }
	void setHeight(int v) { height = v; }
	// poly check (sort of) - Ellipse cartesian formula: (x-h)²/a² + (y-k)²/b² = 1
	// in this formula {h,k} = center, a = half width, and b = half height
	boolean inArea(int xv, int yv) { 
		int a = width/2;	int sqa = a*a;
		int b = height/2;	int sqb = b*b;
		int xh = x - xv;	int sqxh = xh*xh;
		int yk = y - yv;	int sqyk = yk*yk;
		return (sqxh/sqa + sqyk/sqb) <= 1; }
}


/**
 * Circle class - while draw uses the command ellipse(x,y,2r,2r), this does not
 * mean we should make Circle an extension of the Ellipse class, because
 * circles are technically very different beasts from ellipses. While the elliptical
 * function can be given parameters such that it yields the same results as
 * the circular function, it is far more efficient to just define a dedicated class
 * to circles.
 */
class Circle extends Drawable
{
	int radius;
	Circle(int xv, int yv, int r) {
		super(xv,yv);
		radius = (int)r; 
		setupBoundingBox(); }
	void setupBoundingBox() {
		bounds = new BoundingBox(x-radius, y-radius, x+radius, y+radius); }
	// draw
	void draw() { super.draw(); ellipse(x+xoffset, y+yoffset, 2*radius, 2*radius); }
	// getters
	int getRadius() { return radius; }
	// setters
	void setRadius(int v) { radius = v; }
	// poly check (sort of)
	boolean inArea(int xv, int yv) { 
		int dx = xv-x;
		int sqx = dx*dx;
		int dy = yv-y;
		int sqy = dy*dy;
		int sqr = radius*radius;
		// aways use multiplication rather than sqrt when possible. Much, much cheaper
		return sqx+sqy <= sqr; }
}


/**
 * Rect class, corresponding to rect() command in Processing
 */
class Rect extends Drawable
{
	int x2, y2;
	Rect(int x, int y, int w, int h) {
		super(x,y);
		x2 = x+w;
		y2 = y+h;
		setupBoundingBox(); }
	void setupBoundingBox() {
		bounds = new BoundingBox(x,y,x2,y2); }
	// draw
	void draw() {
		super.draw();
		rectMode(CORNER);
		rect(x+xoffset, y+yoffset, getWidth(), getHeight()); }
	// getters
	int getMinX() { return x; }
	int getMinY() { return y; }
	int getMaxX() { return x2; }
	int getMaxY() { return y2; }
	int getWidth() { return x2-x; }
	int getHeight() { return y2-y; }
	// setters
	void setMinX(int v) { setX(v); }
	void setMinY(int v) { setY(v); }
	void setMaxX(int v) { x2 = v; }
	void setMaxY(int v) { y2 = v; }
	// move override, because we have more than just x/y to move
	void move(int dx, int dy) {
		super.move(dx,dy);
		x2 += dx;
		y2 += dy; }
	// poly check (sort of)
	boolean inBoundsX(int v) { return (x<=v && v<=x2); }
	boolean inBoundsY(int v) { return (y<=v && v<=y2); }
	boolean inArea(int x, int y) { return (inBoundsX(x) && inBoundsY(y)); }
}


/**
 * Content pane, basically, akin (in spirit) to java's JPanel.
 * A Panel has its own components administration.
 */
class Panel extends Rect
{
	Components components;
	Component focussed = null;
	Panel(int x, int y, int w, int h) {
		super(x,y,w,h);
		components = new Components();
		setStroke(0,0,0,0);
		setFill(255,255,255,255); }
	// panels don't have a stroke, only a background color
	void setBackground(int r, int g, int b) { setFill(r,g,b,255); }
	void add(Component component) {
		component.setOffsets(x+xoffset,y+yoffset);
		components.add(component); }
	void setDebug(boolean debug) { components.setDebug(debug); }
	Component get(int index) { return (Component) components.get(index); }
	int size() { return components.size(); }
	void draw(){
		super.draw();
		for(int c=0; c<components.size(); c++) {
			Component component = ((Component)components.get(c));
			if (component.isVisible()) { component.draw(); }}}
	
	// movemoved events inside a canvas must be propagated to the components
	// inside the panel - note the offset corrections in the passed coordinates!
	void mouseMoved(int x, int y) {
		boolean handled = components.mouseMoved(x-(this.x+xoffset),y-(this.y+yoffset)); 
		if(handled&&hasfocus) { setFocus(false); focusLost(); }}
	
	// generic propagation - note the offset corrections in the passed coordinates!
	void mouseClicked(int x, int y) { components.mouseClicked(x-(this.x+xoffset),y-(this.y+yoffset)); }
	void mousePressed(int x, int y) { components.mousePressed(x-(this.x+xoffset),y-(this.y+yoffset)); }
	void mouseDragged(int x, int y) { components.mouseDragged(x-(this.x+xoffset),y-(this.y+yoffset)); }
	void mouseReleased(int x, int y) { components.mouseReleased(x-(this.x+xoffset),y-(this.y+yoffset)); }
	void keyPressed(int k, int kc) { components.keyPressed(k,kc); }
	void keyReleased(int k, int kc) { components.keyReleased(k,kc); }
}


/**
 * Line class, corresponding to line() command
 */
class Line extends Drawable
{
	int bounding_thickness;
	int x2, y2, xdif, ydif;

	Line(int x1, int y1, int x2, int y2) {
		super(x1,y1);
		this.x2 = x2; this.y2 = y2;
		bounding_thickness = 3;
		setupBoundingBox(); }
	
	void setupBoundingBox() { 
		bounds = new BoundingBox(min(x,x2)-bounding_thickness, 
							min(y,y2)-bounding_thickness,
							max(x,x2)+bounding_thickness,
							max(y,y2)+bounding_thickness); }

	void setStartX(int v) { setX(v); setupBoundingBox(); }
	void setStartY(int v) { setY(v); setupBoundingBox(); }
	void setEndX(int v) { x2=v; setupBoundingBox(); }
	void setEndY(int v) { y2=v; setupBoundingBox(); }
	
	void move(int dx, int dy) {
		super.move(dx,dy);
		x2+=dx;
		y2+=dy; }

	void setBoundingThickness(int t) {
		bounding_thickness = t; 
		setupBoundingBox(); }

	void draw() { super.draw(); drawLine(); }
	void drawLine() { line(x+xoffset,y+yoffset,x2+xoffset,y2+yoffset); }

	// compute the y we get for this x, and see if it's close enough to the y we *should* get.
	boolean onLine(int xv, int yv) {
		if(bounds.inArea(xv,yv)) {
			if(abs(x2-x)<=bounding_thickness) { return (min(y,y2)<=yv && yv<=max(y,y2)); }
			else if(abs(y2-y)<=bounding_thickness) { return (min(x,x2)<=yv && yv<=max(x,x2)); }
			else {
				double ydif = y2-y;
				double xdif = x2-x;
				double coeff = ydif/xdif;
				int computed_y = (int) (y + (((xv-bounding_thickness)-x)*coeff));
				int computed_y2 = (int) (y + (((xv+bounding_thickness)-x)*coeff));
				boolean online = (min(computed_y,computed_y2)<=yv && yv<=max(computed_y,computed_y2));
				return online; }}
		return false; }

	// lines strictly speaking have no area, so an inArea check is the same as an onLine check
	boolean inArea(int x, int y) { return onLine(x,y); }
}


/**
 * Bezier class, corresponding to bezier() command in Processing
 */
class Bezier extends Line
{
	int cx1, cy1, cx2, cy2;	// control coordinates

	// third order bezier (cubic)
	Bezier(int x1, int y1, int cx1, int cy1, int cx2, int cy2, int x2, int y2) {
		super(x1,y1,x2,y2);
		this.cx1 = cx1; this.cy1 = cy1; this.cx2 = cx2; this.cy2 = cy2;
		setupBezierBounds(); }

	// bounds computation for Bezier curves is not quite as straight forward as lines...
	// in fact, it's downright complex.
	void setupBezierBounds()
	{
		int x1=x;
		int y1=y;

		// I don't know why, but when we get to this point, bounds is not guaranteed to have been instantiated...
		// perhaps a processing.js bug, perhaps an inherent feature. Don't know, easy to work around.
		bounds = new BoundingBox(min(x1,x2), min(y1,y2), max(x1,x2), max(y1,y2));

		//
		//	The next bit is technical. See the comment on this topic on
		//	http://newsgroups.derkeiler.com/Archive/Comp/comp.graphics.algorithms/2005-07/msg00334.html
		//	and the worked out mathematics at http://pomax.nihongoresources.com/downloads/bezierbounds.html
		//	for an explanation of why the following code is being used, and why it works.
		//

		double dcx0 = (double) (cx1-x1);
		double dcy0 = (double) (cy1-y1);
		double dcx1 = (double) (cx2 - cx1);
		double dcy1 = (double) (cy2 - cy1);
		double dcx2 = (double) (x2 - cx2);
		double dcy2 = (double) (y2 - cy2);

		// recompute bounds projected on the x-axis, if the control points lie outside the bounding box x-bounds
		if(!bounds.inBoundsX(cx1) || !bounds.inBoundsX(cx2)) {
			double a = dcx0;
			double b = dcx1;
			double c = dcx2;

			// Do we have a problematic discriminator if we use these values?
			// If we do, because we're computing at sub-pixel level anyway, simply salt 'b' a tiny bit.
			if(a+c != 2*b) { b+=0.01; }

			double numerator = 2*(a - b);
			double denominator = 2*(a - 2*b + c);
			double doubleroot = (2*b-2*a)*(2*b-2*a) - 2*a*denominator;
			double root = sqrt((float)doubleroot);

			// there are two possible values for t that yield inflection points
			double t1 =  (numerator + root) / denominator;
			double t2 =  (numerator - root) / denominator;

			// so, which of these is the useful point? (t must lie in [0,1])
			if(0<=t1 && t1<=1) {
				double inflectionpoint = evaluateX(t1);
				if(inflectionpoint>=0) {
					if(bounds.getMinX() > inflectionpoint) { bounds.setMinX((int)inflectionpoint); }
					else if(bounds.getMaxX() < inflectionpoint) { bounds.setMaxX((int)inflectionpoint); }}}
			if(0<=t2 && t2<=1) {
				double inflectionpoint = evaluateX(t2);
				if(inflectionpoint>=0) {
					if(bounds.getMinX() > inflectionpoint) { bounds.setMinX((int)inflectionpoint); }
					else if(bounds.getMaxX() < inflectionpoint) { bounds.setMaxX((int)inflectionpoint); }}}
		}

		// recompute bounds projected on the y-axis, if the control points lie outside the bounding box y-bounds
		// no comments, because it's virtually identical code. Kept as duplicate, though, to emphasise that we have
		// to do this for the x-axis and y-axis separately.
		if(!bounds.inBoundsY(cy1) || !bounds.inBoundsY(cy2)) {
			double a = dcy0;
			double b = dcy1;
			double c = dcy2;
			if(a+c != 2*b) { b+=0.01; }
			double numerator = 2*(a - b);
			double denominator = 2*(a - 2*b + c);
			double doubleroot = (2*b-2*a)*(2*b-2*a) - 2*a*denominator;
			double root = sqrt((float)doubleroot);
			double t1 =  (numerator + root) / denominator;
			double t2 =  (numerator - root) / denominator;
			if(0<=t1 && t1<=1) {
				double inflectionpoint = evaluateY(t1);
				if(inflectionpoint>=0) {
					if(bounds.getMinY() > inflectionpoint) { bounds.setMinY((int)inflectionpoint); }
					else if(bounds.getMaxY() < inflectionpoint) { bounds.setMaxY((int)inflectionpoint); }}}
			if(0<=t2 && t2<=1) {
				double inflectionpoint = evaluateY(t2);
				if(inflectionpoint>=0) {
					if(bounds.getMinY() > inflectionpoint) { bounds.setMinY((int)inflectionpoint); }
					else if(bounds.getMaxY() < inflectionpoint) { bounds.setMaxY((int)inflectionpoint); }}}
		}
	}
	// evaluates the x-projection of the bezier curve for parameter t
	double evaluateX(double t) {
		double it = (1-t);
		return ((double)x*it*it*it + 3.0*(double)cx1*t*it*it + 3.0*(double)cx2*it*t*t + (double)x2*t*t*t); }

	// evaluates the y-projection of the bezier curve for parameter t
	double evaluateY(double t) {
		double it = (1-t);
		return ((double)y*it*it*it + 3.0*(double)cy1*t*it*it + 3.0*(double)cy2*it*t*t + (double)y2*t*t*t); }
		
	// override - instead of drawing a straight line, we draw the bezier curve
	void drawLine() { bezier(x+xoffset,y+yoffset, cx1+xoffset,cy1+yoffset, cx2+xoffset,cy2+yoffset, x2+xoffset,y2+yoffset); }

	// override on move, because not only do we have to move our start and end point, but also our
	// bezier control points.
	void move(int dx, int dy) {
		super.move(dx,dy);
		this.cx1 += dx;
		this.cy1 += dy;
		this.cx2 += dx;
		this.cy2 += dy; }

	// run through the bezier parametric curve and see if we pass the provided coordinate.
	// this check can be optimised by setting the step size for t based on what the bezier
	// parameters in the constructor are. however, for simplicity (right now), we simply use
	// step 1/100 so we'll probably always catch any mouseover
	boolean onLine(int x, int y) {
		for(double t=0.0; t<=1.0; t+=0.01) {
			int ftx = (int) evaluateX(t);
			int fty = (int) evaluateY(t);
			if(abs(ftx-x)<=bounding_thickness && abs(fty-y)<=bounding_thickness) { return true; }}
		return false; }
}

class Label extends Drawable
{
	String label = "";
	Label(String l, int x, int y) { super(x,y); label=l; }
	// draw
	void draw() {
		super.draw();
		text(label, x+xoffset, y+yoffset); }
	// getter and setter
	String getLabel() { return label; }
	void setLabel(String l) { label=l; }
}

// =========================================================
//                                     Extended components
// =========================================================

class Button extends Component
{
	public int BUTTON_PRESSED = 0;
	public int BUTTON_UNPRESSED = 1;
	private boolean pressed = false;
	ArrayList actionlisteners = new ArrayList();
	private Drawable face;
	private String action;
	public Button(Drawable face, String action) { 
		this.face=face; 
		this.action=action;
		release(); }
	void draw() { face.draw(); }
	void setDebug(boolean debug) { face.setDebug(debug); }
	void setStroke(int r, int g, int b, int a) { face.setStroke(r,g,b,a); }
	void setFill(int r, int g, int b, int a) { face.setFill(r,g,b,a); }
	void setOffsets(int x, int y) { face.setOffsets(x,y); }
	// standard getter/setters
	int getX() { return face.getX(); }
	int getY() { return face.getY(); }
	void setX(int v) { face.setX(v); }
	void setY(int v) { face.setY(v); }
	BoundingBox getBoundingBox() { return face.getBoundingBox(); }
	void move(int dx, int dy) { if(face!=null) { face.move(dx,dy); }}
	// superancestral methods for determining whether focus and events should propagate
	boolean listensAt(int x, int y) { return face.listensAt(x,y); }
	boolean inArea(int x, int y) { return face.inArea(x,y); }
	// getter
	Drawable getFace() { return face; }
	// action listeners
	void addActionListener(Component c) { actionlisteners.add(c); }
	String getActionString() { return action; }
	// coloring
	void setNormalColors() { setStroke(0,0,0,255); setFill(255,255,255,255); }
	void setMouseoverColors() { setStroke(255,0,175,255); }
	void setClickedColors() { setStroke(0,0,0,255); setFill(200,200,255,255); }
	// button has been pressed
	void press() {
		pressed = true;
		setClickedColors();
		for(int a=0; a<actionlisteners.size(); a++) {
			((Component)actionlisteners.get(a)).actionPerformed(this, BUTTON_PRESSED, action); }}
	// button has been released
	void release() {
		pressed = false;
		setNormalColors();
		for(int a=0; a<actionlisteners.size(); a++) {
			((Component)actionlisteners.get(a)).actionPerformed(this, BUTTON_UNPRESSED, action); }}
	// state check
	boolean isPressed() { return pressed; }
	// mouse handler for clicking on the button
	void mousePressed(int x, int y) { if(pressed) { release(); } else { press(); }}
	// what to do when focus is lost
	void focusLost() {
		if(pressed) { setClickedColors(); }
		else { setNormalColors(); }}
	// what to do when focus is granted
	void focusReceived() { setMouseoverColors(); }
}

// =========================================================
//                     Event Drive Framework example code
// =========================================================

class RandomColorPanel extends Panel {
	RandomColorPanel(int x, int y, int w, int h) { 
		super(x,y,w,h); 
		colorBackground();}
	void colorBackground() {
		setBackground((int)random(255),(int)random(255),(int)random(255)); }
	// when something notifies us that an action occured, change color
	void actionPerformed(Component source, int event, String action) { colorBackground(); }
}

class MainPanel extends Panel {
	MainPanel(int x, int y, int w, int h) { super(x,y,w,h); setBackground(230,230,230);}
	void focusLost() { setBackground(230,230,230); }
	void focusReceived() { setBackground(255,255,255); }
	void mousePressed(int x, int y) { 
		if(hasFocus()) { setBackground(100,200,255); }
		super.mousePressed(x,y); }
	void mouseReleased(int x, int y) { 
		if(hasFocus()) { setBackground(255,255,255); }
		super.mouseReleased(x,y); }
}

class RectButton extends Button {
	RectButton(int x, int y, int w, int h, String action) {
		super(new Rect(x,y,w,h), action); }}

class EllipseButton extends Button {
	EllipseButton(int x, int y, int r1, int r2, String action) {
		super(new Ellipse(x,y,r1,r2), action); }}

class CircleButton extends Button {
	CircleButton(int x, int y, int r, String action) {
		super(new Circle(x,y,r), action); }}

class BezierLineButton extends Button {
	BezierLineButton(int x1, int y1, int cx1, int cy1, int cx2, int cy2, int x2, int y2, String action) {
		super(new Bezier(x1,y1,cx1,cy1,cx2,cy2,x2,y2), action); }
	void setNormalColors() { setStroke(0,0,0,255); setFill(0,0,0,0); }
	void setMouseoverColors() { setStroke(255,0,175,255); setFill(0,0,0,0); }
	void setClickedColors() { setStroke(150,150,255,255); setFill(0,0,0,0); }}

// =========================================================
//             Processing part of the Event Drive Framework code
// =========================================================

/**
 * All events are routed through a single "components" instance
 */
Components components;

/**
 * Only does initialisation, then hands off all event work to the components handler
 */
void setup() {
	noLoop();
	size(600,600);
	components = new Components();
	initUI();
}

/**
 * Wrapper for component's draw()
 */
void draw() { background(255); components.draw(); }

/**
 * Wrappers for event handling
 */
void mouseMoved() { components.mouseMoved(mouseX, mouseY); }
void mouseDragged() { components.mouseDragged(mouseX, mouseY); }
void mouseClicked() { components.mouseClicked(mouseX, mouseY); }
void mousePressed() { components.mousePressed(mouseX, mouseY); }
void mouseReleased() { components.mouseReleased(mouseX, mouseY); }
void keyPressed() { components.keyPressed(key, keyCode); }
void keyReleased() { components.keyReleased(key, keyCode); }


/**
 * Framework method for building the UI
 */
void initUI()
{
	// make a 400x400 panel, placed at {100, 100}
	RandomColorPanel randomcolorpanel = new RandomColorPanel(100,550,400,25);
	MainPanel mainpanel = new MainPanel(100,100,400,400);

	int howmanyofeach=10;

	// make a couple of (almost) randomly placed 25x25 buttons, inside the panel.
	for(int i=0; i<howmanyofeach; i++) {
		RectButton button = new RectButton((int)random(0,300)+25,(int)random(0,300)+25,25,25, "rectangle");
		button.addActionListener(randomcolorpanel);
		mainpanel.add(button); }

	// make a couple of (almost) randomly placed 13px radius circle buttons, inside the panel.
	for(int i=0; i<howmanyofeach; i++) {
		CircleButton button = new CircleButton((int)random(0,300)+25,(int)random(0,300)+25,13, "circle");
		button.addActionListener(randomcolorpanel);
		mainpanel.add(button); }
	
	// make a couple of almost randomly placed randomly bulged ellipse buttons, too.
	for(int i=0; i<howmanyofeach; i++) {
		EllipseButton button = new EllipseButton((int)random(0,300)+25,(int)random(0,300)+25,(int)random(10,30), (int)random(10,30), "ellipse");
		button.addActionListener(randomcolorpanel);
		mainpanel.add(button); }
	
	// and finally, make a couple of randomly curved clickable bezier curves, in a pretty configuration
	for(int i=0; i<howmanyofeach; i++) {
		BezierLineButton button = new BezierLineButton(0,0,
											(int)random(100,400),(int)random(100,400),
											(int)random(-200,600),(int)random(-200,600),
											380,380,
											"bezier line");
		button.addActionListener(randomcolorpanel);
		mainpanel.add(button); }	
	
	// add panel to components, and we're done
	components.add(mainpanel);
	components.add(randomcolorpanel);
}
