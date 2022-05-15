package com._线程通信;

public class SaveThread extends Thread{
    private Account acc;

    public SaveThread(Account acc,String name){
        super(name);
        this.acc = acc;
    }

    @Override
    public void run() {
        while (true) {
            acc.saveMoney(10000);
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
