// by Sean McKenna, with outside help
// read input from a file
// must run in same directory as text file

#include <stdio.h>

// Code found on website by Cerolobo:
// www.dreamincode.net/forums/topic/44152-making-the-first-letter-of-a-string-uppercase/
char caseup(char entry){
  if(entry >= 'a' && entry <= 'z')
    entry -= 'a' - 'A';
  return entry;
}

// Turn an entire string upper-case
void allcaps(char string[]){
  int i;
  for(i = 0; i < 256; i++)
   if(string[i] >= 'a' && string[i] <= 'z')
    string[i] -= 'a' - 'A'; 
}

void main(){
  FILE *inFile;
  FILE *outFile;
  inFile = fopen("fileIO.in", "r");
  outFile = fopen("fileIO.out", "w");
  char input[256];
  char output[256];

  // Use fgets instead? gets multiple lines?
//  fscanf(inFile, "%[^\n]\n", input);
  fgets(input, 256, inFile);
  printf("Your file contains: %s", input);
  fgets(output, 256, inFile);
  fclose(inFile);

  // This is where you can change the output based on the input
//  output = input;
allcaps(output);
//  int i;
//  for(i = 0; i < 256; i++){
//    output[i] = caseup(output[i]);
//  }

  fprintf(outFile, "%s\n", output);
  fclose(outFile);
}
