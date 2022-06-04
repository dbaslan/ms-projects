# -----------------------------------------------------------
# Deniz Baran ASLAN
# 04.06.2022
# SWE514 Project - STM2IR
#
# Converts simple arithmetic statements to LLVM IR code
# -----------------------------------------------------------

import sys
import re

# initialize sets and dictionaries
variables = []
stack = []
statements = []
operators = ["(", ")", "-", "+", "/", "*"]
op_dict = {"-": "sub", "+": "add", "*": "mul", "/": "udiv"}
priorities = {"-": 0, "+": 0, "*": 1, "/": 1}

# add initial LLVM IR lines
llvm_ir = ["; ModuleID = 'stm2ir'",
           "declare i32 @printf(i8*, ...)",
           "@print.str = constant [4 x i8] c\"%d\\0A\\00\"",
           "",
           "define i32 @main() {"]

# read input file from argument, store statements in list
with open(sys.argv[1], "r") as input_file:
    for line in input_file.readlines():
        statements.append(line.rstrip("\n"))


# find variables, allocate memory
for line in statements:
    for i in range(len(line)):
        if line[i] == "=":
            if line[:i] not in variables:
                variables.append(line[:i])

for variable in variables:
    llvm_ir.append(f"   %{variable} = alloca i32")


# convert infix to postfix notation
for index, statement in enumerate(statements):
    postfix_statement = []
    assignment = False
    statement = list(filter(None, re.split("([+,\-,=,/,\*,(,)])", statement)))

    if "=" in statement:
        expression = statement[2:]
        assignment = True
    else:
        expression = statement

    for token in expression:
        if token not in operators:
            postfix_statement.append(token)
        elif token == "(":
            stack.append(token)
        elif token == ")":
            while stack and stack[-1] != "(":
                postfix_statement.append(stack.pop())

            stack.pop()

        else:
            while stack and stack[-1] != "(" \
                    and priorities[token] <= priorities[stack[-1]]:
                postfix_statement.append(stack.pop())

            stack.append(token)

    while stack:
        postfix_statement.append(stack.pop())

    if assignment:
        postfix_statement = statement[:2] + postfix_statement

    statements[index] = postfix_statement


# turn postfix statements to LLVM IR
temp_counter = 0
printer = "   call i32 (i8*, ...)* @printf(i8* getelementptr ([4 x i8]* " \
          "@print.str, i32 0, i32 0), i32"  # set print command

for statement in statements:
    stack = []
    temp_var_dict = {}
    var_dict = {}
    if "=" in statement:  # handle assignment statements
        assignee = statement[0]
        statement = statement[2:]
        if any(x in operators for x in statement):  # arithmetic operations
            for token in statement:
                if token in variables:
                    temp_counter += 1
                    llvm_ir.append(f"   %{temp_counter} = load i32* %{token}")
                    var_dict[token] = temp_counter
            for token in statement:
                if token.isnumeric():
                    stack.append(token)
                elif token in variables:
                    stack.append(f"%{var_dict[token]}")
                elif token in operators:
                    temp_counter += 1
                    llvm_ir.append(f"   %{temp_counter} = {op_dict[token]} "
                                   f"i32 {stack[-2]}, {stack[-1]}")
                    temp_var_dict[f"result{temp_counter}"] = temp_counter
                    stack.pop()
                    stack.pop()
                    stack.append(f"%{temp_counter}")
            llvm_ir.append(f"   store i32 {stack[0]}, i32* %{assignee}")

        else:  # assignment without arithmetic operations
            llvm_ir.append(f"   store i32 {statement[0]}, i32* %{assignee}")

    else:  # handle print statements
        if any(x in operators for x in statement):  # arithmetic operations
            for token in statement:
                if token in variables:
                    temp_counter += 1
                    llvm_ir.append(f"   %{temp_counter} = load i32* %{token}")
                    var_dict[token] = temp_counter
            for token in statement:
                if token.isnumeric():
                    stack.append(token)
                elif token in variables:
                    stack.append(f"%{var_dict[token]}")
                elif token in operators:
                    temp_counter += 1
                    llvm_ir.append(
                        f"   %{temp_counter} = {op_dict[token]} i32 "
                        f"{stack[-2]}, {stack[-1]}")
                    temp_var_dict[f"result{temp_counter}"] = temp_counter
                    stack.pop()
                    stack.pop()
                    stack.append(f"%{temp_counter}")
        else:  # no arithmetic operations
            temp_counter += 1
            llvm_ir.append(f"   %{temp_counter} = load i32* %{statement[0]}")
        llvm_ir.append(f"{printer} %{temp_counter} )")
        temp_counter += 1

llvm_ir.append("   ret i32 0")  # add return statement


# write output to file
with open("file.ll", "w") as output_file:
    for line in llvm_ir:
        output_file.write(line)
        output_file.write("\n")
    output_file.write("}")  # add closing brace
