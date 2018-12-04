raw_data = [0.056, 0.088, 0.19, 0.467, 0.813, 1.271, 2.134, 2.82, 3.924, 4.938, 5.875, 7.088, 8.771, 10.605, 12.119, 14.47, 16.949, 19.138, 21.966, 23.824, 27.857, 30.971, 33.691, 38.082, 46.113, 50.83, 53.8, 60.802, 64.321, 70.119, 79.735]

size_raw = [100 * i for i in range(1, 32)]

import scipy
import numpy as np

def func(x,a,b):
    return a * (x**2) * np.log(x)

from scipy.optimize import curve_fit

a, b = curve_fit(func, size_raw, raw_data)
print(a,b)

pred = []
for x in size_raw:
    
    pred.append(func(x, *a))

import matplotlib.pyplot as plt

time_max = 3600*24

for x in range(1000000):
    if func(x, *a) > time_max:
        print(x)
        break
