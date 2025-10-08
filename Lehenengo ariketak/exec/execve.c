#include <unistd.h>
#include <stdio.h> //Paquete para poder utilizar printf y scanf

int main() {
    // Creates an array of pointers to arguments to pass to the "ls" command
    char *args[] = {"ls", "-la", NULL};

    // Creates an array of pointers to environment variables to pass to the "ls" command
    char *env[] = {"MY_VAR=123", "ANOTHER_VAR=abc", NULL};

    // Executes the "ls" command in the current process with the specified environment variables
    execve("/bin/ls", args, env);

    // This line will not be executed if the above execve() call succeeds
    printf("This line will not be printed if the execve() call succeeds\n");
    return 0;
}