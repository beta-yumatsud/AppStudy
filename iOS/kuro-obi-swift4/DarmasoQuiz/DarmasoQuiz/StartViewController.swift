//
//  StartViewController.swift
//  DarmasoQuiz
//
//  Created by Yuki Matsuda on 2018/01/14.
//  Copyright © 2018年 Yuki Matsuda. All rights reserved.
//

import UIKit

class StartViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        QuestionDataManager.sharedInstance.loadQuestion()
        guard let nextViewController = segue.destination as? QuestionViewController else {
            return
        }
        guard let questionData = QuestionDataManager.sharedInstance.nextQuestion() else {
            return
        }
        nextViewController.questionData = questionData
    }
    
    @IBAction func goToTitle(_ segue: UIStoryboardSegue) {
        
    }
}
