//
//  AppDelegate.h
//  HelloGUI
//
//  Created by 優貴 松田 on 12/02/09.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Cocoa/Cocoa.h>

@interface AppDelegate : NSObject <NSApplicationDelegate>{
    NSWindow *window;
    IBOutlet id outputField;
}

@property (assign) IBOutlet NSWindow *window;

@end
