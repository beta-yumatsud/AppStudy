//
//  main.m
//  Sample5
//
//  Created by 優貴 松田 on 12/02/08.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "MyCal.h"
#import "UsefulCal.h"
#import "MyCal+MoveMonth.h"

#define BMI 22

@interface HCare : NSObject
{
    NSString *name;
    double height;
}

- (id) init;
- (id) initWithName:(NSString *)name;
- (id) initWithName:(NSString *)name height:(int)heoght;

- (double) stdWeight;

@property double height;
@property (copy) NSString *name;

@end

@implementation HCare

// 指定イニシャライズ
- (id) initWithName:(NSString *)n height:(int)h
{
    self = [super init];
    if(self != nil){
        name = n;
        height = h;
    }
    return (self);
}

- (id) initWithName:(NSString *)n
{
    self = [self initWithName:n height:-1];
    return (self);
}

- (id) init
{
    self = [self initWithName:@"NO NAME" height:-1];
    return (self);
}

@synthesize height;
@synthesize name;

- (double) stdWeight
{
    return (pow((height / 100), 2) * BMI);
}

@end

int main (int argc, const char * argv[])
{

    @autoreleasepool {
        
        HCare *myself, *myFriend;
        myself = [[HCare alloc]init];
        myself.name = @"OTU TARO";
        myself.height = 180;
        NSLog(@"%@ :%.1fkg", myself.name, [myself stdWeight]);
        
        myFriend = [[HCare alloc]init];
        myFriend.name = @"KITA JIN";
        myFriend.height = 165;
        NSLog(@"%@ :%.1fkg", myFriend.name, [myFriend stdWeight]);
        
        // 作成したイニシャライズで試す
        HCare *person1, *person2, *person3;
        person1 = [[HCare alloc] init];
        NSLog(@"%@ : %.1fcm", person1.name, person1.height);
        
        person2 = [[HCare alloc] initWithName:@"YAMADA HANAKO"];
        NSLog(@"%@ : %.1fcm", person2.name, person2.height);
        
        person3 = [[HCare alloc] initWithName:@"HOSI JUN" height:160];
        NSLog(@"%@ : %.1fcm", person3.name, person3.height);
        
        // カレンダー表示
        MyCal *myCal;
        myCal = [[MyCal alloc] init];
        NSString *str = [myCal calStr];
        
        // 今月のカレンダーを表示
        NSLog(@"year:%ld month:%ld", myCal.year, myCal.month);
        NSLog(@"%@", str);
        
        // 2014年6月のカレンダーを表示
        myCal.year = 2014;
        myCal.month = 6;
        str = [myCal calStr];
        NSLog(@"year:%ld month:%ld", myCal.year, myCal.month);
        NSLog(@"%@", str);
        
        // 前後の月
        UsefulCal *myCal2;
        myCal2 = [[UsefulCal alloc] init];
        str = [myCal2 calStr];
        NSLog(@"year:%ld month:%ld", myCal2.year, myCal2.month);
        NSLog(@"%@", str);
        
        [myCal2 prevMonth];
        str = [myCal2 calStr];
        NSLog(@"year:%ld month:%ld", myCal2.year, myCal2.month);
        NSLog(@"%@", str);
        
        [myCal2 nextMonth];
        [myCal2 nextMonth];
        str = [myCal2 calStr];
        NSLog(@"year:%ld month:%ld", myCal2.year, myCal2.month);
        NSLog(@"%@", str);
        
        [myCal2 nextMonth];
        str = [myCal2 calStr];
        NSLog(@"year:%ld month:%ld", myCal2.year, myCal2.month);
        NSLog(@"%@", str);
        
        // カテゴリ使用例
        MyCal *myCal3;
        myCal3 = [[MyCal alloc] init];
        str = [myCal3 calStr];
        NSLog(@"year:%ld month:%ld", myCal3.year, myCal3.month);
        NSLog(@"%@", str);
        
        [myCal3 prevMonth];
        str = [myCal3 calStr];
        NSLog(@"year:%ld month:%ld", myCal3.year, myCal3.month);
        NSLog(@"%@", str);
        
        [myCal3 nextMonth];
        [myCal3 nextMonth];
        str = [myCal3 calStr];
        NSLog(@"year:%ld month:%ld", myCal3.year, myCal3.month);
        NSLog(@"%@", str);
        
        [myCal3 nextMonth];
        str = [myCal3 calStr];
        NSLog(@"year:%ld month:%ld", myCal3.year, myCal3.month);
        NSLog(@"%@", str);

    }
    return 0;
}

