#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid;
    int status;

    pid = fork();

    if (pid == 0) { // child process
        printf("Child process running...\n");
        sleep(5); // simulate some work
        printf("Child process exiting...\n");
        exit(0);
    } else if (pid > 0) { // parent process
        printf("Parent process waiting...\n");
        waitpid(pid, &status, 0); // wait for specific child process to complete
        printf("Child process finished with status %d.\n", status);
    } else { // fork error
        perror("fork");
        exit(1);
    }

    return 0;
}