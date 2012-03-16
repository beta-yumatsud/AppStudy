//
//  UsefulCal.m
//  Sample5
//
//  Created by 優貴 松田 on 12/02/09.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "UsefulCal.h"

@implementation UsefulCal

- (void) nextMonth
{
    if(month == 12){
        month = 1;
        year++;
    }else{
        month++;
    }
}

- (void) prevMonth
{
    if(month == 1){
        month = 12;
        year--;
    }else{
        month--;
    }
}

@end
