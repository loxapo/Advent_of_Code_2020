import java.io.File
import java.io.InputStream

fun main(args: Array<String>)
{
    val inputStream: InputStream = File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 2\\Day_2_Input.txt").inputStream()
    val lineList = ArrayList<String>()
    inputStream.bufferedReader().forEachLine{lineList.add(it)} 
    var count1 = 0
    var count2 = 0
    val delimiter1 = " "
    val delimiter2 = "-"
    val delimiter3 = ": "
    for (i in lineList.indices)
    {
        var parts = lineList.get(i).split(delimiter1, delimiter2, delimiter3)
        if (parts.get(parts.lastIndex).filter{it == parts.get(parts.lastIndex-1).get(0)}.count() in parts.get(0).toInt()..parts.get(1).toInt())
        {
            count1++
        }
        var password = parts.get(parts.lastIndex)
        var ind1 = parts.get(0).toInt()-1
        var ind2 = parts.get(1).toInt()-1
        var char = parts.get(2).get(0)
        if ((password.get(ind1)==char && password.get(ind2)!=char)||(password.get(ind1)!=char && password.get(ind2)==char))
        {
            count2++
        }
    }
    println("Part 1: The number of valid passwords is: " + count1)
    println("Part 2: The number of valid passwords is: " + count2)
}