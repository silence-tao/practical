a = {}
for i = 1, 10 do
    a[i] = i
end

a["key"] = "val"

print(a["key"])
print(a["none"])
