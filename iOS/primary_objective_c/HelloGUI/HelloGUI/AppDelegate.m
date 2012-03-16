//
//  AppDelegate.m
//  HelloGUI
//
//  Created by 優貴 松田 on 12/02/09.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "AppDelegate.h"

@implementation AppDelegate

@synthesize window = _window;

- (void)applicationDidFinishLaunching:(NSNotification *)aNotification
{
    // Insert code here to initialize your application
    NSFont* font = [NSFont fontWithName:@"HiraKakuProN-W6" size:44.0];
    [outputField setFont:font];
    [outputField setStringValue:@""];
}

@end
