import os
from pathlib import Path
files_name = ['GroupChat.java','Client.java','Server.java','arp_rarp.java','RMIClient.java','RMIServer.java','myinterface.java']
name = input('Enter your name: ')
reg = input('Enter your registration number: ')
# Get the current working directory
cwd = os.getcwd()
#print(cwd)
dris = [d for d in os.listdir(cwd) if os.path.isdir(d)]
print(dris)
files = []
for i in dris:
    x = os.listdir(i)
    for j in x:
        if j in files_name:
            files.append(i+'/'+j)
for f in files:
    print(f)
    try:
        with open(f, 'r') as file:
            con = file.read()
        update_con = con.replace('SURESH S', name)
        update_con = update_con.replace('221211101143', reg)
        with open(f, 'w') as file:
            file.write(update_con)
            print(f'File {f} updated successfully')
    except Exception as e:
        print(e)