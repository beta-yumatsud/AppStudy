//
//  WebViewController.swift
//  DarmasoSearchApp
//
//  Created by Yuki Matsuda on 2018/01/15.
//  Copyright © 2018年 Yuki Matsuda. All rights reserved.
//

import UIKit
import WebKit

class WebViewController: UIViewController {
    
    @IBOutlet weak var webView: WKWebView!
    
    var itemUrl: String?

    override func viewDidLoad() {
        super.viewDidLoad()
        
        webView.customUserAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1"
        guard let imageUrl = itemUrl else {
            return
        }
        
        guard let url = URL(string: imageUrl) else {
            return
        }
        
        let request = URLRequest(url: url)
        webView.load(request)

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
}
