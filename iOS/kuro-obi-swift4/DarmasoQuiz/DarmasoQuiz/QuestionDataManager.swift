//
//  QuestionDataManager.swift
//  DarmasoQuiz
//
//  Created by Yuki Matsuda on 2018/01/14.
//  Copyright © 2018年 Yuki Matsuda. All rights reserved.
//

import Foundation

class QuestionData {
    var question: String
    
    var answer1: String
    var answer2: String
    var answer3: String
    var answer4: String
    
    var correctAnswerNumber: Int
    
    var userChoiceAnswerNumber: Int?
    
    var questionNo: Int = 0
    
    init(questionSourceDataArray: [String]) {
        question = questionSourceDataArray[0]
        answer1 = questionSourceDataArray[1]
        answer2 = questionSourceDataArray[2]
        answer3 = questionSourceDataArray[3]
        answer4 = questionSourceDataArray[4]
        correctAnswerNumber = Int(questionSourceDataArray[5])!
    }
    
    func isCorrect() -> Bool {
        if correctAnswerNumber == userChoiceAnswerNumber {
            return true
        }
        return false
    }
}

class QuestionDataManager {
    static let sharedInstance = QuestionDataManager()
    
    var questionDataArray = [QuestionData]()
    
    var nowQuestionIndex: Int = 0
    
    private init() {
    }
    
    func loadQuestion() {
        questionDataArray.removeAll()
        nowQuestionIndex = 0
        
        guard let csvFilePath = Bundle.main.path(forResource: "question", ofType: "csv") else {
            print("CSVファイルが存在しません")
            return
        }
        do {
            let csvStringData = try String(contentsOfFile: csvFilePath, encoding: String.Encoding.utf8)
            csvStringData.enumerateLines(invoking: {(line, stop) in
                let questionSourceDataArray = line.components(separatedBy: ",")
                let questionData = QuestionData(questionSourceDataArray: questionSourceDataArray)
                self.questionDataArray.append(questionData)
                questionData.questionNo = self.questionDataArray.count
            })
        } catch let error {
            print("CSVファイル読み込みエラーが発生しました:\(error)")
            return
        }
    }
    
    func nextQuestion() -> QuestionData? {
        if nowQuestionIndex < questionDataArray.count {
            let nextQuestion = questionDataArray[nowQuestionIndex]
            nowQuestionIndex += 1
            return nextQuestion
        }
        return nil
    }
}
