# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


import threading
import time


class BankAccount:
    def __init__(self, balance):
        self.balance = balance

    def withdraw(self, amount):
        if self.balance >= amount:
            self.balance -= amount
            if self.balance < 0:
                print(f"withdraw failed. Current balance: {self.balance}")
            else:
                print(f"withdraw successful. Current balance: {self.balance}")
        else:
            print("No money")


def withdraw_amount(account, amount):
    account.withdraw(amount)


def concurrent():
    account = BankAccount(500)
    thread1 = threading.Thread(target=withdraw_amount, args=(account, 200))
    thread2 = threading.Thread(target=withdraw_amount, args=(account, 400))

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()


# Press the green button in the gutter to run the script.

if __name__ == '__main__':
    for i in range(1, 100):
        concurrent()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
