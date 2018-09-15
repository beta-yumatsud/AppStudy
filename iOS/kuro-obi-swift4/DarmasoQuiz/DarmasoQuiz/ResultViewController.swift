//
//  ResultViewController.swift
//  DarmasoQuiz
//
//  Created by Yuki Matsuda on 2018/01/14.
//  Copyright © 2018年 Yuki Matsuda. All rights reserved.
//

import UIKit

class ResultViewController: UIViewController {

    @IBOutlet weak var correctPercentLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let questionCount = QuestionDataManager.sharedInstance.questionDataArray.count
        var correctCount: Int = 0
        for questionData in QuestionDataManager.sharedInstance.questionDataArray {
            if questionData.isCorrect() {
                correctCount += 1
            }
        }
        
        let correctPercent: Float = (Float(correctCount) / Float(questionCount)) * 100
        correctPercentLabel.text = String(format: "%.1f", correctPercent) + "%"
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
}
