from random import shuffle
import re
import os

for filename in os.listdir(os.getcwd()):
    if filename.endswith(".txt"):
        
        name = ["희연", "동훈", "원주"]
        probs = []
        f = open(filename,'rt', encoding='UTF8')
        nums = 0
        flag = False
        
        while True and not flag:
            line = f.readline()
            if not line:
                break

            if line == "---------------------\n":
                flag = True
                break

            if re.compile('http').match(line) or line == '\n':
                continue
            probs.append(line)
            nums += 1

        if flag:
            f.close()
            continue

            
        #print(probs)
        #print(names)
        names = []
        for _ in range(nums//3 + (1 if nums%3 else 0)):
            names += name

        for _ in range(nums%3):
            probs.append(".")
        
        shuffle(names)
        f.close()

        f = open(filename,'at', encoding='UTF8')

        f.write('\n\n---------------------\n')
        print("\t< ",filename, "> :")
        for i in range(nums):
            names[i] +=": "
            names[i] +=probs[i]
            print(names[i][:-1])
            f.write(names[i])

        f.close()
