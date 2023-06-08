package hackaton.amat.java.bug;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BankAccount {
        private int amount;
        
        BankAccount(int amount) {
            this.amount = amount;
        }
        
        public void takeAmount(int takeIt,int sl) {
            try {
                Thread.sleep(sl);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if ( amount >= takeIt) {
                amount-= takeIt;
                System.out.println(" Balance: " + amount);
            }
            else {
                System.out.println(" No money!!!");
            }
        }

        public static void main(String[] args) {
            for ( int i =0; i < 100; i++) {
                var a = new BankAccount(500);
                CompletableFuture<Void> a1 = CompletableFuture.runAsync(new Runnable() {
                    @Override
                    public void run() {
                        a.takeAmount(300, 100);

                    }
                });
                CompletableFuture<Void> a2= CompletableFuture.runAsync(new Runnable() {
                    @Override
                    public void run() {

                        a.takeAmount(400, 100);
                    }
                });
                try {
                    a1.get();
                    a2.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }


