import threading

class BankAccount :
    def __init__(self, balance)
        self.balance = balance

    def withdraw(self, amount)
        if self.balance >= amount :
            print(f"Wihdrawing ${amount}...")
            self.balance -= amount
            print(f"Withdrawl succesful. Current balance: {self.balance}")
        else :
            print("Withdrawl funds!")

def withdraw_amount(account, amount)  :
    account.withdraw(amount)

def concurrent() :
    account = BankAccount(500)
    thread1 = threading.Thread(target=withdraw_amount, args=(account,200)
    thread2 = threading.Thread(target=withdraw_amount, args=(account,400)

     thread1.start()
     thread2.start()

     thread1.join()
     thread2.join()
