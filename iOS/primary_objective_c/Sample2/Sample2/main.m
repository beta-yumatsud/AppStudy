//
//  main.m
//  Sample2
//
//  Created by 優貴 松田 on 12/02/01.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#define BMI 22

@interface HCare : NSObject
{
    NSString *name;
    double height;
}
- (void) setName:(NSString *)name;
- (NSString *) name;
- (void) setHeight:(double) height;
- (double) height;
- (double) stdWeight;

@end

@implementation HCare

- (void) setName:(NSString *) n
{
    name = n;
}

- (NSString *) name
{
    return name;
}

- (void) setHeight:(double) h
{
    height = h;
}

- (double) height
{
    return height;
}

- (double) stdWeight
{
    return (pow((height / 100), 2) * BMI);
}

@end

int main (int argc, const char * argv[])
{

    NSDate *now;
    NSString *nowStr;
    
    // 単純に現在時刻を返す
    now = [[NSDate alloc] init];
    nowStr = [now description];
    NSLog(@"%@", nowStr);
    
    // ロケールを指定して現在の時刻を返す
    NSLocale *cLocale = [NSLocale currentLocale];
    nowStr = [now descriptionWithLocale:cLocale];
    NSLog(@"%@", nowStr);
    
    // 指定した日時までの日数を求める
    NSDate *theDate;
    theDate = [[NSDate alloc]initWithString:@"2009-1-05 00:00:00 +0900"];
    
    NSTimeInterval diff = [theDate timeIntervalSinceNow];
    int day = diff / 60 / 60 / 24;
    
    NSLog(@"Days: %d", day);
    
    
    // HCareクラスを利用した例
    HCare *myself, *myFriend;
    myself = [[HCare alloc] init];
    [myself setName:@"OTU TARO"];
    [myself setHeight:180];
    NSLog(@"%@ :%.1fkg", [myself name], [myself stdWeight]);
    
    myFriend = [[HCare alloc] init];
    [myFriend setName:@"KITA JIN"];
    [myFriend setHeight:175];
    NSLog(@"%@ :%.1fkg", [myFriend name], [myFriend stdWeight]);
    
    return 0;
}

