#include <unistd.h>
#include <stdio.h> //Paquete para poder utilizar printf y scanf

//This code uses execv() to achieve the same result as the execl() example, but uses an array
// of arguments instead of a list of arguments.
int main() {
    // Creates an array of pointers to arguments to pass to the "ls" command
    char *args[] = {"ls", "-la", NULL};

    // Executes the "ls" command with the "-la" argument in the current process
    execv("/bin/ls", args);

    // Alternatively, the following line will also execute "ls -la" in the current process
    execvp("ls", args);

    // This line will not be executed if either of the above execv() calls succeed
    printf("This line will not be printed if either execv() call succeeds\n");
    return 0;
}