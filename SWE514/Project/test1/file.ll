; ModuleID = 'stm2ir'
declare i32 @printf(i8*, ...)
@print.str = constant [4 x i8] c"%d\0A\00"

define i32 @main() {
   %x1 = alloca i32
   %y = alloca i32
   %zvalue = alloca i32
   %k = alloca i32
   store i32 3, i32* %x1
   %1 = udiv i32 11, 2
   store i32 %1, i32* %y
   %2 = load i32* %x1
   %3 = load i32* %y
   %4 = add i32 1, %3
   %5 = mul i32 %2, %4
   %6 = add i32 23, %5
   store i32 %6, i32* %zvalue
   %7 = load i32* %zvalue
   call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* @print.str, i32 0, i32 0), i32 %7 )
   %9 = load i32* %x1
   %10 = load i32* %y
   %11 = load i32* %zvalue
   %12 = sub i32 %9, %10
   %13 = sub i32 %12, %11
   store i32 %13, i32* %k
   %14 = load i32* %x1
   %15 = load i32* %y
   %16 = mul i32 3, %15
   %17 = add i32 2, 5
   %18 = mul i32 1, %17
   %19 = mul i32 %16, %18
   %20 = add i32 %14, %19
   store i32 %20, i32* %k
   %21 = load i32* %k
   %22 = add i32 %21, 1
   call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* @print.str, i32 0, i32 0), i32 %22 )
   ret i32 0
}