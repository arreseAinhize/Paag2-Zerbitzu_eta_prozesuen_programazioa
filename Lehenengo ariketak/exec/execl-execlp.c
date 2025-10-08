#include <unistd.h>
#include <stdio.h> //Paquete para poder utilizar printf y scanf

int main() {
    // Executes the "ls" command with the "-la" argument in the current process
    execl("/bin/ls", "ls", "-la", NULL);

    // Alternatively, the following line will also execute "ls -la" in the current process
    // Since ls is a commonly used command, it can be found in the PATH environment variable 
    //  and doesn't require a full file path.
    execlp("ls", "ls", "-la", NULL);

    // This line will not be executed if either of the above execl() calls succeed
    printf("This line will not be printed if either execl() call succeeds\n");
    return 0;
}