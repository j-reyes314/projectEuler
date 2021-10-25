#Problem 9- Project Euler
# A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
#
# a^2 + b^2 = c^2
# For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
#
# There exists exactly one Pythagorean triplet for which a + b + c = 1000.
# Find the product abc.

def triplet(x,y):
    return (x**2 + y**2)

def construct():
    for i in range(500):
        for j in range(i):
            a = i**2 - j**2
            b =2 *i*j
            c= i**2 + j**2

            if(a+b+c ==1000):    return a*b*c

def main():
    print(construct())

main()
