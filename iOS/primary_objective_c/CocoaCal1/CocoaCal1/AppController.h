//
//  AppController.h
//  CocoaCal1
//
//  Created by 優貴 松田 on 12/02/10.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Cocoa/Cocoa.h>
#import "UsefulCal.h"

@interface AppController : NSObject{
    UsefulCal *myCal;
    
    IBOutlet id yearLabel;
    IBOutlet id monthLabel;
    IBOutlet id calField;
}

- (id) init;
- (IBAction) nextMonth:(id) sender;
- (IBAction) prevMonth:(id) sender;

@end
