func_start checkScope
treturn void
idec x
lt x 10
loop_start
idec x
store x
istore 1
add
assign x
loop_end
func_end
func_start main
treturn void
call checkScope 1
func_end
