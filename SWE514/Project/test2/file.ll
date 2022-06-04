; ModuleID = 'stm2ir'
declare i32 @printf(i8*, ...)
@print.str = constant [4 x i8] c"%d\0A\00"

define i32 @main() {
   %variable1 = alloca i32
   %variable2 = alloca i32
   %variable3 = alloca i32
   store i32 43, i32* %variable1
   %1 = load i32* %variable1
   %2 = sub i32 %1, 41
   store i32 %2, i32* %variable2
   %3 = load i32* %variable2
   %4 = load i32* %variable1
   %5 = mul i32 %3, %4
   %6 = sub i32 72, 69
   %7 = mul i32 %5, %6
   store i32 %7, i32* %variable3
   %8 = load i32* %variable1
   call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* @print.str, i32 0, i32 0), i32 %8 )
   %10 = load i32* %variable2
   call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* @print.str, i32 0, i32 0), i32 %10 )
   %12 = load i32* %variable1
   %13 = mul i32 10, 4
   %14 = add i32 %12, %13
   %15 = sub i32 %14, 2
   call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* @print.str, i32 0, i32 0), i32 %15 )
   %17 = load i32* %variable3
   %18 = sub i32 %17, 33
   call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* @print.str, i32 0, i32 0), i32 %18 )
   ret i32 0
}