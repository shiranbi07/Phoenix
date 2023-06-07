import threading

# Shared resources
resource_a = threading.Lock()
resource_b = threading.Lock()


def thread_a():
    with resource_a:
        print("Thread A acquired resource A.")
        with resource_b:
            print("Thread A acquired resource B.")


def thread_b():
    with resource_b:
        print("Thread B acquired resource B.")
        with resource_a:
            print("Thread B acquired resource A.")


def deadlock():
    # Creating two threads
    thread1 = threading.Thread(target=thread_a)
    thread2 = threading.Thread(target=thread_b)

    # Starting the threads
    thread1.start()
    thread2.start()


# Waiting for both threads to finish
    thread1.join()
    thread2.join()
