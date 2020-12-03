import java.io.File
import java.io.InputStream

fun main(args: Array<String>)
{
    val inputStream: InputStream = File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 2\\Day_2_Input.txt").inputStream()
    val lineList = ArrayList<String>()
    inputStream.bufferedReader().forEachLine{lineList.add(it)} 
    var count = 0
    val delimiter1 = " "
    val delimiter2 = "-"
    val delimiter3 = ": "
    for (i in lineList.indices)
    {
        var parts = lineList.get(i).split(delimiter1, delimiter2, delimiter3)
        if (parts.get(parts.lastIndex).filter{it == parts.get(parts.lastIndex-1).get(0)}.count() in parts.get(0).toInt()..parts.get(1).toInt())
        {
            count++
        }
    }
    println("The number of valid passwords is: " + count)
}