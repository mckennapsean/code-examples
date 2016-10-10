// from the OpenGL Redbook
// outputs a simple white square on the screen, a graphical "hello world"

#include <GL/glut.h>

void display(void){
  
  // clear all pixels
  glClear(GL_COLOR_BUFFER_BIT);
  
  // draw white polygon/rectangle by its corners
  glColor3f(1.0, 1.0, 1.0);
  
  // can create many shapes, not just polygons
  // GL_POINTS, GL_LINES, GL_LINE_STRIP, GL_LINE_LOOP,
  // GL_TRIANGLES, GL_TRIANGLES_STRIP, GL_TRIANGLE_FAN,
  // GL_QUADS, GL_QUAD_STRIP, and GL_POLYGON
  glBegin(GL_POLYGON);
    glVertex3f(0.25, 0.25, 0.0);
    glVertex3f(0.75, 0.25, 0.0);
    glVertex3f(0.75, 0.75, 0.0);
    glVertex3f(0.25, 0.75, 0.0);
  glEnd();
  
  // start processing buffered OpenGL routines
  // sends network packet, forcing rendering
  // even better, synchronize tasks with glFinish();
  glFlush();
}

void init(void){
  // select clearing background color
  glClearColor(0.0, 0.0, 0.0, 0.0);
  
  // initialize viewing values
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
}

// declare initial window size, position, display mode
// single buffer, RGBA. open window with 'hello' in title
// call initialization routines, register callback function
// to display graphics, enter main loop and process events
int main(int argc, char** argv){
  
  // initalize GLUT library
  glutInit(&argc, argv);
  
  // single/double buffer and what color mode
  glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
  
  // declare version of OpenGL implementation
  // REQUIRED for OpenGL v. 3.0 or greater
  // void glutInitContextVersion(int majorVersion, int minorVersion);
  
  // declare where window goes and what size it should be 
  glutInitWindowSize(250, 250);
  glutInitWindowPosition(100, 100);
  
  // creates the window with those characterists, and a title name
  glutCreateWindow("hello");
  
  // initialize program
  init();
  
  // register callback functions to call for redrawing window
  glutDisplayFunc(display);
  
  // set all input & other misc. events
  // void glutReshapeFunc(void (*func)(int width, int height));
  // void glutKeyboardFunc(void (*func)(unsigned char key, int x, int y));
  // void glutMouseFunc(void (*func)(int button, int state, int x, int y));
  // void glutMotionFunc(void (*func)(int x, int y));
  // void glutPostRedisplay(void);
  // void glutIdleFunc(void (*func)(void));
  
  // initalize main drawing loop
  glutMainLoop();
  
  // return of an int mandatory during program completion
  return 0;
}
