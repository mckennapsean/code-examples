// a simple triangle blending colors across each of the points

#include <stdio.h>
#include "GL/glew.h"
#include "GL/freeglut.h"

void display();
void keyboard(unsigned char key, int x, int y);

int main(int argc, char* argv[]){
  glutInit(&argc, argv);
  glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA);
  glutInitWindowSize(300, 300);

  glutCreateWindow("OpenGL Template - Windows");

  GLenum glewStatus = glewInit();
  if (glewStatus != GLEW_OK) {
    printf("GLEW initialization error: %s\n", glewGetErrorString(glewStatus));
    return 1;
  }

  glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

  glutDisplayFunc(display);

  glutMainLoop();

  return 0;
}

void display(){
  glClear(GL_COLOR_BUFFER_BIT);

  gluOrtho2D(-1.0, 1.0, -1.0, 1.0);

  glBegin(GL_TRIANGLES);
  glColor3f(1.0f, 0.0f, 0.0f);
  glVertex2f(0.0f, 0.75f);
  glColor3f(0.0f, 1.0f, 0.0f);
  glVertex2f(-0.5f, -0.3f);
  glColor3f(0.0f, 0.0f, 1.0f);
  glVertex2f(0.5f, -0.3f);
  glEnd();

  glutSwapBuffers();
}
