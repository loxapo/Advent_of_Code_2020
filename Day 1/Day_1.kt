import java.io.File
import java.io.InputStream

fun main(args: Array<String>) 
{
    val inputStream: InputStream = File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 1\\Day_1_Input.txt").inputStream()
    val lineArray = ArrayList<Int>()
    inputStream.bufferedReader().forEachLine{lineArray.add(it.toInt())} 
    loop@ for (i in 0 until (lineArray.lastIndex-1))
    {
        for (j in (i+1) until lineArray.lastIndex)
        {
            for (k in (j+1)..lineArray.lastIndex)
            {
                if (lineArray.get(i)+lineArray.get(j)+lineArray.get(k)==2020)
                {
                    println("Product of the three entries that sum to 2020: " + lineArray.get(i)*lineArray.get(j)*lineArray.get(k))
                    break@loop
                }
            }
        }
    }
}