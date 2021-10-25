def primes_sum(num):
    sum = 0

    
    if num >=2: sum += 2
    if num >= 3: sum +=3
    if num >= 5: sum +=5
    if num >= 7: sum +=7
    
    for i in range(11,num):
        # print('hello')
        even = False

        if(i % 2 ==0):  
            continue
        if(i % 3 ==0):
            continue
        
        j = 5

        while( j * j <= i ):
            if(  i % j == 0 or i % (j + 2) == 0 ):
                even = True
                break
            else:
                j += 6
        if even:
            continue
        else:
            sum += i
    

    return sum

print(primes_sum(2000000))
