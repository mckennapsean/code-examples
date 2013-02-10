OpenGL - Sample Code
====================

Program List
------------

*Language*

-  **motion**.c
    - a constantly moving rectangle
-  **triangle**.c
    - a simple triangle blending colors across each of the points

*Shaders*

-  **shaders**.c
    - how to set up a shader in OpenGL (C)
    - includes sample shaders: base, gouraud, phong, texture-g, texture-p, & toon

*System*

-  **gui**.c
    - a GUI using OpenGL with GLUT and GLUI

*Tutorials*

-  **double**.c
    - simple square with mouse interactions
-  **fog**.c
    - generates a steadily increasing fog in a scene
-  **hello**.c
    - outputs a simple white square on the screen, a graphical "hello world"
-  **material**.c
    - generates many spheres with a variety of different materials
-  **moveLight**.c
    - a moving light around a torus
-  **planet**.c
    - creates a simple 3D solar system, with keyboard interactions

Compiling & Running Code
------------------------

all programs use C, OpenGL, GLUT, and GLUI

for Linux, you can use the provided bash script
>  e.g. for program: **triangle.c**

> >  cd **Language/**

> >  ./../run.sh **triangle**

for Windows (using Cygwin), you can use the following commands
>  gcc -o FILE.exe FILE.c -lgl -lglu -lglut -lopengl32
>  startx
>    *(gives you xterm window)*
>  ./FILE.exe


for Mac, you can use the following commands
>  gcc -o FILE FILE.c -framework GLUT -framework OpenGL
>  ./FILE

Credit
------

OpenGL Redbook for inspiring all of the *Tutorials/*
