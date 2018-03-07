//
// Created by aleccoder on 2/22/18.
//

#ifndef CIS3360_FILEHANDLE_H
#define CIS3360_FILEHANDLE_H

#endif //CIS3360_FILEHANDLE_H

#include "def.h"
#include <stdio.h>
#include <string.h>

char * givemestring(char * file){

    FILE *fp;

    char text[MAXSIZE];
    //char* plainfile = "../plainText1.txt"; //staticly defines file name for plain text
    // take command line arguments for name of plain text file
    fp = fopen(file, "r");
    if (fp == NULL) {
        printf("Could not open file %s", file);
        return strdup("");
    }
    // opens and catches for bad files
    // puts("the plaintext input is");
    //   while (fgets(plaintext, MAXSIZE, fp) != NULL) {
    //   printf("%s", plaintext);

    // break; //fixes weird data corruption issue
// }
    int i=0;
    char x;
    while  ( ( x = fgetc( fp ) ) != EOF )
    {
        //printf( "%c", x );
        text[i]=x;
        i++;
    }
    text[i]=0;
    // adds null charecter
//printf("%s",text);
    return strdup(text);
}

char * givemecleanstring(char * file){

	FILE *fp;

	char text[MAXSIZE];
	//char* plainfile = "../plainText1.txt"; //staticly defines file name for plain text
	// take command line arguments for name of plain text file
	fp = fopen(file, "r");
	if (fp == NULL) {
		printf("Could not open file %s", file);
		return strdup("");
	}
	// opens and catches for bad files
	// puts("the plaintext input is");
	//   while (fgets(plaintext, MAXSIZE, fp) != NULL) {
	//   printf("%s", plaintext);//printf( "%c", x );

	// break; //fixes weird data corruption issue
// }
	int i=0;
	char x;
	while  ( ( x = fgetc( fp ) ) != EOF )
	{
		if((isalpha(x)==1)) {
			text[i] = x;
			i++;
		}
	}
	text[i]=0;
	// adds null charecter
//printf("%s",text);
	fclose(file);
	return strdup(text);
}
