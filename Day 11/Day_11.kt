import java.io.File
import java.io.InputStream

fun getAdjacent(array: Array<CharArray?>, max_x: Int, max_y: Int, x: Int, y: Int): ArrayList<Char>
{
    var result = ArrayList<Char>()
    for (dx in (if (x > 0) -1 else 0)..(if (x < max_x) 1 else 0))
    {
        for (dy in (if (y > 0) -1 else 0)..(if (y < max_y) 1 else 0))
        {
            if (dx != 0 || dy != 0)
            {
                result.add(array.get(x + dx)!!.get(y + dy));
            }
        }
    }
    return result
}

fun getVisible(array: Array<CharArray?>, max_x: Int, max_y: Int, x: Int, y: Int): ArrayList<Char>
{
    var result = ArrayList<Char>()
    for (dx in (if (x > 0) -1 else 0)..(if (x < max_x) 1 else 0))
    {
        for (dy in (if (y > 0) -1 else 0)..(if (y < max_y) 1 else 0))
        {
            if (dx != 0 || dy != 0)
            {
                var x_coor = x + dx
                var y_coor = y + dy
                var element: Char? = array.get(x_coor)!!.get(y_coor)
                while (element == '.')
                {
                    x_coor += dx
                    y_coor += dy
                    element = array.getOrNull(x_coor)?.getOrNull(y_coor)
                }
                if (element == null) element = '.'
                result.add((element).toChar());
            }
        }
    }
    return result
}

fun main(args: Array<String>)
{
    val seatMapRaw = ArrayList<String>()
    File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 11\\Day_11_Input.txt").inputStream().bufferedReader().forEachLine{seatMapRaw.add(it)}

    // Part 1
    var seatMap = Array<CharArray?>(seatMapRaw.lastIndex+1, {null})
    for (i in seatMapRaw.indices)
    {
        var charSeats = seatMapRaw[i].toCharArray()
        seatMap[i] = charSeats
    }
    var change = true
    while (change)
    {    
        change = false
        var seatMapClone = Array<CharArray?>(seatMap.lastIndex+1, {null})
        for (i in seatMapClone.indices)
        {
            seatMapClone.set(i, CharArray(seatMap.get(0)!!.lastIndex+1))
            for (j in seatMapClone.get(i)!!.indices)
            {
                seatMapClone.get(i)!!.set(j, seatMap.get(i)!!.get(j))
            }
        }
        for (i in seatMap.indices)
        {
            for (j in seatMap.get(i)!!.indices)
            {
                var seat = seatMap.get(i)!!.get(j)
                if (seat == '.') continue
                var adjacent = getAdjacent(seatMap, seatMap.lastIndex, seatMap.get(0)!!.lastIndex, i, j)
                if (seat == 'L' && !adjacent.contains('#')) 
                {
                    seatMapClone.get(i)!!.set(j, '#')
                    change = true
                }
                else if (seat == '#' && adjacent.filter{it == '#'}.size >= 4)
                {
                    seatMapClone.get(i)!!.set(j, 'L')
                    change = true
                }
            }
        }
        seatMap = seatMapClone
    }
    var sum = 0;
    for (i in seatMap.indices)
    {
        for (j in seatMap.get(0)!!.indices)
        {
            if (seatMap.get(i)!!.get(j) == '#') sum++
        }
    }
    println("Part 1: The number of occupied seats is: " + sum)

    // Part 2
    seatMap = Array<CharArray?>(seatMapRaw.lastIndex+1, {null})
    for (i in seatMapRaw.indices)
    {
        var charSeats = seatMapRaw[i].toCharArray()
        seatMap[i] = charSeats
    }
    change = true
    while (change)
    {    
        change = false
        var seatMapClone = Array<CharArray?>(seatMap.lastIndex+1, {null})
        for (i in seatMapClone.indices)
        {
            seatMapClone.set(i, CharArray(seatMap.get(0)!!.lastIndex+1))
            for (j in seatMapClone.get(i)!!.indices)
            {
                seatMapClone.get(i)!!.set(j, seatMap.get(i)!!.get(j))
            }
        }
        for (i in seatMap.indices)
        {
            for (j in seatMap.get(i)!!.indices)
            {
                var seat = seatMap.get(i)!!.get(j)
                if (seat == '.') continue
                var visible = getVisible(seatMap, seatMap.lastIndex, seatMap.get(0)!!.lastIndex, i, j)
                if (seat == 'L' && !visible.contains('#')) 
                {
                    seatMapClone.get(i)!!.set(j, '#')
                    change = true
                }
                else if (seat == '#' && visible.filter{it == '#'}.size >= 5)
                {
                    seatMapClone.get(i)!!.set(j, 'L')
                    change = true
                }
            }
        }
        seatMap = seatMapClone
    }
    sum = 0;
    for (i in seatMap.indices)
    {
        for (j in seatMap.get(0)!!.indices)
        {
            if (seatMap.get(i)!!.get(j) == '#') sum++
        }
    }
    println("Part 2: The number of occupied seats is: " + sum)
}