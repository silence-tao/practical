a = {}
a["key"] = "value"

key = 10
a[key] = 20
a[key] = a[key] + 12

for k, v in pairs(a) do
    print(k .. " : " .. v)
end
