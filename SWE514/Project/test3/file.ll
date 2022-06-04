; ModuleID = 'stm2ir'
declare i32 @printf(i8*, ...)
@print.str = constant [4 x i8] c"%d\0A\00"

define i32 @main() {
   %a = alloca i32
   %b = alloca i32
   %c = alloca i32
   %1 = add i32 5, 3
   %2 = mul i32 4, %1
   %3 = sub i32 4, 2
   %4 = udiv i32 %2, %3
   store i32 %4, i32* %a
   %5 = load i32* %a
   %6 = sub i32 %5, 13
   %7 = mul i32 5, %6
   %8 = udiv i32 %7, 3
   store i32 %8, i32* %b
   %9 = load i32* %a
   %10 = load i32* %b
   %11 = add i32 %9, %10
   %12 = sub i32 %11, 10
   %13 = add i32 %12, 21
   call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* @print.str, i32 0, i32 0), i32 %13 )
   %15 = load i32* %a
   %16 = load i32* %b
   %17 = mul i32 %15, %16
   call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* @print.str, i32 0, i32 0), i32 %17 )
   %19 = load i32* %a
   %20 = load i32* %b
   %21 = sub i32 %19, %20
   store i32 %21, i32* %c
   %22 = load i32* %c
   %23 = add i32 %22, 34
   call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* @print.str, i32 0, i32 0), i32 %23 )
   ret i32 0
}