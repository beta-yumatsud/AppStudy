//
//  main.m
//  Sample3
//
//  Created by 優貴 松田 on 12/02/02.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

int main (int argc, const char * argv[])
{
    // 制御構想(if文)
    int num1 = 10;
    int num2 = 8;
    
    NSLog(@"num1 == num2:%d", num1 == num2);
    NSLog(@"num1 != num2:%d", num1 != num2);
    NSLog(@"num1 > num2:%d", num1 > num2);
    NSLog(@"num1 >= num2:%d", num1 >= num2);
    NSLog(@"num1 < num2:%d", num1 < num2);
    NSLog(@"num1 <= num2:%d", num1 <= num2);
    
    int tensu = 90;
    int rankA = 90;
    int rankB = 80;
    int rankC = 60;
    
    if (tensu >= rankA) {
        NSLog(@"Rank A");
    }else if(tensu >= rankB){
        NSLog(@"Rank B");
    }else if(tensu >= rankC){
        NSLog(@"Rank C");
    }else{
        NSLog(@"Rank D");
    }
    
    int english = 90;
    int math = 65;
    int refValue = 80;
    if((english >= refValue) && (math >= refValue)){
        NSLog(@"GOUKAKU");
    }else{
        NSLog(@"FU-GOUKAKU");
    }
    
    // 制御構造(switch文)
    int month = 3;
    switch (month) {
        case 12:
        case 1:
        case 2:
            NSLog(@"Winter");
            break;
        case 3:
        case 4:
        case 5:
            NSLog(@"Spring");
            break;
        case 6:
        case 7:
        case 8:
            NSLog(@"Summer");
            break;
        case 9:
        case 10:
        case 11:
            NSLog(@"Autumn");
            break;
        default:
            NSLog(@"must be 1-12");
    }
    
    // 繰り返し処理
    int i;
    for(i = 1; i <= 10; i++){
        NSLog(@"Hello, %d", i);
    }
    
    int maxNum = 10;
    int sum = 0;
    for (int j = 1; j <= maxNum; j++) {
        sum += j;
    }
    NSLog(@"1 to %d => %d by for", maxNum, sum);
    
    sum = 0;
    int k = 1;
    while (k <= maxNum) {
        sum += k;
        k++;
    }
    NSLog(@"1 to %d => %d by while", maxNum, sum);
    
    int num = 4;
    i = 1;
    do {
        NSLog(@"Hello");
        i++;
    } while (i <= num);
    
    maxNum = 4;
    i = 0;
    
    while (1) {
        NSLog(@"i = %d", i);
        if(i >= maxNum) break;
        i++;
    }
    
    maxNum = 90;
    for(i = 1; i <= maxNum; i++){
        if((i % 9) != 0) continue;
        NSLog(@"%d", i);
    }

    return 0;
}