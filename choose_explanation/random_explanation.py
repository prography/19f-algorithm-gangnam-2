from random import shuffle
import re

names = ["희연", "동훈", "원주"] * 2
probs = []

filename = "./0528.txt"
f = open(filename,'rt', encoding='UTF8')

while True:
    line = f.readline()
    if not line:
        break

    if re.compile('http').match(line) or line == '\n':
        continue

    cnt = 0
    
    probs.append(line)


#print(probs)
#print(names)

shuffle(names)
f.close()

f = open(filename,'at', encoding='UTF8')

f.write('\n\n---------------------\n')
for i in range(6):
    names[i] +=": "
    names[i] +=probs[i]
    print(names[i][:-1])
    f.write(names[i])

f.close()
