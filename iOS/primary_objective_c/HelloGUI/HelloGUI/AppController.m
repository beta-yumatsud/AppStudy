//
//  AppController.m
//  HelloGUI
//
//  Created by 優貴 松田 on 12/02/09.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "AppController.h"

@implementation AppController

- (IBAction)hello:(id)sender
{
    NSString *str = [@"Hello, " stringByAppendingString:[inputField stringValue]];
    [outputField setStringValue:str];
}
@end
