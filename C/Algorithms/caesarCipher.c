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

// this is a File I/O Caesar Cipher
// takes caesarCipher.in as input by line, user-specified key
// outputs the coded messages to caesarCipher.out
// limited use of string.h for practice with C
// must execute from directory of text file

#include <stdio.h>
#include <string.h>

// grab only letters from input and separate into blocks
void parseinput(char input[], int inSize, int block, char output[]){
  int i;
  int j = 0;
  int k = 0;

  // check if we have alpha characters first
  for(i = 0; i < inSize; i++){
    if((input[i] >= 'a' && input[i] <= 'z') || (input[i] >= 'A' && input[i] <= 'Z')){

      // add spaces between the input
      if(j != 0 && (j - k) % block == 0){
        output[j] = ' ';
        j++;
        k++;
      }

      // add the input to the output
      output[j] = input[i];
      j++;
    }
  }
}

// turn an entire string upper-case
// www.dreamincode.net/forums/topic/44152-making-the-first-letter-of-a-string-uppercase/
void allcaps(char string[], int inSize){
  int i;
  for(i = 0; i < inSize; i++)
   if(string[i] >= 'a' && string[i] <= 'z')
    string[i] -= 'a' - 'A'; 
}

// main run of program
void main(){
  // initial variables for cipher
  FILE *inFile;
  FILE *outFile;
  inFile = fopen("caesarCipher.in", "r");
  outFile = fopen("caesarCipher.out", "w");
  int inSize = 256;
  char input[256] = "";
  int outSize = 310;
  char code[310] = "";
  char mode;
  int block = 5;
  int key;

  // set mode to cipher or decipher
  printf("Please type a 'D' to decrypt or leave blank for encrypt: ");
  scanf("%c", &mode);

  // get the key from user for creating cipher
  printf("Please enter the Caesar cipher key: ");
  scanf("%d", &key);
  if(mode == 'd' || mode == 'D')
    key = 26 - key;

  while(fgets(input, inSize, inFile) != NULL){
    // modify code: letters only & all caps
    parseinput(input, inSize, block, code);
    allcaps(code, inSize);

    // perform the Caesar cipher
    int i;
    for(i = 0; i < outSize; i++){
      if(code[i] >= 'A' && code[i] <= 'Z')
        code[i] = ((code[i] - 'A') + key) % 26 + 'A';
    }

    // write code to output
    fprintf(outFile, "%s\n", code);

    // reset strings for next iteration
    memset(&input[0], 0, inSize);
    memset(&code[0], 0, outSize);
  }

  // close files
  fclose(inFile);
  fclose(outFile);

  // print out success
  if(mode == 'd' || mode == 'D')
    printf("Your input is decoded in 'caesarCipher.out utilizing key %d.\n", 26-key);
  else
    printf("Your input is encoded in 'caesarCipher.out'utilizing key %d.\n", key);
}
