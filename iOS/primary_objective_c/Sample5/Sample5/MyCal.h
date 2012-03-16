//
//  MyCal.h
//  Sample5
//
//  Created by 優貴 松田 on 12/02/08.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface MyCal : NSObject
{
    NSCalendar *cal;
    long year;
    long month;
}

@property long year;
@property long month;

- (id) init;
- (NSString *) calStr;
- (long) firstDay;
- (long) daysOfMonth: (long)m inYear: (long)y;

@end
