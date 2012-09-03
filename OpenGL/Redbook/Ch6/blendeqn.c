#include <GL/glut.h>

// following keys affect blending equation:
// a = add, s = subtract, r, = reverse subtract
// m = go to min, x = go to max

void init(void){
  glClearColor(1.0, 1.0, 0.0, 0.0);
  
  glBlendFunc(GL_ONE, GL_ONE);
  glEnable(GL_BLEND);
}

void display(void){
  glClear(GL_COLOR_BUFFER_BIT);
  glColor3f(0.0, 0.0, 1.0);
  glRectf(-0.5, -0.5, 0.5, 0.5);
  glFlush();
}

void reshape(int w, int h){
  glViewport(0, 0, (GLsizei) w, (GLsizei) h);
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluOrtho2D(0.0, (GLdouble) w, 0.0, (GLdouble) h);
}

void keyboard(unsigned char key, int x, int y){
  switch(key){
    case 'a': case 'A':
      // colors are added as: (1, 1, 0) + (0, 0, 1) = (1, 1, 1)
      // which produces a white square on yellow background
      glBlendEquation(GL_FUNC_SUBTRACT);
      break;
    
    case 's': case'S':
      // colors are subtracted as: (0, 0, 1) - (1, 1, 0) = (-1, -1, 1)
      // which is clamped to (0, 0, 1)
      // produces blue square on a yellow background
      glBlendEquation(GL_FUNC_SUBTRACT);
      break;
    
    case 'r': case'R':
      // colors are subtracted as: (1, 1, 0) - (0, 0, 1) = (1, 1, -1)
      // clamped to (1, 1, 0)
      // produces yellow for both square and background
      glBlendEquation(GL_FUNC_REVERSE_SUBTRACT);
      break;
    
    case 'm': case 'M':
      // minimum of each component computed as
      // min(1, 0), min(1, 0), min(0, 1), aka (0, 0, 0)
      // produces black square on yellow background
      glBlendEquation(GL_MIN);
      break;
    
    case 'x': case 'X':
      // maximum of each component computed as
      // max(1, 0), max(1, 0), max(0, 1), aka (1, 1, 1)
      // produces white square on yellow background
      glBlendEquation(GL_MAX);
      break;
    
    case 27:
      exit(0);
      break;
    
    default:
      break;
  }
  
  glutPostRedisplay();
}

int main(int argc, char** argv){
  glutInit(&argc, argv);
  glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
  glutInitWindowSize(500, 500);
  glutInitWindowPosition(100, 100);
  glutCreateWindow(argv[0]);
  init();
  glutDisplayFunc(display);
  glutReshapeFunc(reshape);
  glutKeyboardFunc(keyboard);
  glutMainLoop();
  return 0;
}
