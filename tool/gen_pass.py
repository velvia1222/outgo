import sys
from bcrypt import gensalt
from bcrypt import hashpw

if __name__ == '__main__':
    argv = sys.argv
    if len(argv) != 2:
        print('Usage: python3 %s password' % argv[0])
        quit()

    password = argv[1]

    print(hashpw(password.encode('utf-8'), gensalt(10, b'2a')))
