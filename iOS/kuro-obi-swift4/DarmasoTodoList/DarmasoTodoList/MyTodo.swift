//
//  MyTodo.swift
//  DarmasoTodoList
//
//  Created by Yuki Matsuda on 2018/01/07.
//  Copyright © 2018年 Yuki Matsuda. All rights reserved.
//

import Foundation

class MyTodo: NSObject, NSCoding {
    
    var todoTitle: String?
    var todoDone: Bool = false
    
    override init() {
    }
    
    func encode(with aCoder: NSCoder) {
        aCoder.encode(todoTitle, forKey: "todoTitle")
        aCoder.encode(todoDone, forKey: "todoDone")
    }
    
    required init?(coder aDecoder: NSCoder) {
        todoTitle = aDecoder.decodeObject(forKey: "todoTitle") as? String
        todoDone = aDecoder.decodeBool(forKey: "todoDone")
    }
    
}
