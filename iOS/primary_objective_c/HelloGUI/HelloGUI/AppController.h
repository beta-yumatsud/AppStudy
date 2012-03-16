//
//  AppController.h
//  HelloGUI
//
//  Created by 優貴 松田 on 12/02/09.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Cocoa/Cocoa.h>

@interface AppController : NSObject{
    IBOutlet id inputField;
    IBOutlet id outputField;
}

- (IBAction)hello:(id)sender;
@end
