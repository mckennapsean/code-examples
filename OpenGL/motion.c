// a constantly moving rectangle

#include <stdlib.h>
#include <GL/glut.h>

int lastFrameTime = 0;
float boxX = 0.0f;

void display(void){
  if(lastFrameTime == 0)
    lastFrameTime = glutGet(GLUT_ELAPSED_TIME);
  
  int now = glutGet(GLUT_ELAPSED_TIME);
  int elapsedMilliseconds = now - lastFrameTime;
  float elapsedTime = elapsedMilliseconds / 1000.0f;
  lastFrameTime = now;
  
  int windowWidth = glutGet(GLUT_WINDOW_WIDTH);
  boxX += 512.0f * elapsedTime;
  if(boxX > windowWidth)
    boxX -= windowWidth;
  
  glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
  
  glPushMatrix();
  glTranslatef(boxX, 0.0f, 0.0f);
  
  glBegin(GL_QUADS);
  glVertex2f(  0.0f,   0.0f);
  glVertex2f(128.0f,   0.0f);
  glVertex2f(128.0f, 128.0f);
  glVertex2f(  0.0f, 128.0f);
  glEnd();
  
  glPopMatrix();
  
  glutSwapBuffers();
}

void reshape(int width, int height){
  glViewport(0, 0, width, height);
  
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluOrtho2D(0, width, 0, height);
  glMatrixMode(GL_MODELVIEW);
}

void idle(void){
  glutPostRedisplay();
}

int main(int argc, char** argv){
  glutInit(&argc, argv);
  
  glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH);
  glutInitWindowSize(640, 480);
  
  glutCreateWindow("GLUT Program");
    
  glutDisplayFunc(display);
  glutReshapeFunc(reshape);
  glutIdleFunc(idle);
  
  glutMainLoop();
  return EXIT_SUCCESS;
}
