package ch.zhaw.prog2.account;

public class Account {
    private int id;
    private int saldo = 0;

    public Account(int id, int initialAmount) {
        this.id = id;
        this.saldo = initialAmount;
    }

    public int getId() {
        return id;
    }

    public int getSaldo() {
        return saldo;
    }

    public synchronized void changeSaldo(int delta) {
        this.saldo += delta;
    }
}
