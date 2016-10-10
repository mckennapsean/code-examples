// changing case inspired by Cerolobo link below
// read input from a file
// must run in same directory as text file

#include <stdio.h>

// code for changing the case, by Cerolobo:
// http://www.dreamincode.net/forums/topic/44152-making-the-first-letter-of-a-string-uppercase/
char caseup(char entry){
  if(entry >= 'a' && entry <= 'z')
    entry -= 'a' - 'A';
  return entry;
}

// turn an entire string upper-case
void allcaps(char string[]){
  int i;
  for(i = 0; i < 256; i++)
   if(string[i] >= 'a' && string[i] <= 'z')
    string[i] -= 'a' - 'A'; 
}

// main test of the input from a file
void main(){
  FILE *inFile;
  FILE *outFile;
  inFile = fopen("fileIO.in", "r");
  outFile = fopen("fileIO.out", "w");
  char input[256];
  char output[256];
  
  // can use fscanf, or fgets
  //fscanf(inFile, "%[^\n]\n", input);
  fgets(input, 256, inFile);
  printf("Your file contains: %s", input);
  fgets(output, 256, inFile);
  fclose(inFile);
  
  // change the output as desired
  //output = input;
  allcaps(output);
  //int i;
  //for(i = 0; i < 256; i++){
  //  output[i] = caseup(output[i]);
  //}
  
  // write out the final output
  fprintf(outFile, "%s\n", output);
  fclose(outFile);
}
