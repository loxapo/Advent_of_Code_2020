import java.io.File
import java.io.InputStream

public data class mutableInteger(var value: Int)

fun execute(instr: ArrayList<String>, acc: mutableInteger): Boolean
{
    var result = false
    acc.value = 0
    var record = HashMap<Int, Int>()
    var i = 0
    while (i <= instr.lastIndex)
    {
        if (record.containsKey(i)) break
        record.put(i, 1)
        var op = instr[i].take(3)
        var increment = instr[i].substring(3).replace("+", "").toInt()
        if (op == "nop") i++
        else if (op == "acc")
        {
            acc.value += increment
            i++
        }
        else if (op == "jmp") i+=increment
    }
    if (i > instr.lastIndex) result = true
    return result
}

fun main(args: Array<String>)
{
    val instructions = ArrayList<String>()
    File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 8\\Day_8_Input.txt").inputStream().bufferedReader().forEachLine{instructions.add(it.replace(" ", ""))}

    var acc = mutableInteger(0)
    execute(instructions, acc)
    println("Part 1: The value of the accumulator immediately before the infinite loop begins is: " + acc.value)

    for (i in instructions.indices)
    {
        var op = instructions[i].take(3)
        if (op == "nop")
        {
            var instr = ArrayList<String>(instructions)
            instr[i] = instr[i].replace("nop", "jmp")
            if(execute(instr, acc)) break

        }
        else if (op == "jmp")
        {
            var instr = ArrayList<String>(instructions)
            instr[i] = instr[i].replace("jmp", "nop")
            if(execute(instr, acc)) break
        }
    }
    println("Part 2: The value of the accumulator after repairing the code is: " + acc.value)
}