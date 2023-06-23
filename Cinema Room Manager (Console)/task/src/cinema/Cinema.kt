package cinema

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRow = readln().toInt()
    val seatsInRoom = seatsInRow * rows
    val cinema = mutableListOf<MutableList<String>>()
    val firstRowCinema = mutableListOf<String>()
    var countPurchasedTickets = 0
    var percentageOfCountPurchasedTickets = 0.0
    var currentIncome = 0
    val totalIncome = if (seatsInRoom <= 60) {
        seatsInRoom * 10
    } else {
        if (rows % 2 == 0) {
            (rows / 2 * 10 * seatsInRow) + (rows / 2 * 8 * seatsInRow)
        } else {
            rows / 2 * seatsInRow * 10 + ((rows - rows / 2) * seatsInRow * 8 )
        }
    }
    for (i in 0..seatsInRow) {
        if (i == 0) {
            firstRowCinema.add(i, " ")
        } else {
            firstRowCinema.add(i, "$i")
        }
    }
    cinema.add(firstRowCinema)
    for (i in 1..rows) {
        val row = mutableListOf("$i")
        for (j in 1..seatsInRow) {
            row.add("S")
        }
        cinema.add(row)
    }
    var showMenu = true
    while (showMenu) {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when (readln().toInt()) {
            0 -> showMenu = false
            1 -> {
                println("Cinema:")
                for (i in 0 until cinema.size) {
                    println(cinema[i].joinToString(" "))
                }
            }
            2 -> {
                println()
                var uncorrectInput = true
                var selectRow = 0
                var selectSeat = 0
                while(uncorrectInput) {
                    println("Enter a row number:")
                    selectRow = readln().toInt()
                    println("Enter a seat number in that row:")
                    selectSeat = readln().toInt()
                    println()
                    if (selectRow > rows || selectSeat > seatsInRow) {
                        println("Wrong input!")
                    } else if (cinema[selectRow][selectSeat] === "B") {
                        println("That ticket has already been purchased!")
                    } else {
                        uncorrectInput = false
                        val priceOfSeat = if (seatsInRoom <= 60) {
                            10
                        } else {
                            if (selectRow <= rows / 2) {
                                10
                            } else {
                                8
                            }
                        }
                        cinema[selectRow][selectSeat] = "B"
                        countPurchasedTickets += 1
                        percentageOfCountPurchasedTickets =
                            countPurchasedTickets.toDouble() / seatsInRoom.toDouble() * 100
                        currentIncome += priceOfSeat
                        println("Ticket price: \$$priceOfSeat")
                    }
                    println()
                }
            }
            3 -> {
                val formatPercentage = "%.2f".format(percentageOfCountPurchasedTickets)
                println()
                println("Number of purchased tickets: $countPurchasedTickets")
                println("Percentage: $formatPercentage%")
                println("Current income: \$$currentIncome")
                println("Total income: \$$totalIncome")
            }
        }
    }
}