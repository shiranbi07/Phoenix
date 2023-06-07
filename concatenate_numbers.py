#This function gets two numbers and concatenates them. For example: given the numbers 5 and 9 the function will return the result 59
def add_numbers(a, b):
    result = a + b
    return result

num1 = input("Enter the first number: ")
num2 = input("Enter the second number: ")

sum = add_numbers(num1, num2)
print("The sum is: " + sum)
