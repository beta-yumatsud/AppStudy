//
//  ItemTableViewCell.swift
//  DarmasoSearchApp
//
//  Created by Yuki Matsuda on 2018/01/15.
//  Copyright © 2018年 Yuki Matsuda. All rights reserved.
//

import UIKit

class ItemTableViewCell: UITableViewCell {
    
    var itemUrl: String?
    @IBOutlet weak var itemImageView: UIImageView!
    @IBOutlet weak var itemTitleLabel: UILabel!
    @IBOutlet weak var itemPriceLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    override func prepareForReuse() {
        itemImageView.image = nil
    }
}
