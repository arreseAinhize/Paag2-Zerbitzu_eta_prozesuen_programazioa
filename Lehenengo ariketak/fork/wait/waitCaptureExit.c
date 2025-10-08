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
        printf("Child process exiting with status 42...\n");
        exit(42);
    } else if (pid > 0) { // parent process
        printf("Parent process waiting...\n");
        wait(&status); // wait for child process to complete and capture exit status
        if (WIFEXITED(status)) { // check if child process exited normally
            int exit_status = WEXITSTATUS(status); // extract exit code
            printf("Child process finished with status %d.\n", exit_status);
        } else if (WIFSIGNALED(status)) { // check if child process was terminated by a signal
            int signal_num = WTERMSIG(status); // extract signal number
            printf("Child process was terminated by signal %d.\n", signal_num);
        }
    } else { // fork error
        perror("fork");
        exit(1);
    }

    return 0;
}