// Copyright 2013 Sean McKenna
// 
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
// 
//        http://www.apache.org/licenses/LICENSE-2.0
// 
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//

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
