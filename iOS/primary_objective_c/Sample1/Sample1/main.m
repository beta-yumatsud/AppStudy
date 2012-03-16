//
//  main.m
//  Sample1
//
//  Created by 優貴 松田 on 11/12/28.
//  Copyright (c) 2011年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#define MAXNUM 5

double calcBmi(double height, double weight);

double calcBmi(double height, double weight){
    double result;
    result = weight / pow((height / 100), 2);
    return result;
}

int main (int argc, const char * argv[])
{

    @autoreleasepool {
        
        // insert code here...
        NSLog(@"Hello, World!");
        NSLog(@"Hello, Objective-C\tC++\n\"Java\"");
        NSLog(@"I'm %d years old. My BMI is %.2f", 40, 12.457);
        NSLog(@"7 + 5 * 6 / 2 = %d", 7 + 5 * 6 / 2);
        
        int num1 = 13, num2 = 14;
        NSLog(@"13 * 14 = %d", num1 * num2);
        
        const double BMI = 22;
        double height = 175;
        double stdWeight;
        
        //stdWeight = (height / 100) * (height / 100) * BMI;
        stdWeight = pow((height / 100), 2) * BMI;
        NSLog(@"%.1fcm -> %.1fkg", height, stdWeight);
        
        char aChar = 'a';
        NSLog(@"%c", aChar);
        NSLog(@"%x", aChar);
        aChar++;
        NSLog(@"%c", aChar);
        
        int goukei = 130;
        int ninzu = 8;
        double heikin;
        heikin = (double)goukei / ninzu;
        NSLog(@"Average is %f.", heikin);
        
        double myBmi;
        myBmi = calcBmi(height, 80.0);
        NSLog(@"My BMI is %.2f.", myBmi);
        
        // Array
        /*
        int ages[MAXNUM];
        ages[0] = 15;
        ages[1] = 23;
        ages[2] = 25;
        ages[3] = 29;
        ages[4] = 26;
         */
        int ages[] = {15, 23, 25, 29, 26};
        
        int sum = 0;
        for (int i = 0; i < MAXNUM; i++) {
            sum = sum + ages[i];
        }
        NSLog(@"HEIKIN = %.1f", (double)sum/MAXNUM);
        
        // pointer
        int thisYear = 2012;
        int *ptrYear1, *ptrYear2;
        
        ptrYear1 = &thisYear;
        ptrYear2 = &thisYear;
        
        *ptrYear1 = 2000;
        
        NSLog(@"thisYear: %d", thisYear);
        NSLog(@"ptrYear1: %d", *ptrYear1);
        NSLog(@"ptrYear2: %d", *ptrYear2);
        
        // 構造体
        typedef struct{
            NSString *name;
            int age;
        }person;
        person taro, ichiro;
        person *yamada;
        
        taro.name = @"Tanaka Ichiro";
        taro.age = 30;
        
        ichiro.name = @"Yamada Taro";
        ichiro.age = 25;
        
        NSLog(@"%@: %d", taro.name, taro.age);
        NSLog(@"%@: %d", ichiro.name, ichiro.age);
        
        yamada = &taro;
        //NSLog(@"%@ :%d years old", (*yamada).name, (*yamada).age);
        NSLog(@"%@ :%d years old", yamada->name, yamada->age);

    }
    return 0;
}

