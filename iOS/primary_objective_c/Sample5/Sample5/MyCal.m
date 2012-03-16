//
//  MyCal.m
//  Sample5
//
//  Created by 優貴 松田 on 12/02/08.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "MyCal.h"

@implementation MyCal

@synthesize year, month;

- (id) init
{
    if(self = [super init]){
        // 今日の日付を生成
        NSDate *today = [NSDate date];
        // 暦を西暦にする
        cal = [[NSCalendar alloc]initWithCalendarIdentifier:NSGregorianCalendar];
        // 年と月を生成
        unsigned int unitFlags = NSYearCalendarUnit | NSMonthCalendarUnit;
        NSDateComponents *comps = [cal components:unitFlags fromDate:today];
        year = [comps year];
        month = [comps month];
    }
    return (self);
}

- (NSString *) calStr
{
    int column = 1;
    NSMutableString *calStr;
    calStr = [NSMutableString stringWithCapacity: 40];
    [calStr appendString:@" Su Mo Tu We Th Fr Sa\n"];
    // 最初の曜日までスキップ
    for (int i = 1; i < [self firstDay]; i++) {
        [calStr appendString:@"   "];
        column++;
    }
    
    NSString *day;
    for (int i = 1; i <= [self daysOfMonth:month inYear:year]; i++) {
        // 日付が一桁の場合はスペースを多くする
        if( i < 10 ){
            day = [NSString stringWithFormat:@"  %d", i];
        }else{
            day = [NSString stringWithFormat:@" %d", i];
        }
        [calStr appendString:day];
        // 7日分表示したら改行をいれる
        if(column == 7){
            [calStr appendString:@"\n"];
            column = 0;
        }
        column++;
    }
    return (calStr);
}

- (long) firstDay
{
    NSDateComponents *comps = [[NSDateComponents alloc] init];
    // 今年の1日に
    [comps setDay:1];
    [comps setMonth:month];
    [comps setYear:year];
    NSDate *theDay = [cal dateFromComponents:comps];
    NSDateComponents *weekdayComps = [cal components:NSWeekdayCalendarUnit fromDate:theDay];
    return ([weekdayComps weekday]);
}

- (long) daysOfMonth:(long)m inYear:(long)y
{
    NSDateComponents *theDay = [[NSDateComponents alloc] init];
    // 次の月の0日に
    [theDay setYear:year];
    [theDay setMonth:month + 1];
    [theDay setDay:0];
    NSDate *date = [cal dateFromComponents:theDay];
    return ([[cal components:NSDayCalendarUnit fromDate:date] day]);
}

@end
