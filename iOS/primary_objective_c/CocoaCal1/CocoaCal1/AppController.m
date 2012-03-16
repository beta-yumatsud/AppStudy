//
//  AppController.m
//  CocoaCal1
//
//  Created by 優貴 松田 on 12/02/10.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "AppController.h"

@implementation AppController

// イニシャライズ
- (id) init
{
    self = [super init];
    if(self != nil){
        myCal = [[UsefulCal alloc] init];
    }
    return (self);
}

// 次の月へ
- (IBAction) nextMonth:(id) sender
{
    [myCal nextMonth];
    [calField setStringValue:[myCal calStr]];
    [yearLabel setStringValue:[NSString stringWithFormat:@"%d", myCal.year]];
    [monthLabel setStringValue:[NSString stringWithFormat:@"%d", myCal.month]];
}

// 前の月へ
- (IBAction) prevMonth:(id) sender
{
    [myCal prevMonth];
    [calField setStringValue:[myCal calStr]];
    [yearLabel setStringValue:[NSString stringWithFormat:@"%d", myCal.year]];
    [monthLabel setStringValue:[NSString stringWithFormat:@"%d", myCal.month]];
}

// 初期化処理
- (void) applicationDidFinishLaunching:(NSNotification *)aNotification
{
    // フォントを設定
    NSFont* font = [NSFont fontWithName:@"Monaco" size:24.0];
    [calField setFont:font];
    // カレンダー表示
    [calField setStringValue:[myCal calStr]];
    [yearLabel setStringValue:[NSString stringWithFormat:@"%d", myCal.year]];
    [monthLabel setStringValue:[NSString stringWithFormat:@"%d", myCal.month]];
}
@end
