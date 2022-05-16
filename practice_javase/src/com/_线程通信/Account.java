package com._线程通信;

//
public class Account {
    private String cardId;
    private double money;

    public Account() {
    }

    public Account(String cardId, double money) {
        this.cardId = cardId;
        this.money = money;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public synchronized void saveMoney(double money) {
        try {
            String name = Thread.currentThread().getName();
            if (this.money > 0) {

                this.notifyAll();
                this.wait();
            } else {
                this.money += money;
                System.out.println(name + "存了" + money + ",余额：" + this.money);
                this.notifyAll();
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void drawMoney(double money) {
        try {
            String name = Thread.currentThread().getName();
            if (this.money >= money) {
                this.money -= money;
                System.out.println(name + "来取钱，取钱：" + money + "剩余：" + this.money);
                //取钱后没钱，唤醒别人，等待自己
                this.notifyAll();
                this.wait();
            } else {
                System.out.println("余额不足");
                this.notifyAll();
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
