number_dict={0:'',1:'I',2:'II',3:'III',4:'IV',5:'V',6:'VI',7:'VII',8:'VIII',9:'IX'}

def printNumber(dig,place):

    rom_dict=dict()
    if place==1:
        print(number_dict[dig],end='')
        return

    elif place==10:
        rom_dict={'':'','I':'X','V':'L','X':'C'};

    elif place==100:
        rom_dict={'':'','I':'C','V':'D','X':'M'};

    elif place==1000:
        rom_dict={'':'','I':'M','V':'{Vdash}','X':'{Xdash}'};
    
    elif place==10000:
        rom_dict={'':'','I':'{Xdash}','V':'{Ldash}','X':'{Cdash}'};
    
    elif place==100000:
        rom_dict={'':'','I':'{Cdash}','V':'{Ddash}','X':'{Mdash}'};

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
        val=int(input("Enter a value: "))
        printRoman(val,1)
        print()

