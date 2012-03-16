//
//  main.m
//  Sample4
//
//  Created by 優貴 松田 on 12/02/06.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

int main (int argc, const char * argv[])
{

    @autoreleasepool {
        
        // insert code here...
        NSMutableString *str1;
        str1 = [[NSMutableString alloc] initWithCapacity:10];
        [str1 appendString:@"Hello"];
        [str1 appendString:@" Objective-C"];
        NSLog(@"%@", str1);
        
        NSString *str2 = @"abc";
        NSString *str3 = @"ABC";
        if ([str2 isEqualToString:str3]) {
            NSLog(@"same");
        }else{
            NSLog(@"not same");
        }
        
        NSString *string1 = @"0123456789";
        NSRange range = NSMakeRange(2, 4);
        NSString *string2 = [string1 substringWithRange:range];
        NSLog(@"%@", string2);
        
        NSString *path, *text;
        NSError *error;
        
        path = [[@"~" stringByExpandingTildeInPath] stringByAppendingString:@"/sample.txt"];
        text = [NSString stringWithContentsOfFile:path encoding:NSUTF8StringEncoding error:&error];
        if(text == nil){
            NSLog(@"Error:%@", [error localizedDescription]);
        }else{
            NSLog(@"¥n%@", text);
        }
        
        // 配列(オブジェクト)
        NSArray *theArray;
        theArray = [NSArray arrayWithObjects:@"one", @"two", @"three", nil];
        //for (int i = 0; i < [theArray count]; i++) {
        //    NSLog(@"%@", [theArray objectAtIndex:i]);
        //}
        for(NSString *str in theArray){
            NSLog(@"%@", str);
        }
        
        path = [[@"~" stringByExpandingTildeInPath] stringByAppendingString:@"/sample2.txt"];
        text = [NSString stringWithContentsOfFile:path encoding:NSUTF8StringEncoding error:&error];
        if(text == nil){
            NSLog(@"Error:%@", [error localizedDescription]);
        }else{
            NSArray *lines;
            lines = [text componentsSeparatedByString:@"¥n"];
            NSString *line;
            for (int i = 0; i < [lines count]; i++) {
                line = [lines objectAtIndex:i];
                if([[line stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceCharacterSet]] length] != 0){
                    NSLog(@"%d: %@", i, line);
                }
            }
        }
        
        NSMutableArray *mArray;
        mArray = [NSMutableArray arrayWithCapacity:5];
        [mArray addObject:@"one"];
        [mArray addObject:@"two"];
        [mArray addObject:@"three"];
        [mArray insertObject:@"new" atIndex:1];
        int i = 0;
        for(NSString *str in mArray){
            NSLog(@"%d: %@", i++, str);
        }
        
        NSMutableDictionary *myDic = [NSMutableDictionary dictionaryWithCapacity:5];
        [myDic setObject:@"Yamada Taro" forKey:@"taro55"];
        [myDic setObject:@"Tanaka Ichiro" forKey:@"macok3"];
        [myDic setObject:@"Sakai Naoko" forKey:@"flash33"];
        for(NSString *key in myDic){
            NSLog(@"%@ -> %@", key, [myDic objectForKey:key]);
        }
        
        NSCalendar *cal = [[NSCalendar alloc]initWithCalendarIdentifier:NSGregorianCalendar];
        NSDateComponents *comps = [[NSDateComponents alloc]init];
        [comps setYear:2010];
        [comps setMonth:4];
        [comps setDay:1];
        NSDate *date = [cal dateFromComponents:comps];
        NSLog(@"%@", date);
        
        NSArray *weekday = [NSArray arrayWithObjects:@"SUN", @"MON", @"TUE", @"WED", @"THU", @"FRI", @"SAT", nil];
        NSDate *today = [NSDate date];
        NSCalendar *cal2 = [[NSCalendar alloc]initWithCalendarIdentifier:NSGregorianCalendar];
        NSUInteger flags = NSYearCalendarUnit | NSMonthCalendarUnit | NSDayCalendarUnit | NSWeekdayCalendarUnit;
        NSDateComponents *comps2 = [cal2 components:flags fromDate:today];
        NSLog(@"year: %ld", [comps2 year]);
        NSLog(@"month: %ld", [comps2 month]);
        NSLog(@"day: %ld", [comps2 day]);
        NSLog(@"weekday: %@", [weekday objectAtIndex:[comps2 weekday] - 1]);
        
        // 1年後の今日の曜日を求める
        NSDateComponents *oneYear = [[NSDateComponents alloc]init];
        [oneYear setYear:1];
        NSDate *newDate = [cal2 dateByAddingComponents:oneYear toDate:today options:0];
        NSDateComponents *oneYearComp = [cal2 components:NSWeekdayCalendarUnit fromDate:newDate];
        NSLog(@"%@", [weekday objectAtIndex:[oneYearComp weekday] - 1]);
        
        // 今年の残り日数を求める
        NSInteger thisYear = [[cal2 components:NSYearCalendarUnit fromDate:today]year];
        NSDateComponents *newYearsDayComp = [[NSDateComponents alloc]init];
        [newYearsDayComp setYear:thisYear + 1];
        [newYearsDayComp setMonth:1];
        [newYearsDayComp setDay:1];
        NSDate *newYearDay = [cal2 dateFromComponents:newYearsDayComp];
        NSDateComponents *diffComp = [cal2 components:NSDayCalendarUnit fromDate:today toDate:newYearDay options:0];
        NSLog(@"%ld days remaining", [diffComp day]);
        
        // 指定した年、月の日数を求める
        int year = 2012;
        int month = 2;
        
        NSDateComponents *theDay = [[NSDateComponents alloc]init];
        [theDay setYear:year];
        [theDay setMonth:month + 1];
        [theDay setDay:0];
        NSDate *date2 = [cal dateFromComponents:theDay];
        NSDateComponents *newComp = [cal components:NSDayCalendarUnit fromDate:date2];
        NSLog(@"year:%d month:%d -> %lddays", year, month, [newComp day]);
    }
    return 0;
}

