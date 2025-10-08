#include <stdio.h>      // for printf
#include <stdlib.h>     // for exit
#include <unistd.h>     // for fork(), getpid(), sleep()
#include <signal.h>     // for kill(), SIGINT


int main() {
    pid_t pid;

    pid = fork();

    if (pid == -1) {
        printf("Failed to fork\n");
        exit(1);
    } else if (pid == 0) {
        // Child process
        printf("\tCHILD: This is the child process (PID=%d)\n", getpid());
        sleep(10); // wait for 10 seconds
    } else {
        // Parent process
        printf("PARENT: This is the parent process (PID=%d)\n", getpid());
        printf("PARENT: His child process ID is %d\n", pid);
        sleep(2); // wait for 2 seconds
        printf("PARENT: Sending signal to child process...\n");
        kill(pid, SIGINT); // send SIGINT signal to child process
    }
    return 0;
}