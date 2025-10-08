#include <stdio.h>
#include <unistd.h>

int main() {
   pid_t pid;

   pid = fork();
   if (pid == -1) { // Error occurred
      printf("Failed to create child process\n");
   }
   else if (pid == 0) { // Child process
      printf("Hello from child process! My PID is %d\n", getpid());      
      printf("Hello from child process! My parent PID is %d\n", getppid());
   }
   else { // Parent process
      printf("Hello from parent process! My PID is %d\n", getpid());
      printf("Hello from parent process! My child PID is %d\n", pid);
   }

   return 0;
}
