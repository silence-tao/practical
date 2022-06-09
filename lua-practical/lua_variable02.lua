a, b, c = 0, 1
print(a, b, c)		--> 0 1 nil

a, b = a + 1, b + 1, b + 2	-- b + 2 会被忽略
print(a, b)		--> 1 2

a, b, c = 0
print(a, b, c)		--> 0 nil nil
