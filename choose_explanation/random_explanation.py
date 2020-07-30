from random import shuffle
import re

# setting
filename = "./0730.txt"
num_probs = 9
# end setting

names = ["희연", "동훈", "원주"] * int(num_probs/3)
probs = []


f = open(filename,'rt', encoding='UTF8')

while True:
    line = f.readline()
    if not line:
        break

    if re.compile('http').match(line) or line == '\n':
        continue
    probs.append(line)


#print(probs)
#print(names)

shuffle(names)
f.close()

f = open(filename,'at', encoding='UTF8')

f.write('\n\n---------------------\n')
for i in range(num_probs):
    names[i] +=": "
    names[i] +=probs[i]
    print(names[i][:-1])
    f.write(names[i])

f.close()
