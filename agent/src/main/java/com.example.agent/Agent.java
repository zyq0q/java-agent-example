package com.example.agent;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class Agent {

    static int num = 0;
    static long period = 0;
    static int mem = 0;
    static List<Object> memList = new ArrayList<>();
    static long[] memoryFillIntVar = null;

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs:"+agentArgs);
        String[] split = agentArgs.split(",");
        num = Integer.valueOf(split[0]);
        period = Long.valueOf(split[1]);
        mem = Integer.valueOf(split[2]);

        //genMem();
        genMem2();

        for (int i = 0; i < num; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        double x = 0.0001;
                        for (int i = 0; i < 1000000; i++) {
                            x += Math.sqrt(x);
                        }
                    }
                }
            }).start();
        }

        System.out.println("wjagent begin");
    }

    static List<Integer> generateList() {
        return IntStream.range(0, num).parallel().map(IntUnaryOperator.identity()).
                collect(LinkedList::new, List::add, List::addAll);
        //return null;
    }
    

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static void genMem2(){
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        System.out.println(maxMemory);
        System.out.println(maxMemory/1024/1024);
        System.out.println(maxMemory * mem * 0.01/1024/1024);
        long blockSize = (long) (maxMemory * mem *0.01);
        memoryFillIntVar = new long[(int) (blockSize / Long.BYTES)];
    }
}

