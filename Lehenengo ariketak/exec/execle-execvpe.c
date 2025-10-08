#define _GNU_SOURCE
#include <unistd.h>
#include <stdio.h> //Paquete para poder utilizar printf y scanf

int main() {
    // Creates an array of pointers to arguments to pass to the "env" command
    char *args[] = {"env", NULL};

    // Creates an array of pointers to environment variables to pass to the "env" command
    char *env[] = {"MY_VAR=123", "ANOTHER_VAR=abc", NULL};

    // Executes the "env" command in the current process with the specified environment variables
    execle("/usr/bin/env", "env", NULL, env);

    // Alternatively, the following line will also execute "env" with the specified environment variables
    execvpe("env", args, env);

    // This line will not be executed if either of the above execle() calls succeed
    printf("This line will not be printed if either execle() call succeeds\n");
    return 0;
}