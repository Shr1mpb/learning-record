package homework.h15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrimeUtil {

    public List<Long> getPrimeList(long start, long end, int threadCount) throws ExecutionException, InterruptedException {
        // 初始化返回量
        List<Long> primes = new ArrayList<>();
        // 初始化线程返回量
        List<Future<List<Long>>> futures = new ArrayList<>();
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        try  {
            // 计算任务均分给每个线程
            long rangeSize = (end - start) / threadCount;
            for (int i = 0; i < threadCount; i++) {
                long threadStart = start + i * rangeSize;
                long threadEnd = (i == threadCount - 1) ? end : threadStart + rangeSize;// 注意这里的范围 最后一个线程直接算到最后
                futures.add(executorService.submit(() -> {// lambda写一下比较方便 Callable下的call是函数式接口
                    List<Long> ret = new ArrayList<>();
                    for (long num = threadStart; num < threadEnd; num++) {
                        if (isPrime(num)) {
                            ret.add(num);
                        }
                    }
                    return ret;
                }));
            }
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            executorService.shutdown();
        }
        // 开始获取计算结果并拼接
        for (Future<List<Long>> future : futures) {
            List<Long> longs = future.get();
            primes.addAll(longs);
        }
        return primes;
    }

    private boolean isPrime(long num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;

        for (long i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }
}
