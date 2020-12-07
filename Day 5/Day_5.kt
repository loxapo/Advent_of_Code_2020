import java.io.File
import java.io.InputStream

fun main(args: Array<String>)
{
    // Import Day 5 data
    val boardingPasses = ArrayList<String>()
    File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 5\\Day_5_Input.txt").inputStream().bufferedReader().forEachLine{boardingPasses.add(it)}
    
    val rows = 127
    val columns = 7
    var seatList = ArrayList<Int>()
    var maxSeatID = 0
    for (i in boardingPasses.indices)
    {
        var lowerRow = 0
        var upperRow = rows
        var lowerColumn = 0
        var upperColumn = columns
        for (j in boardingPasses.get(i).indices)
        {
            if (boardingPasses.get(i)[j] == 'F') upperRow = (upperRow + lowerRow)/2
            else if (boardingPasses.get(i)[j] == 'B') lowerRow = Math.ceil((upperRow + lowerRow).toDouble()/2).toInt()
            else if (boardingPasses.get(i)[j] == 'L') upperColumn = (upperColumn + lowerColumn)/2
            else if (boardingPasses.get(i)[j] == 'R') lowerColumn = Math.ceil((upperColumn + lowerColumn).toDouble()/2).toInt()
        }
        var seatID = lowerRow*8 + lowerColumn
        if (seatID > maxSeatID) maxSeatID = seatID
        seatList.add(seatID)
    }
    println("Part 1: The largest seat ID is: " + maxSeatID)
    seatList.sort()
    for (i in 0..seatList.lastIndex-1)
    {
        if (seatList.get(i+1) - seatList.get(i) > 1) 
        {
                println("Part 2: Your seat ID is: " + (seatList.get(i)+1))
                break
        }
    }
}