import os
from pathlib import Path
name = input('Enter your name: ')
reg = input('Enter your registration number: ')
# Get the current working directory
cwd = os.getcwd()
#print(cwd)
dris = [d for d in os.listdir(cwd) if os.path.isdir(d)]
print(dris)

for i in dris:
    #print(i)
    files = os.listdir(i)
    #print(files)
    if files in {'Client.java','Server.java','arp_rarp.java','RMIClient.java','RMIServer.java'}:
        for f in files:
            try:
                with open(f, 'r') as file:
                    con = file.read()
                update_con = con.replace('SURESH S', name)
                update_con = update_con.replace('221211101143', reg)
                with open(f, 'w') as file:
                    file.write(update_con)
            except Exception as e:
                print(e)