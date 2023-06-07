#This function takes two numbers from the user, adds them together and returns their sum
def add_numbers(a, b):
    result = a + b
    return result

num1 = input("Enter the first number: ")
num2 = input("Enter the second number: ")

sum = add_numbers(num1, num2)
print("The sum is: " + sum)
