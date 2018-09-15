//: Playground - noun: a place where people can play

import UIKit
import PlaygroundSupport

PlaygroundPage.current.needsIndefiniteExecution = true

//var str = "Hello, playground"
var testStr: String = "hogehoge"
var value: Int = 3;
var valueStr: String = String(value)
print("変数はこんな感じで展開できるみたいよ:value=\(value)")
var arr: [String] = [String]()
print(arr)
arr.append(testStr)
arr.append(valueStr)
print(arr)
let dic: [String:String] = ["key1":"hugahuga", "key2":"hogehoge"]
print(dic)
var val: String?
val = "hello"
//print(val)
if let val = val {
    print(val)
}
func myFunc(value: Int) -> Int {
    let valueInt: Int = value + 1
    return valueInt
}
myFunc(value: 10)
enum MyError: Error {
    case InvalidValue
}
func doubleUp(value: Int) throws -> Int {
    if value < 0 {
        throw MyError.InvalidValue
    }
    return value * 2
}
do {
    defer {
        print("deferだね〜")
    }
    let doubleResultValue = try doubleUp(value: 10)
    print("正常終了:\(doubleResultValue)")
} catch MyError.InvalidValue {
    print("以上狩猟")
}
var radio = UISwitch()
radio.isOn = true
radio.setOn(false, animated: true)
class Dog {
    var name = ""
    var type = ""
    var birth: Date = Date()
    
    func bark() {
        print("吠えたったわ")
    }
    
    func walk() {
    }
}
var dog: Dog = Dog()
dog.name = "king"
dog.bark()
class GuideDog: Dog {
    override func bark() {
        super.bark()
        print("盲導犬と言えど吠えるでしかしっ！")
    }
}
var guideDog: GuideDog = GuideDog()
guideDog.bark()
struct MyStruct {
    var valStr: String?
    func callMethod(value: Int) -> Int {
        let resVal: Int = 0
        return resVal
    }
}
enum Fluit {
    case Apple
    case Orange
}
func requestMinAndMax() -> (min: Int, max: Int) {
    return (1, 100)
}
let minAndMax = requestMinAndMax()
print(minAndMax.min)
print(minAndMax.max)
for val in arr {
    print(val)
}
var res: Int = 0;
for num in 0..<9 {
    res = res + num;
}
print(res)
for (key, value) in dic.enumerated() {
    print("\(key):\(value)")
}
if res > 100 {
    print("resは100より大きいらしいよ")
} else if res > 10 {
    print("resは10よりは大きいらしいよ")
} else {
    print("resはなんか小さいらしいよ")
}
switch res {
case 100:
    print("resは100らしい")
default:
    print("resのあたいは何かわからん")
}
let valFruit: Fluit = Fluit.Apple
switch valFruit {
case .Apple:
    print("りんごじゃな")
case .Orange:
    print("オレンジかいな")
}
func buyItem(myMoney: Int?) {
    guard let money = myMoney else {
        return
    }
    print("所持金は\(money)")
}
buyItem(myMoney: 10000)
var allVal: [Int] = [11,4,5,16,25]
let values = allVal.filter{(x: Int) -> Bool in
    if x < 10 {
        return true
    }
    return false
}
print(values)
let doubleUpValues = allVal.map{$0 * 2}
print(doubleUpValues)
let totalValues = allVal.reduce(0, {$0 + $1})
print(totalValues)
allVal.sort{$0 < $1}
for value in allVal where value != 4 {
    print(value)
}
switch res {
case let x where x >= 10:
    print("うん、ここだよね")
default:
    print("もはや何かわからんこの現実")
}
let fluits: [Fluit] = [.Apple, .Orange, .Apple]
for case .Apple in fluits {
    print("Appleじゃボケ")
}

let session = URLSession.shared
if let url = URL(string: "https://www.yahoo.co.jp") {
    let request = URLRequest(url: url)
    let task = session.dataTask(with: request, completionHandler: {
        (data:Data?, response:URLResponse?,error:Error?) in
        guard let data = data else {
            print("データなし")
            return
        }
        let value = String(data: data, encoding: String.Encoding.utf8)
        print(value!)
    })
    task.resume()
}
