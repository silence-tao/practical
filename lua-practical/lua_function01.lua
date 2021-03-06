function testFun(tab, fun)
    for k, v in pairs(tab) do
        print(fun(k, v))
    end
end

tab = {key1 = "value1", key2 = "value2"}

testFun(tab,
    function(key, val) -- 匿名函数
        return key .. " = " .. val
    end
)

