number_dict={0:'',1:'I',2:'II',3:'III',4:'IV',5:'V',6:'VI',7:'VII',8:'VIII',9:'IX'}

def printNumber(dig,place):
    if place==1:
        print(number_dict[dig],end='')

    if place==10:
        rom_dict={'':'','I':'X','V':'L','X':'C'};
        basic_roman=number_dict[dig]
        for num in basic_roman:
            print(rom_dict[num],end='')
    elif place==100:
        rom_dict={'':'','I':'C','V':'D','X':'M'};
        basic_roman=number_dict[dig]
        for num in basic_roman:
            print(rom_dict[num],end='')

    elif place==1000:
        rom_dict={'':'','I':'M','V':'{Vdash}','X':'{Xdash}'};
        basic_roman=number_dict[dig]
        for num in basic_roman:
            print(rom_dict[num],end='')

def printRoman(number,place):
    if(number==0):
        return 
    dig=number%10
    printRoman(number//10,place*10)
    printNumber(dig,place)

if __name__=='__main__':

    while True:
        while True:
            try:
                val=input('Enter the number: ')
                if val=='quit':
                    exit(0)
            except:
                continue
            val=int(val)
            break
        printRoman(val,1)
        print()

