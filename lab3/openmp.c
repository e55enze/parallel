#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>

int counter = 0;

void *heavy_task() {
  int limit = 1e8;

  for(int i=0; i < limit; i++){
    // result += i;
    sqrt(i);
  }
}


void openmp(int thread_num){
#pragma omp parallel for num_threads(thread_num)
  for (int i = 0; i < thread_num; i++) {
    heavy_task();
  }
}


int main(int argc, char** argv){
    int counter = 0;
    int threads_num = atoi(argv[1]);

    openmp(threads_num);

    printf("Counter: %d \n",counter);

}