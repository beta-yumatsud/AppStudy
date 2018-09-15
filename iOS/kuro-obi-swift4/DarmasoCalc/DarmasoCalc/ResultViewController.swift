//
//  ResultViewController.swift
//  DarmasoCalc
//
//  Created by Yuki Matsuda on 2018/01/06.
//  Copyright © 2018年 Yuki Matsuda. All rights reserved.
//

import UIKit

class ResultViewController: UIViewController {
    
    var price: Int = 0
    var percent: Int = 0

    @IBOutlet weak var resultField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let percentValue = Float(percent) / 100
        let discountPrice = Float(price) * percentValue
        let resultPrice = price - Int(discountPrice)
        resultField.text = "\(resultPrice)"
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
}
